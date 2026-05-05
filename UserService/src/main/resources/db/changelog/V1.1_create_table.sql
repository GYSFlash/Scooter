-- liquibase formatted sql

-- changeset yura:1
CREATE TABLE IF NOT EXIST  users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(100) NOT NULL,
    name VARCHAR(55) NOT NULL,
    surname VARCHAR(55) NOT NULL,
    subscription BOOLEAN NOT NULL DEFAULT FALSE,
    date_of_birth DATE NOT NULL
);

-- changeset yura:2
CREATE TABLE IF NOT EXIST  authorization_data (
    username VARCHAR(55) PRIMARY KEY,
    password VARCHAR(55) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    user_id UUID,
    CONSTRAINT fk_authorization_user
    FOREIGN KEY (user_id) REFERENCES users(id)
);