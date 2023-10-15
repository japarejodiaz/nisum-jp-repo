# Project Nisum APP TEST Jesus Parejo 
***
Proyecto para realizar la evaluacion de recurso de aplicacion REST que realiza la creacion de usuario con los datos 
personales y de telefono.

# Repositorio GIT del Aplicativo. 
El repositorio se encuentra en la siguiente direccion ```https://github.com/japarejodiaz/nisum-jp-repo.git```

##  Clonar el repositorio
Repositorio publico ```https://github.com/japarejodiaz/nisum-jp-repo.git``` 
- para clonarlo ```git clone https://github.com/japarejodiaz/nisum-jp-repo.git```
- Rama del proyecto ```main``` 

## Para hacer el build. 
- para buildearlo/construirlo ```mvn clean install``` -> Genera el proyecto jar.
- para buildearlo sin las test ```mvn clean install -DskipTests``` -> Genera el proyecto sin las test.

## Para ejecutarlo desde la linea de comando
- Para ejecutarlo desde la linea de comando ```mvn clean install spring-boot:run -DskipTests``` 

#Swagger del proyecto URL
- Esta es la direccion del Swagger para las pruebas de las funcionalidades ````http://localhost:8080/swagger-ui/index.html#/````

# URL para generar token de autenticacion - desde Postman
- ```http://localhost:8080/oauth/token```
Parametros requeridos para el token Body en Postman
- Body tipo  = ```x-www-form-urlencoded```
- ```username = japarejo@gmail.com```
- ```password = 12345```
- ```grant_type = password```
- Header 
- ```Content-type = application/x-www-form-urlencoded```
- Autorization
- ```Type = Basic Auth``` 
- Ingresar los parametros para Authorizacion
- ```username = angularapp```
- ```password = 123456```

# Cada uno de los endpoints de servicio debe inyectar en postman o swagger 
Autorization o JWT Key
- ```Type = Bearer Token```
Colocar en el campo token el valor del campo llamado accessTokem generado en el endpoint: ```http://localhost:8080/oauth/token```.  
Ejemplo = ```access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTczODk1MjUsInVzZXJfbmFtZSI6ImphcGFyZWpvQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwianRpIjoiWXhvblpQTXNYVXhla0pVeDRTY25oRG5SRUs4IiwiY2xpZW50X2lkIjoiYW5ndWxhcmFwcCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.9MFk3h3vcKe_zwi6G4Y6yi8V-T7TcZpSjVkb6TZIOuo"```
# URL Base de Proyecto de Prueba. 
- ```http://localhost:8080/users```
# Operaciones desarrolladas de usuario
- POST -> ```http://localhost:8080/users/addUser```
- PUT -> ```http://localhost:8080/users/updateUser```
- GET -> ```http://localhost:8080/users/userById```
- GET -> ```http://localhost:8080/users/userByUuid```
- GET ->  ```http://localhost:8080/users/getAllUsers/{page}/{size}```
- DELETE -> ```http://localhost:8080/users/deleteUserByUuid```

# Base de datos y version de java
Se configuró base de datos H2, la misma esta en el application.yml ````url: jdbc:h2:mem:nisumdb````.
Usuario de base de datos ```sa``` no requiere para las pruebas password
Cabe destacar que al arrancar el proyecto se crea la base de datos y los primeros datos bases para la autenticacion de usuario y su token 
- url: jdbc:h2:mem:nisumdb
- Version de java ```JDK 17```.

# Configuracion de proyecto 
Al Arrancar el proyecto la configuracion genera de forma automatica las tablas de base de datos 
al levantar el proyecto y se genera la data base para el usuario de autenticacion de la aplicacion. 
La base de datos que tiene 4 tablas: 
- ```users``` 
- ```roles```
- ```users_roles``` informacion de ROL se crea solo por script para el token de autenticacion:
  - Roles ```ROLE_ADMIN``` Y ``ROLE_USER``
- ```users_phone``` relacionadas por el id de usuario. 

# Condiciones de la contrase#a.
Adicionalmente se configura para el password en el archivo application.yml y las mismas se configuran ahi 
las siguientes condiciones:
- Minimo de 8 y maximo 12 Caracteres.
- Una letra mayuscula
- Una letra minuscula
- Un numero
- Un caracter especial entre los siguientes ```@#$%``` 







