CREATE TABLE dish_management.dish_allergen (
    dish_uuid UUID NOT NULL,
    allergen_uuid UUID NOT NULL,
    PRIMARY KEY (dish_uuid, allergen_uuid),
    FOREIGN KEY (dish_uuid) REFERENCES dish_management.dish(uuid) ON DELETE CASCADE,
    FOREIGN KEY (allergen_uuid) REFERENCES dish_management.allergen(uuid) ON DELETE CASCADE
);

GRANT SELECT, INSERT, UPDATE, DELETE ON dish_management.dish_allergen TO admin_user;
GRANT SELECT ON dish_management.dish_allergen TO developer_user;
