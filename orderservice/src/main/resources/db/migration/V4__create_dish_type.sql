-- Table: dish_type
CREATE TABLE dish_management.dish_type (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

INSERT INTO dish_management.dish_type (uuid, name) VALUES
    (gen_random_uuid(), 'BEVERAGE'),
    (gen_random_uuid(), 'STARTER'),
    (gen_random_uuid(), 'FIRST_COURSE'),
    (gen_random_uuid(), 'SECOND_COURSE'),
    (gen_random_uuid(), 'DESSERT');