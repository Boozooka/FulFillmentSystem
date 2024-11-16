--changeset boozooka:2
DROP TABLE product_entries;
CREATE TABLE IF NOT EXISTS product_entries(
    id SERIAL PRIMARY KEY,
    product_id VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    fulfillmentCenter VARCHAR(20) NOT NULL,
    qty INTEGER,
    val INTEGER
);