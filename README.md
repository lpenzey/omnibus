[![Build Status](https://travis-ci.org/lpenzey/omnibus.svg?branch=master)](https://travis-ci.org/lpenzey/omnibus)

# Omnibus
The brains behind the bus. 

Interact with the beta version [here](https://which-bus-chicago.herokuapp.com/)

Written in [Scala](https://scala-lang.org)

## Run the project

This repository uses [SBT](http://www.scala-sbt.org/) as build tool. 

To run the project clone this repository and import it into your IDE or run the following command:

Note: before running locally, modify the `Access-Control-Allow-Origin` field in `/lpenzey/helpsers/CORShandler` with your client connection (e.g. `http://localhost:3000`)
```
sbt run
```

This will download necessary dependencies and run the project and will start listening for HTTP requests on ``localhost:8080``.

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

###Bus Routes
`/api/routes`

`/api/stops`

`/api/directions`

`/api/predictions`

###User Routes
Access to favorites is protected. To gain access, create an account over at: `/users/register`

`/users/login`

`/users/favorites`


###Features to add
- Logout: Currently the authorization token is stored in session storage and comes with an expiration date of 10 hours. It would be great to implement server-side revocation of a compromised token, or for a logged out user. 
- Delete a user: This is implemented on the back end but needs a front end component
- Delete a favorite
- Change your username/password
- Recover a lost password
- Pre-compile db actions to speed up queries
- Implement a supervisor strategy to have more control over actors 