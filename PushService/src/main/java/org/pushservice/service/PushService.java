package org.pushservice.service;

import org.pushservice.dto.PushDto;
import org.pushservice.dto.PushEventDto;

import java.util.List;
import java.util.UUID;

public interface PushService {
    List<PushDto> getAllPushByUserId(UUID userId);
    void deletePushById(UUID id);
}
