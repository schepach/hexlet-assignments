-- BEGIN
DROP TABLE IF EXISTS products;

CREATE TABLE products
(
    id    SERIAL PRIMARY KEY,
    title TEXT,
    price INT
);
-- END
