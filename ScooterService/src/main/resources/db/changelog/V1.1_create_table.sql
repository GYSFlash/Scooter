-- liquibase formatted sql

-- changeset yura:1
CREATE TABLE station (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(55) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);

-- changeset yura:2
CREATE TABLE scooter (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    model VARCHAR(55) NOT NULL,
    number VARCHAR(55) NOT NULL UNIQUE,
    distance DOUBLE PRECISION NOT NULL DEFAULT 0,
    battery INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'FREE',
    station_id UUID,
    CONSTRAINT fk_scooter_station
    FOREIGN KEY (station_id) REFERENCES station(id)
);