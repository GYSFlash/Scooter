package org.pushservice.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pushservice.dto.PushEventDto;
import org.pushservice.entity.Message;
import org.pushservice.entity.Push;
import org.pushservice.repository.PushRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PushKafkaConsumer {

    private static final Logger logger = LogManager.getLogger(PushKafkaConsumer.class);

    private final PushRepository pushRepository;

    public PushKafkaConsumer(PushRepository pushRepository) {
        this.pushRepository = pushRepository;
    }

    @KafkaListener(topics = "trip.started", groupId = "push-service-group")
    @Transactional
    public void handleTripStarted(PushEventDto event) {
        logger.info("Получено событие trip.started для пользователя {}", event.getUserId());
        savePush(event);
    }

    @KafkaListener(topics = "trip.ended", groupId = "push-service-group")
    @Transactional
    public void handleTripEnded(PushEventDto event) {
        logger.info("Получено событие trip.ended для пользователя {}", event.getUserId());
        savePush(event);
    }

    private void savePush(PushEventDto event) {
        Message message = new Message(event.getMessage());
        Push push = new Push(message, event.getUserId());
        pushRepository.create(push);
    }
}