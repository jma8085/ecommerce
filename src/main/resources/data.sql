INSERT INTO PRICES (brandId, startDate, endDate, priceList, productId, priority, price, currency)
SELECT * FROM CSVREAD('classpath:static/prices_.csv');