-- liquibase formatted sql

-- changeset yura:1
CREATE TABLE IF NOT EXISTS trip (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    start_trip DATE,
    end_trip DATE,
    price NUMERIC(10, 2),
    distance DOUBLE PRECISION,
    user_id UUID,
    scooter_id UUID,
    station_from_id UUID,
    station_to_id UUID
);