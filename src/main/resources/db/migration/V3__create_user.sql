CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
