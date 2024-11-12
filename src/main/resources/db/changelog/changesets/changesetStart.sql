--changeset boozooka:1
CREATE TABLE IF NOT EXISTS product_entries(
    id SERIAL PRIMARY KEY,
    product_id VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    fulfillmentCenter VARCHAR(20) NOT NULL,
    qty INTEGER
);