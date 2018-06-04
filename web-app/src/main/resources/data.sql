-- customers
INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('master_1', 'master_ls_1', 'master_mn_1', 'MASTER');
INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('master_2', 'master_ls_2', 'master_mn_2', 'MASTER');
INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('customer_1', 'customer_ls_1', 'customer_mn_1', 'CUSTOMER');
INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('customer_2', 'customer_ls_2', 'customer_mn_2', 'CUSTOMER');
INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('customer_3', 'customer_ls_3', 'customer_mn_3', 'CUSTOMER');
INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('customer_4', 'customer_4', 'customer_mn_4', 'CUSTOMER');

INSERT INTO authorization_data (email, password, customer_id) VALUES
  ('master@mail.com', '123456', 1);
INSERT INTO authorization_data (email, password, customer_id) VALUES
  ('master2@mail.com', '123456', 2);
INSERT INTO authorization_data (email, password, customer_id) VALUES
  ('customer@mail.com', '123456', 3);
INSERT INTO authorization_data (email, password, customer_id) VALUES
  ('customer2@mail.com', '123456', 4);
INSERT INTO authorization_data (email, password, customer_id) VALUES
  ('customer3@mail.com', '123456', 5);
INSERT INTO authorization_data (email, password, customer_id) VALUES
  ('customer4@mail.com', '123456', 6);

INSERT INTO ad (title, description, image_path, author)
VALUES ('Master Of Manicure', 'Test description for Manicure ad with several products', 'https://ladywomans.ru/wp-content/uploads/2017/10/manicure-trendy-3.jpg', 1);
INSERT INTO ad (title, description, image_path, author)
VALUES ('Master Of Manicure2', 'Test description for Manicure ad with several products', 'https://ladywomans.ru/wp-content/uploads/2017/10/manicure-trendy-3.jpg', 1);
INSERT INTO ad (title, description, image_path, author)
VALUES ('Master Of Manicure3', 'Test description for Manicure ad with several products', 'https://ladywomans.ru/wp-content/uploads/2017/10/manicure-trendy-3.jpg', 1);
INSERT INTO ad (title, description, image_path, author)
VALUES ('Master Of Manicure4', 'Test description for Manicure ad with several products', 'https://ladywomans.ru/wp-content/uploads/2017/10/manicure-trendy-3.jpg', 2);
INSERT INTO ad (title, description, image_path, author)
VALUES ('Master Of Manicure5', 'Test description for Manicure ad with several products', 'https://ladywomans.ru/wp-content/uploads/2017/10/manicure-trendy-3.jpg', 2);

INSERT INTO product (product_name, cost, ad_id)
VALUES ('Manicure', 1000, 1);
INSERT INTO product (product_name, cost, ad_id)
VALUES ('Manicure', 1000, 2);
INSERT INTO product (product_name, cost, ad_id)
VALUES ('Manicure', 1000, 3);
INSERT INTO product (product_name, cost, ad_id)
VALUES ('Manicure', 1000, 4);
INSERT INTO product (product_name, cost, ad_id)
VALUES ('Manicure', 1000, 5);

COMMIT;