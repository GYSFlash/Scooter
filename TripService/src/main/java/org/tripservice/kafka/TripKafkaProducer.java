package org.tripservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.tripservice.dto.PushEventDto;

import java.util.UUID;

@Component
public class TripKafkaProducer {

    private static final String TOPIC_STARTED = "trip.started";
    private static final String TOPIC_ENDED = "trip.ended";

    private final KafkaTemplate<String, PushEventDto> kafkaTemplate;

    public TripKafkaProducer(KafkaTemplate<String, PushEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTripStarted(UUID userId, UUID tripId) {
        PushEventDto event = new PushEventDto(userId, "Ваша поездка #" + tripId + " началась!");
        kafkaTemplate.send(TOPIC_STARTED, userId.toString(), event);
    }

    public void sendTripEnded(UUID userId, UUID tripId, String price) {
        PushEventDto event = new PushEventDto(userId, "Поездка #" + tripId + " завершена. Стоимость: " + price + " руб.");
        kafkaTemplate.send(TOPIC_ENDED, userId.toString(), event);
    }
}
