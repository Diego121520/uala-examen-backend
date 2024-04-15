# uala-examen-backend

Este repositorio contiene mi solución para el examen técnico de uala.

## Tecnologías Utilizadas

- Java: 17
- Spring Boot: 3.2.4
- maven
- hibernate
- h2

- ## Instrucciones de Instalación

### Base de datos

no es necesario realizar ninguna configuración adicional, ya que al ser una base de datos embebida en memoria (h2) se inicializa automáticamente con el proyecto y a su vez se carga automáticamente unos usuario y tweets al iniciar la aplicación.

3. Ejecutar el backend de Spring Boot. 

4. Acceder a la aplicación en el navegador: http://localhost:8080

#Enpoints

## user

### create user
URL: /api/user
Método: POST
Descripción: Este endpoint permite crear un nuevo usuario.
Parámetros: se envía por body la propiedad "username" con el nombre de usuario deseado.

### follow user
URL: /api/user/{userId}/follow/{followId}
Método: POST
Descripción: Este endpoint permite seguir a otro usuario.
Parámetros: se envía por path variable el id del usuario y el id del usuario que se desea seguir.

## tweet

### create tweet
URL: /api/tweet/user/{userId}
Método: POST
Descripción: este endpoint permite crear un tweet dado el usuario.
Parámetros: se envía por path variable el id del usuario al que pertenece el tweet.

### timeline tweet
URL: /api/tweet/timeline/user/{userId}?page=0&size=5
Método: GET
Descripción: Este endpoint permite obtener la línea de tiempo de tweets de un usuario específico, paginada según el número de página y el tamaño de la página especificados.
Parámetros: se envía por path variable el id del usuario, el número de página por query param (page) y la cantidad de tweets a mostrar por pagina por query param (size).


