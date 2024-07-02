CREATE TABLE Stock
(
 stock_id       bigint AUTO_INCREMENT ,
 stock_quantity int NOT NULL ,

PRIMARY KEY (stock_id)
);

CREATE TABLE Discount
(
 discount_id          bigint AUTO_INCREMENT ,
 discount_description varchar(150) NOT NULL ,
 discount_value       decimal NOT NULL ,

PRIMARY KEY (discount_id)
);

CREATE TABLE Product
(
 product_id          bigint AUTO_INCREMENT ,
 product_name        varchar(100) NOT NULL ,
 product_description longtext NOT NULL ,
 product_price       decimal NOT NULL ,
 product_details     json NOT NULL ,
 product_active      bool NOT NULL ,
 fk_stock_id         bigint ,
 fk_discount_id      bigint ,

PRIMARY KEY (product_id),
KEY FK_1 (fk_stock_id),
CONSTRAINT FK_1 FOREIGN KEY FK_1 (fk_stock_id) REFERENCES Stock (stock_id),
KEY FK_2 (fk_discount_id),
CONSTRAINT FK_2 FOREIGN KEY FK_2 (fk_discount_id) REFERENCES Discount (discount_id)
);

CREATE TABLE Category
(
 category_id   bigint AUTO_INCREMENT ,
 category_name varchar(100) NOT NULL ,

PRIMARY KEY (category_id)
);

CREATE TABLE Product_Category
(
 product_category_id bigint AUTO_INCREMENT ,
 fk_product_id       bigint ,
 fk_category_id      bigint ,

PRIMARY KEY (product_category_id),
KEY FK_1 (fk_product_id),
CONSTRAINT FK_3 FOREIGN KEY FK_1 (fk_product_id) REFERENCES Product (product_id),
KEY FK_2 (fk_category_id),
CONSTRAINT FK_4 FOREIGN KEY FK_2 (fk_category_id) REFERENCES Category (category_id)
);

CREATE TABLE Image
(
 image_id      bigint AUTO_INCREMENT ,
 fk_product_id bigint ,
 image_name    varchar(100) NOT NULL ,
 image_type    varchar(15) NOT NULL ,
 image_url     varchar(255) NOT NULL ,

PRIMARY KEY (image_id),
KEY FK_1 (fk_product_id),
CONSTRAINT FK_6 FOREIGN KEY FK_1 (fk_product_id) REFERENCES Product (product_id)
);

CREATE TABLE Rating
(
 rating_id      bigint AUTO_INCREMENT ,
 fk_product_id  bigint ,
 rating_value   int NOT NULL ,
 rating_comment text NOT NULL ,

PRIMARY KEY (rating_id),
KEY FK_1 (fk_product_id),
CONSTRAINT FK_5 FOREIGN KEY FK_1 (fk_product_id) REFERENCES Product (product_id)
);