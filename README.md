# MS 2 - rpcadastro -
Este é o segundo microserviço de teste e será consumido pelo primeiro ms. RP testing with Spring Boot Java microservice and entities with Mysql database.

## Spring Boot - Maven
Esse projeto ilustra a estrutura gerada em Spring Initializr, através da utilização do arquétipo  do Maven utilizando o JDK17.

## Getting started

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 17: Necessário para executar o projeto Java](https://download.oracle.com/java/17/archive/jdk-17_windows-x64_bin.exe)
- [Maven 3.9.6: Necessário para realizar o build do projeto Java](https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip)
- Intellij ou Eclipse: Para desenvolvimento do projeto


## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/mpazin/rpcadastro
```


## Build

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```

O comando irá baixar todas as dependências do projeto e criar um diretório *target* com os artefatos construídos, que incluem o arquivo jar do projeto. O Maven exibirá essas informação no console.


## Features

O projeto pode ser usado como modelo para iniciar o desenvolvimento de um projeto Java usando o Maven. Ele também demonstra de forma prática como configurar o Maven em um projeto Java.


## Configuration

To run the project, you need to use Intellij, so that it identifies the dependencies required for execution in the *Maven .m2 repository*. Once the project is imported, a *.classpath* file
will be created that will inform which is the main class for execution. The project must run locally and add the environment variable *SPRING_PROFILES_ACTIVE=local*. This microservice is 
already configured to run locally with *port 8090* in the *application.yaml* file.

## Contact
- LinkedIn: https://www.linkedin.com/in/marcio-pazin-ab178523/
- e-mail: marciopazin@gmail.com


## License

Not apply
