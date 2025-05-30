-- Table: dish
CREATE TABLE dish_management.dish (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    cost NUMERIC(10, 2) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    thumbnail_url TEXT,
    dish_type_id BIGINT NOT NULL,
    FOREIGN KEY (dish_type_id) REFERENCES dish_management.dish_type(id)
);
