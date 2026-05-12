-- liquibase formatted sql

-- changeset yura:1
CREATE TABLE IF NOT EXISTS  users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(100) NOT NULL,
    name VARCHAR(55) NOT NULL,
    surname VARCHAR(55) NOT NULL,
    subscription BOOLEAN NOT NULL DEFAULT FALSE,
    date_of_birth DATE NOT NULL
);

COMMENT ON TABLE users IS 'Пользователи';
COMMENT ON COLUMN users.id IS 'Id пользователя';
COMMENT ON COLUMN users.email IS 'Email пользователя';
COMMENT ON COLUMN users.name IS 'Имя';
COMMENT ON COLUMN users.surname IS 'Фамилия';
COMMENT ON COLUMN users.subscription IS 'Подписка';
COMMENT ON COLUMN users.date_of_birth IS 'Дата рождения';

-- changeset yura:2
CREATE TABLE IF NOT EXISTS  authorization_data (
    username VARCHAR(55) PRIMARY KEY,
    password VARCHAR(55) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    user_id UUID,
    CONSTRAINT fk_authorization_user
    FOREIGN KEY (user_id) REFERENCES users(id)
);
COMMENT ON TABLE authorization_data IS 'Данные авторизации';
COMMENT ON COLUMN authorization_data.username IS 'Логин пользователя';
COMMENT ON COLUMN authorization_data.password IS 'Пароль';
COMMENT ON COLUMN authorization_data.role IS 'Роль пользователя (USER, ADMIN, EMPLOYEE)';
COMMENT ON COLUMN authorization_data.user_id IS 'Связь с пользователем';