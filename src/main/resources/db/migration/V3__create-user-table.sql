CREATE TABLE meow_user (
    user_id bigint AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(100) NOT NULL,
    user_email varchar(100) NOT NULL,
    user_document_number varchar(11) NOT NULL,
    user_phone_number varchar(11) NOT NULL
);