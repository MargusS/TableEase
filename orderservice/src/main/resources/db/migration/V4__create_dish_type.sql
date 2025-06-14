-- Table: dish_type
CREATE TABLE dish_management.dish_type (
    uuid UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    destination VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE
);

-- Seed data
INSERT INTO dish_management.dish_type (uuid, name, destination, created_at)
VALUES
    (gen_random_uuid(), 'BEVERAGE', 'BAR', now()),
    (gen_random_uuid(), 'STARTER', 'KITCHEN', now()),
    (gen_random_uuid(), 'FIRST_COURSE', 'KITCHEN', now()),
    (gen_random_uuid(), 'SECOND_COURSE', 'KITCHEN', now()),
    (gen_random_uuid(), 'DESSERT', 'KITCHEN', now());

-- Permissions
GRANT SELECT, INSERT, UPDATE, DELETE ON dish_management.dish_type TO admin_user;
GRANT SELECT ON dish_management.dish_type TO developer_user;
