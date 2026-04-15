CREATE TABLE PRICES (
	priceId BIGINT AUTO_INCREMENT PRIMARY KEY,
	brandId INTEGER,
	startDate VARCHAR(255),
	endDate VARCHAR(255),
	priceList BIGINT,
	productId BIGINT,
	priority INTEGER,
	price FLOAT,
	currency VARCHAR(100)
);