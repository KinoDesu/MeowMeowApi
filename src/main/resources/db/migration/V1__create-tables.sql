CREATE TABLE Stock (
    stock_id bigint AUTO_INCREMENT PRIMARY KEY,
    stock_quantity int NOT NULL
);
CREATE TABLE Discount (
    discount_id bigint AUTO_INCREMENT PRIMARY KEY,
    discount_description varchar(150) NOT NULL,
    discount_value decimal NOT NULL
);
CREATE TABLE Product (
    product_id bigint AUTO_INCREMENT PRIMARY KEY,
    product_name varchar(100) NOT NULL,
    product_description longtext NOT NULL,
    product_price decimal NOT NULL,
    product_active bool NOT NULL,
    fk_stock_id bigint NOT NULL,
    fk_discount_id bigint,
    KEY FK_1 (fk_stock_id),
    CONSTRAINT FK_1 FOREIGN KEY FK_1 (fk_stock_id) REFERENCES Stock (stock_id),
    KEY FK_2 (fk_discount_id),
    CONSTRAINT FK_2 FOREIGN KEY FK_2 (fk_discount_id) REFERENCES Discount (discount_id)
);
CREATE TABLE Category (
    category_id bigint AUTO_INCREMENT PRIMARY KEY,
    category_name varchar(100) NOT NULL
);
CREATE TABLE Product_Category (
    product_category_id bigint AUTO_INCREMENT PRIMARY KEY,
    fk_product_id bigint NOT NULL,
    fk_category_id bigint NOT NULL,
    KEY FK_1 (fk_product_id),
    CONSTRAINT FK_3 FOREIGN KEY FK_1 (fk_product_id) REFERENCES Product (product_id),
    KEY FK_2 (fk_category_id),
    CONSTRAINT FK_4 FOREIGN KEY FK_2 (fk_category_id) REFERENCES Category (category_id)
);
CREATE TABLE Image (
    image_id bigint AUTO_INCREMENT PRIMARY KEY,
    fk_product_id bigint NOT NULL,
    image_name varchar(100) NOT NULL,
    image_type varchar(15) NOT NULL,
    image_url varchar(255) NOT NULL,
    KEY FK_1 (fk_product_id),
    CONSTRAINT FK_6 FOREIGN KEY FK_1 (fk_product_id) REFERENCES Product (product_id)
);
CREATE TABLE Rating (
    rating_id bigint AUTO_INCREMENT PRIMARY KEY,
    fk_product_id bigint NOT NULL,
    rating_value int NOT NULL,
    rating_comment text NOT NULL,
    KEY FK_1 (fk_product_id),
    CONSTRAINT FK_5 FOREIGN KEY FK_1 (fk_product_id) REFERENCES Product (product_id)
);
CREATE TABLE product_details (
    fk_product_id BIGINT,
    detail_key VARCHAR(25) NOT NULL,
    detail_value VARCHAR(255) NOT NULL,
    PRIMARY KEY (fk_product_id, detail_key),
    FOREIGN KEY (fk_product_id) REFERENCES product(product_id)
);