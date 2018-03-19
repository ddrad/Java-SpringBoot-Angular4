CREATE TABLE customer (
  id SERIAL PRIMARY KEY
  ,first_name VARCHAR (100)
  ,last_name VARCHAR (100)
  ,middle_name VARCHAR (100)
  ,type VARCHAR (10)
);

CREATE TABLE ad (
  id SERIAL PRIMARY KEY
  ,title VARCHAR (100)
  ,description VARCHAR (2000)
  ,image_path VARCHAR (100)
  ,author int (100)
);

CREATE TABLE product (
  id SERIAL PRIMARY KEY
  ,product_name VARCHAR (100)
  ,cost INTEGER
  ,ad_id INTEGER
  ,CONSTRAINT fk_products_ad
     FOREIGN key (ad_id)
     REFERENCES ad (id)
);

CREATE TABLE auth (
  id SERIAL PRIMARY KEY
  ,email VARCHAR (100)
  ,password VARCHAR (100)
  ,customer_id INTEGER
);

CREATE TABLE token_data (
  id SERIAL PRIMARY KEY
  ,alias VARCHAR (100)
  ,token_data bytea
  ,expiration_time TIMESTAMP
  ,remove_after_expiration BOOLEAN
  ,status VARCHAR (100)
);


