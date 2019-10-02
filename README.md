[![Build Status](https://travis-ci.org/lpenzey/omnibus.svg?branch=master)](https://travis-ci.org/lpenzey/omnibus)

# Omnibus
The brains behind the bus. 

Interact with the beta version [here](https://which-bus-chicago.herokuapp.com/) (The project is hosted on Heroku's free plan so it takes a bit to warm up)

## Run the project
Versions used:
 - [Jdk/Jre 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - [Scala 2.13.0](https://www.scala-lang.org/download/2.13.0.html)
 - [SBT 1.3.0](https://www.scala-sbt.org/download.html)
 - Developed using [Intellij](https://www.jetbrains.com/idea/download)

Before running locally, do the following:
1. Make sure you have the above versions installed. 
2. Modify the `Access-Control-Allow-Origin` field in `/src/main/scala/com/lpenzey/helpers/CORShandler` with your client connection (e.g. `http://localhost:3000`)
3. If it does not already exist, create a root level `.env` file and add the following variables. You can obtain a CTA bus tracker api key [here](https://www.transitchicago.com/developers/bustracker/)
    ```
    BUS_TRACKER_API_KEY=<insert CTA bus tracker api key>
    JWT_SECRET=<insert jwt secret>
    ```
4. To setup the local mysql database, go to `src/main/resources/application.conf`. In that file there are two database objects. Whichever object is named `database` 
is the one that will be used when the project runs, and it's currently setup for production. To run a local mysql database, rename `database` to 
anything else (`proddatabase` for example) and in first object, rename all four instances of `devdatabase` to just `database`. Then you'll need to be running mysql locally 
(installation details can be found [here](https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-connecting)) and connect to it using the password 
you created during installation (setup varies depending on what platform you're running - the following commands are mac specific, but all varieties can be found at the installation link above).
5. Once installed login to `mysql` which can be done with the `mysql` cli. If not already installed (and you use homebrew), execute `brew install mysql`, then login with the following commands:
```
mysql -u root -p
```
you'll then be prompted to enter the password that you created for the superuser `root`. When logged in, create the database:
```
CREATE DATABASE omnibus;
```
then:
```
USE omnibus;
```
Then create the `users` and `favorites` table with the following:
```
CREATE TABLE IF NOT EXISTS users(
  id         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(200)   NOT NULL,
  password   VARCHAR(200)   NOT NULL
);
CREATE TABLE IF NOT EXISTS favorites(
  id         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  userId     BIGINT(20)   NOT NULL,
  route      VARCHAR(200)   NOT NULL,
  stopId     VARCHAR(200)  NOT NULL,
  FOREIGN KEY (userId)
        REFERENCES users(id)
        ON DELETE CASCADE
);
```
Almost there! With the database and tables created, go back to `src/main/resources/application.conf` and modify the `user` to be `root` 
and the `password` to be whatever password you chose when installing `mysql`. The final `application.conf` file should appear as follows, 
except of course with the password you created:
```
database {
  profile = "slick.jdbc.MySQLProfile$"
  host = localhost
  port = 3306
  dbName = omnibus
  url = "jdbc:mysql://"${database.host}":"${database.port}"/"${database.dbName}"?serverTimezone=UTC"
  driver = "com.mysql.cj.jdbc.Driver"
  user = "root"
  password = "your password"
  numThreads = 10
}

proddatabase{
  profile = "slick.jdbc.MySQLProfile$"
  url= ${?CLEARDB_DATABASE_URL}
  driver = "com.mysql.cj.jdbc.Driver"
  numThreads = 10
  maxConnections = 10
  queueSize=1000
  keepAliveConnection = true
  connectionPool = disabled
}
```

With that done, you can now run the project! Run the following command to start the server:
```
sbt run
```

This will download necessary dependencies and run the project and will start listening for HTTP requests on ``localhost:8080``.

Note: if you're running the [front end](https://github.com/lpenzey/which_bus) as well, you first need to run `npm install` in that repo to download dependencies, and then you need to change all instances of 
`DeployedV1OmnibusUri` in `src/Services/busTrackerAPI` to `LocalOmnibusUri` 

## Run the tests
To build and test this project, run ``sbt test``. 

The test suite is configured to use an in memory database so there's no external connection string to manage. 

Additionally, HTTP requests have also been replaced with stubbed responses to a given path. To add a test route/response, create it like so (actual JSON responses are kept in the fixtures folder to keep things tidy): 

`val httpFuture: Future[HttpResponse] = Marshal(JSONFixtures.routes).to[HttpResponse]`

Then setup the stubbed `HttpWrapper`:

`val stubHttp: HttpWrapper = stub[HttpWrapper]`

The finally within each test (won't work if all of these are instantiated beforehand) use ScalaMock to mock the response with a given route (which are also in `/fixtures):

`stubHttp.externalService _ when JSONFixtures.routesUri returns routesFuture`

`externalService()` is the method in the `HttpWrapper` which can be found in the `/helpers` folder.

## Available routes

Access to the main bus estimate portal is public and has the following routes:

### Bus Routes

`/api/routes` 
  
 `get` - no parameters. Returns all available bus routes. 

`/api/stops` 

 `get` - returns stops for a given route and direction with the parameters `rt` and `dir`. 
 
 Example: `/api/stops?rt=70&dir=Eastbound`

`/api/directions`

 `get` - returns directions for a given route with the parameter `rt`. 
 
 Example: `/api/directions?rt=70`


`/api/predictions`

 `get` - returns available bus predictions for a given route and stop id with parameters `rt` and `stpid`. 
 
 Example: `/api/predictions?rt=70&stpid=2000`


### User Routes

Access to favorites is protected. To gain access, create an account over at:

`/users/register`

`/users/login`

`/users/favorites`


### Features to add

- Logout: Currently the authorization token is stored in session storage and comes with an expiration date of 10 hours. It would be great to implement server-side revocation of a compromised token, or for a logged out user. 
- Delete a user: This is implemented on the back end but needs a front end component
- Delete a favorite
- Change your username/password
- Recover a lost password
- Pre-compile db actions to speed up queries
- Implement a supervisor strategy to have more control over actors 
