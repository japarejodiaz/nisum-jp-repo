# Project Nisum TEST 
***
- Proyecto para realizar la evaluacion de recurso de aplicacion REST que realiza la creacion de usuario con los datos 
- personales y de telefono.

# Repositorio GIT del Aplicativo. 
- El repositorio se encuentra en la siguiente direccion https://github.com/japarejodiaz/nisum-jp-test-project.git

##  Clonar el repositorio
- git clone https://github.com/japarejodiaz/nisum-jp-test-project.git
- Rama master y feature/dev-20230831 (bajar y clonar de la rama feature/dev-20230831)

## Para hacer el build. 
- mvn clean install -> Genera el proyecto jar.
- mvn clean install -DskipTests -> Genera el proyecto sin las test.

# Ejecutar el proyecto 
- nisum-project-rest-app/target$ java -jar nisum-project-rest-app-0.0.1-SNAPSHOT.jar

#Swagger del proyecto URL
- Esta es la direccion del Swagger para las pruebas de las funcionalidades ````http://localhost:8080/swagger-ui/index.html#/````

# URL Base de Proyecto de Prueba. 
- http://localhost:8080/users

# Operaciones desarrolladas 
- POST -> http://localhost:8080/users/addUser
- GET -> http://localhost:8080/users/userById
- GET -> http://localhost:8080/users/userByUuid
- GET -> http://localhost:8080/users/getAllUsers/{page}/{size}
- DELETE -> http://localhost:8080/users/deleteUserById

# Swagger configurado 
- URL -> http://localhost:8080/swagger-ui.html

# Base de datos y version de java
- Se configuro base de datos H2, la misma esta en el application.yml.
- url: jdbc:h2:mem:nisumdb
- JDK 17.

# Configuracion de proyecto 
-Al levantar el proyecto la configuracion genera de forma automatica las tablas de base de datos 
- al levantar el proyecto. 
- La base de datos que tiene dos tablas: users y users_phone, relacionadas por el id de usuario. 

# Condiciones de la contrasena.
Adicionalmente se me olvidada para el password tiene las siguientes condiciones:
- Minimo de 8 caractares y maximo 16.
- Una letra mayuscula
- Una letra minuscula
- Un numero
- Un caracter especial. 







