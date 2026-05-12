-- liquibase formatted sql

-- changeset yura:1
CREATE TABLE messages (
    id UUID PRIMARY KEY,
    message VARCHAR(255)
);

COMMENT ON TABLE messages IS 'Сообщения для уведомлений';
COMMENT ON COLUMN messages.id IS 'Id сообщения';
COMMENT ON COLUMN messages.message IS 'Текст сообщения';

-- changeset yura:2
CREATE TABLE push (
    id UUID PRIMARY KEY,
    message_id UUID,
    user_id UUID,
    CONSTRAINT fk_push_message FOREIGN KEY (message_id) REFERENCES messages (id)
);
COMMENT ON TABLE push IS 'Push-уведомления';
COMMENT ON COLUMN push.id IS 'Id уведомления';
COMMENT ON COLUMN push.message_id IS 'ID сообщения';
COMMENT ON COLUMN push.user_id IS 'ID пользователя, которому отправлено уведомление';

