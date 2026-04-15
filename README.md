# ecommerce

Ecommerce is an application to know the price of the company products depending of the queried moment

Ecommerce has a service which you can read the price of the product

## Description SERVICE

#### URI

/ecommerce/api/productinfo

#### In-Parameters

**date:** Parameter to define the moment of the price. Format **yyyy-mm-dd-HH.MM.SS**. This parameter is required

**productId:** Product identification number. This parameter is required

**brandId:** Product brand identification number. This parameter is required

#### Responses

**- 200 - OK: ** When the query is accepted, return product related information.

**- 400 - Bad request:** When one of the parameters is malformed or if a required parameter is missing.
The possible messages:
- Parameter 'date' is malformed
- Parameter 'date' is required
- Parameter 'productId' is required
- Parameter 'brandId' is required

**- 204 - No Content:** When the query is accepted but there is not information related with this.

