CREATE SCHEMA IF NOT EXISTS dish_management;

GRANT USAGE, CREATE ON SCHEMA dish_management TO admin_user;

GRANT USAGE ON SCHEMA dish_management TO developer_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA dish_management
  GRANT SELECT ON TABLES TO developer_user;

GRANT USAGE, CREATE ON SCHEMA dish_management TO flyway_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA dish_management
  GRANT ALL ON TABLES TO flyway_user;
