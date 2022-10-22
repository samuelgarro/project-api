# project-api #
This project, is a project for university for programming  web of UNVIME.

### Technologies ###
Was developed with:
- IntelliJ IDEA 2021.3
- OpenJDK Azul Zulu 17.0.4
- Maven 3.8.1
- Spring Boot 2.7.4
- MongoDB 6.0.1

### Execute API Rest ###

1. First create executable jar
<pre>
mvn clean
mvn package
</pre>
2. Execute jar created
<pre>java -jar target/application.jar</pre>
3. For check if api up, run next curl or open browser with url
<pre>curl --location --request GET 'http://localhost:8080/hello</pre>
4. Ready to use.

### Connect to db ###

With MongoDBCompass connect with url:
<pre>mongodb://localhost:27017</pre>
