-- Table: allergen
CREATE TABLE dish_management.allergen (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

INSERT INTO dish_management.allergen (uuid, name) VALUES
    (gen_random_uuid(), 'Gluten'),
    (gen_random_uuid(), 'Crustaceans'),
    (gen_random_uuid(), 'Eggs'),
    (gen_random_uuid(), 'Fish'),
    (gen_random_uuid(), 'Peanuts'),
    (gen_random_uuid(), 'Soybeans'),
    (gen_random_uuid(), 'Milk'),
    (gen_random_uuid(), 'Nuts'),
    (gen_random_uuid(), 'Celery'),
    (gen_random_uuid(), 'Mustard'),
    (gen_random_uuid(), 'Sesame seeds'),
    (gen_random_uuid(), 'Sulphur dioxide and sulphites'),
    (gen_random_uuid(), 'Lupin'),
    (gen_random_uuid(), 'Molluscs');