# Estacionamiento de Vehículos

Este es un proyecto de demostración desarrollado con Spring Boot para gestionar un estacionamiento de vehículos.

## Versiones utilizadas

- Spring Boot: 3.2.3
- Java: 17
- PostgreSQL Driver: 42.7.2

## Dependencias

El proyecto utiliza las siguientes dependencias de Spring Boot:

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-data-rest
- spring-boot-starter-validation
- spring-boot-starter-tomcat (alcanzado en tiempo de ejecución)
- spring-boot-starter-test (para pruebas unitarias)

## Estructura del proyecto

El proyecto sigue la estructura típica de un proyecto Spring Boot, con los siguientes directorios principales:

- `src/main/java`: Contiene el código fuente Java del proyecto.
- `src/main/resources`: Contiene los recursos, como archivos de configuración.
- `src/test`: Contiene las pruebas unitarias del proyecto.
  
## Herramientas
Adjunto esta la colección de postman de los servicios, el nombre es `estacionamiento.postman_collection`

## Ejecución

Para ejecutar el proyecto, puedes usar el plugin de Maven de Spring Boot:

```bash
mvn spring-boot:run
