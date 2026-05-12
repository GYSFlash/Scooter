package org.pushservice.service;

import org.pushservice.dto.PushDto;
import org.pushservice.dto.PushEventDto;
import org.pushservice.mapper.PushMapper;
import org.pushservice.repository.PushRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PushServiceImpl implements PushService {
    private PushRepository pushRepository;
    private PushMapper pushMapper;

    public PushServiceImpl(PushRepository pushRepository, PushMapper pushMapper) {
        this.pushRepository = pushRepository;
        this.pushMapper = pushMapper;
    }
    @Override
    public void deletePushById(UUID id) {
        pushRepository.deleteById(id);
    }
    @Override
    public List<PushDto> getAllPushByUserId(UUID userId) {
        return pushMapper.pushToDtoList(pushRepository.findByUserId(userId));
    }
}
