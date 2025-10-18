DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
    brand_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list BIGINT PRIMARY KEY,
    priority INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    curr VARCHAR(3) NOT NULL
);

CREATE INDEX idx_product_brand_date ON PRICES (product_id, brand_id, start_date, end_date);
CREATE INDEX idx_brand ON PRICES (brand_id);
CREATE INDEX idx_product ON PRICES (product_id);