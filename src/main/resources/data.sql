DELETE FROM PRICES;

INSERT INTO PRICES (id, brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (1, 1, 35455, TIMESTAMP '2020-06-14 00:00:00', TIMESTAMP '2020-12-31 23:59:59', 1, 0, 35.50, 'EUR');

INSERT INTO PRICES (id, brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (2, 1, 35455, TIMESTAMP '2020-06-14 15:00:00', TIMESTAMP '2020-06-14 18:30:00', 2, 1, 25.45, 'EUR');

INSERT INTO PRICES (id, brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (3, 1, 35455, TIMESTAMP '2020-06-15 00:00:00', TIMESTAMP '2020-06-15 11:00:00', 3, 1, 30.50, 'EUR');

INSERT INTO PRICES (id, brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (4, 1, 35455, TIMESTAMP '2020-06-15 16:00:00', TIMESTAMP '2020-12-31 23:59:59', 4, 1, 38.95, 'EUR');