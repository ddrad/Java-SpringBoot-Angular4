INSERT INTO customer (first_name, last_name, middle_name, type)
VALUES ('test_user_1', 'test_user_ls_1', 'test_user_mn_1', 'MASTER');

INSERT INTO auth (email, password, customer_id) VALUES
  ('test@mail.com', '12345', 1);

INSERT INTO ad (title, description, image_path, author)
VALUES ('Master Of Manicure', 'Test description for Manicure ad with several products', 'https://img.grouponcdn.com/deal/aWFpSi2PVtMhHtBiQGQD/gy-2048x1242/v1/c700x420.jpg', 1);

INSERT INTO product (product_name, cost, ad_id)
VALUES ('Manicure', 1000, 1);

COMMIT;