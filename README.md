# Prueba Técnica Credibanco - Ingeniero FullStack
En este repositorio se creó una simple aplicación web para exponer un servicio REST usando el framework de `SpringBoot` con `Java 17` usando un base de datos `MYSQL` de acuerdo al problema a resolver en la prueba técnica.

## Despliegue

### Producción
La Aplicación se encuentra desplegada en AWS en la url: [CredibancoSpringBoot-env.eba-jhijkhmh.us-east-2.elasticbeanstalk.com
](CredibancoSpringBoot-env.eba-jhijkhmh.us-east-2.elasticbeanstalk.com
), 

### Local
En caso de querer desplegar la aplicacion en un entorno local, este debe contar con `Java 17` y tener Maven, adicionalemte tener el puerto **8080** habilitado en caso de tener otro puerto se puede modificar **aplication.properties**, la base de datos esta desplegada en AWS en MYSQL **(usuario de prueba)**

```
spring.datasource.url=jdbc:mysql://database-1.cx6ijbm4d3qu.us-east-2.rds.amazonaws.com:3306/credit_card_core
spring.datasource.username=user_test
spring.datasource.password=8y=bVDk%2fwqx]8
```
* 1: Clonar el repositorio
* 2: Acceder a el archivo clonado
* 3: Ejecutar el comando mvn `spring-boot:run`


## Consumo de la API 

* Ejemplo usando Curl:
``` 
curl -X 'POST' \
  'https://CredibancoSpringBoot-env.eba-jhijkhmh.us-east-2.elasticbeanstalk.com
/api/v1/card' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 6,
  "iterations": 10
}'
``` 

## Estructura del proyecto
El proyecto usa por defecto  la estructura de `SpringBoot` , en este caso se muestra la estructura de los archivos que se trabajaron, y como es la estructura del proyecto

* **Controllers/** : Recibe los eventos de entrada mediante HTTP, Contiene reglas de gestión de eventos.
* **Helpers/** : Clases o Interfaces reutilizables para el proyecto.
* **Models/** : Nos pertime enlazar nuestros datos mediante Objetos.
* **Repositories/**: Enlaza los modelos para realizar consultas.
