CREATE TABLE dish_management.dish (
    uuid UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    cost NUMERIC(10, 2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    thumbnail TEXT,
    dish_type_uuid UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_dish_type FOREIGN KEY (dish_type_uuid) REFERENCES dish_management.dish_type(uuid)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON dish_management.dish TO admin_user;
GRANT SELECT ON dish_management.dish TO developer_user;