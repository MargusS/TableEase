-- Create schemas
CREATE SCHEMA IF NOT EXISTS dish_management;
CREATE SCHEMA IF NOT EXISTS order_management;

-- Create roles
CREATE ROLE admin_user WITH LOGIN PASSWORD 'admin_password';
CREATE ROLE developer_user WITH LOGIN PASSWORD 'developer_password';
CREATE ROLE flyway_user WITH LOGIN PASSWORD 'flyway_password';

-- Grant privileges to admin_user
GRANT ALL PRIVILEGES ON DATABASE tablease TO admin_user;
GRANT ALL PRIVILEGES ON SCHEMA dish_management TO admin_user;
GRANT ALL PRIVILEGES ON SCHEMA order_management TO admin_user;

-- Grant limited privileges to developer_user
GRANT CONNECT ON DATABASE tablease TO developer_user;
GRANT USAGE ON SCHEMA dish_management TO developer_user;
GRANT USAGE ON SCHEMA order_management TO developer_user;
GRANT SELECT ON ALL TABLES IN SCHEMA dish_management TO developer_user;
GRANT SELECT ON ALL TABLES IN SCHEMA order_management TO developer_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA dish_management GRANT SELECT ON TABLES TO developer_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA order_management GRANT SELECT ON TABLES TO developer_user;

-- Grant privileges to flyway_user
GRANT USAGE ON SCHEMA dish_management TO flyway_user;
GRANT USAGE ON SCHEMA order_management TO flyway_user;
GRANT CREATE, ALTER, DROP, SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA dish_management TO flyway_user;
GRANT CREATE, ALTER, DROP, SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA order_management TO flyway_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA dish_management GRANT ALL ON TABLES TO flyway_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA order_management GRANT ALL ON TABLES TO flyway_user;
