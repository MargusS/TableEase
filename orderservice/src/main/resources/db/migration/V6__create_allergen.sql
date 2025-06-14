CREATE TABLE dish_management.allergen (
    uuid UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    symbol_url TEXT,
    description TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE
);

INSERT INTO dish_management.allergen (uuid, name, created_at) VALUES
    (gen_random_uuid(), 'Gluten', now()),
    (gen_random_uuid(), 'Crustaceans', now()),
    (gen_random_uuid(), 'Eggs', now()),
    (gen_random_uuid(), 'Fish', now()),
    (gen_random_uuid(), 'Peanuts', now()),
    (gen_random_uuid(), 'Soybeans', now()),
    (gen_random_uuid(), 'Milk', now()),
    (gen_random_uuid(), 'Nuts', now()),
    (gen_random_uuid(), 'Celery', now()),
    (gen_random_uuid(), 'Mustard', now()),
    (gen_random_uuid(), 'Sesame seeds', now()),
    (gen_random_uuid(), 'Sulphur dioxide and sulphites', now()),
    (gen_random_uuid(), 'Lupin', now()),
    (gen_random_uuid(), 'Molluscs', now());

GRANT SELECT, INSERT, UPDATE, DELETE ON dish_management.allergen TO admin_user;
GRANT SELECT ON dish_management.allergen TO developer_user;
