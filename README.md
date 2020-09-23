# Iobuilders-application
## Api rest service for management user and create payments

### Introducción
Este proyecto esta desarrollado para utilizarse con java 1.8 y Maven.
Para compilar el proyecto:

```sh
$ mvn clean install
```
Para arrancar el proyecto:

```sh
$ mvn spring-boot:run
```

También se puede generar una imagen Docker y utilizarla así:

```sh
$ docker build -t iobuilders .
```

```sh
$ docker run -p 9090:9090 -t iobuilders
```

Una vez arrancado el proyecto estan habilitadas las siguientes urls:

- [Swagger](http://localhost:9090/api/v1/swagger-ui/index.html?configUrl=/api/v1/v3/api-docs/swagger-config)

- [ApiDoc](http://localhost:9090/api/v1/api-doc.html)



### Objetivo
Se ha diseñado un servicio encargado de gestionar usuarios y poder hacer una transacción con este usuario.
Al ser una POC no se ha tenido en cuenta un login de usuario ni la seguridad del servicio, tampoco hay una gestión de errores y hay unas validaciones básicas.
Se han realizado test unitarios a nivel de controller y a nivel de servicio, son básicos y solo esta testeado el happypath.
La base de datos es H2 y esta en memoria, se inicia al arrancar el servicio. 


### Tecnologia
En el proyecto se ha utilizado Git, Docker, Maven, Springboot, H2 Database, Flyway, RestAssured, Mockito, Powermock, Lombock, Mapstruct, Actuator

---

