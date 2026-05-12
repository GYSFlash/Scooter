-- liquibase formatted sql

-- changeset yura:1
CREATE TABLE station (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(55) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);
COMMENT ON TABLE station IS 'Точки размещения самокатов';
COMMENT ON COLUMN station.id IS 'Id станции';
COMMENT ON COLUMN station.name IS 'Название станции';
COMMENT ON COLUMN station.latitude IS 'Широта';
COMMENT ON COLUMN station.longitude IS 'Долгота';

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
COMMENT ON TABLE scooter IS 'Самокаты';
COMMENT ON COLUMN scooter.id IS 'Id самоката';
COMMENT ON COLUMN scooter.model IS 'Модель самоката';
COMMENT ON COLUMN scooter.number IS 'Номер самоката';
COMMENT ON COLUMN scooter.distance IS 'Пробег (км)';
COMMENT ON COLUMN scooter.battery IS 'Заряд батареи (%)';
COMMENT ON COLUMN scooter.status IS 'Статус (FREE, IS_USED, REPAIRING)';
COMMENT ON COLUMN scooter.station_id IS 'ID точки, где находится самокат';