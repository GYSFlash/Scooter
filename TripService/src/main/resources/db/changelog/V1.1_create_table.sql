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

COMMENT ON TABLE trip IS 'Поездки';
COMMENT ON COLUMN trip.id IS 'Id поездки';
COMMENT ON COLUMN trip.start_trip IS 'Дата начала поездки';
COMMENT ON COLUMN trip.end_trip IS 'Дата окончания поездки';
COMMENT ON COLUMN trip.price IS 'Стоимость поездки';
COMMENT ON COLUMN trip.distance IS 'Пройденное расстояние (км)';
COMMENT ON COLUMN trip.user_id IS 'ID пользователя';
COMMENT ON COLUMN trip.scooter_id IS 'ID самоката';
COMMENT ON COLUMN trip.station_from_id IS 'Точка начала поездки';
COMMENT ON COLUMN trip.station_to_id IS 'Точка завершения поездки';