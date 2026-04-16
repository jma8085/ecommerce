# ecommerce

Ecommerce is an application to know the price of the company products depending of the queried moment

Ecommerce has a service which you can read the price of the product

## Description SERVICE

**URI** => /ecommerce/api/productinfo

#### Input Parameters

**date:** In UTC. Parameter to define the moment of the price. Format **yyyy-mm-ddTHH:MM:SSZ** -> **2020-04-12T10:00:00Z**. This parameter is required

**productId:** Product identification number. This parameter is required

**brandId:** Product brand identification number. This parameter is required

**Example:** 

curl -v "http://localhost:8080/ecommerce/api/productinfo?date=2020-06-14T10:00:00Z&productId=35455&brandId=1"

#### Responses

**- 200 - OK:** When the query is accepted, returns the related product information.

The interface of the returned product:

```json
{
	"productId":35455
	"price": 35.5,
	"priceList": 1,
	"brandId": 1,
	"endDate": "2020-12-31T23:59:59.000Z",
	"startDate": "2020-06-14T00:00:00.000Z",
}
```

**- 400 - Bad request:** When one of the parameters is malformed or if a required parameter is missing.

The possible messages:

- Parameter 'date' is malformed
- Parameter 'date' is required
- Parameter 'productId' is required
- Parameter 'brandId' is required

**- 204 - No Content:** When the query is accepted but there is not information related with this.



## Execution Application

- **Compile project** to execute JUnitTests => mvn clean install
- **Execute application** => mvn spring-boot:run

And test the above example => curl -v "http://localhost:8080/ecommerce/api/productinfo?date=2020-06-14T10:00:00Z&productId=35455&brandId=1"

