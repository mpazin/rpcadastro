# MS 2 - rpcadastro -
This is an example test of a second microservice API (RPCADASTRO) that will be consumed by a first microservice via rest json. This example was made with Spring Boot Java.

## Spring Boot - Maven
This project illustrates the structure generated in Spring Initializr, through the use of the Maven archetype and JDK17.


## Getting started

To run the project, you will need to install the following programs:
- [JDK 17: Required to run the Java project](https://download.oracle.com/java/17/archive/jdk-17_windows-x64_bin.exe)
- [Maven 3.9.6: Required to build the Java project](https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip)
- [Mysql: Required to access data base ](https://www.mysql.com/downloads/)
- Intellij or Eclipse: For project development.


## Development

To start development, you need to clone the GitHub project into a directory of your choice:
```shell
cd "directory of your preference"
git clone https://github.com/mpazin/rpcadastro
```


## Build

To build the project with Maven, run the commands below:
```shell
mvn clean install
```

The command will download all project dependencies and create a *target* directory with the built artifacts, which include the project jar file. Maven will display this information in the console.

## Features

This is a test example of an API for an operations service in a financial wallet. It must be consumed by micro service 1 (RPCARTEIRA). The API receives the request for the specific operation, executes the business rules, accesses the database and returns the updated information to micro service 2 (RPCARTEIRA).
#### Available operations:
- Account and Wallet registration;
- Account balance query;
- Deposit of Amount into the Wallet;
- Withdrawal of Amount from Wallet;
- Transfer of Amount between Wallets;
- Querying balance history by date;
- Save transaction history
- Save wallet log

## Configuration

To run the project, you need to use Intellij, so that it identifies the dependencies required for execution in the *Maven .m2 repository*. Once the project is imported, a *.classpath* file
will be created that will inform which is the main class for execution. The project must run locally and add the environment variable *SPRING_PROFILES_ACTIVE=local*. This microservice is
already configured to run locally with *port 8090* in the *application.yaml* file.
The dllScriptDataBase folder located at the root of the project contains the ddl script for executing the creation of the database in MySql and a simple technical drawing of the services.

## Contact
- LinkedIn: https://www.linkedin.com/in/marcio-pazin-ab178523/
- e-mail: marciopazin@gmail.com


## License

Not apply
