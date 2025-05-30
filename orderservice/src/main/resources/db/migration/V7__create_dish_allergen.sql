-- Table: dish_allergen (many-to-many)
CREATE TABLE dish_management.dish_allergen (
    dish_id BIGINT NOT NULL,
    allergen_id BIGINT NOT NULL,
    PRIMARY KEY (dish_id, allergen_id),
    FOREIGN KEY (dish_id) REFERENCES dish_management.dish(id) ON DELETE CASCADE,
    FOREIGN KEY (allergen_id) REFERENCES dish_management.allergen(id) ON DELETE CASCADE
);