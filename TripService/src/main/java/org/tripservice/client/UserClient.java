package org.tripservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.tripservice.dto.UserResponseDto;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{userId}")
    UserResponseDto getUserById(UUID id);



}
