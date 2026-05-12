package org.pushservice.controller;

import org.pushservice.dto.PushDto;
import org.pushservice.service.PushService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/push")
public class PushController {
    private PushService pushService;

    public PushController(PushService pushService) {
        this.pushService = pushService;
    }

    @GetMapping("/id")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PushDto> getAllPushByUserId(@PathVariable UUID userId) {
        return pushService.getAllPushByUserId(userId);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePushById(@PathVariable UUID id) {
        pushService.deletePushById(id);
    }
}
