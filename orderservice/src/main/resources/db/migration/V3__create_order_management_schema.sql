CREATE SCHEMA IF NOT EXISTS order_management;

GRANT USAGE, CREATE ON SCHEMA order_management TO admin_user;

GRANT USAGE ON SCHEMA order_management TO developer_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA order_management
  GRANT SELECT ON TABLES TO developer_user;

GRANT USAGE, CREATE ON SCHEMA order_management TO flyway_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA order_management
  GRANT ALL ON TABLES TO flyway_user;
