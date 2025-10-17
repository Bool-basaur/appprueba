# App prueba (Spring Boot)

Proyecto Java Spring Boot que contiene un endpoint para consultar el precio final para un producto/brand en una fecha concreta

## Versiones y herramientas utilizadas
- Java 21
- Spring boot 3.5.6
- Maven

## Ejecución
1. `mvn clean package`
2. `mvn spring-boot:run`
    - La API escuchará en `http://localhost:8080`
    - La base de datos se puede encontrar en `http://localhost:8080/h2-console`, y se accede con la jdbc url `jdbc:h2:mem:pricesdb` y el usuario `root`
    - La url de api swagger es `http://localhost:8080/swagger-ui/index.html`

## Endpoint
`GET /api/v1/prices?applicationDate={ISO_DATETIME}&brandId={brand_id}&productId={product_id}`

Ejemplo:
`/api/v1/prices?date=2020-06-16T21:00:00&productId=35455&brandId=1`

Respuesta 200:
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.5,
  "currency": "EUR"
}