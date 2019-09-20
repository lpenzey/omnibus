[![Build Status](https://travis-ci.org/lpenzey/omnibus.svg?branch=master)](https://travis-ci.org/lpenzey/omnibus)

# Omnibus
The brains behind the bus. 

Written in [Scala](https://scala-lang.org)

## Run the project

This repository uses [SBT](http://www.scala-sbt.org/) as build tool. To run the project clone this repository and import it into your IDE or run the following command:

```
sbt run
```

This will download necessary dependencies and run the project and will start listening for HTTP requests on ``localhost:8080``.

## Run the tests

To build and test this project, run ``sbt test``. 

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


