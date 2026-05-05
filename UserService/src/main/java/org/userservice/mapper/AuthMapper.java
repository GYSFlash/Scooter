package org.userservice.mapper;

import org.mapstruct.Mapper;
import org.userservice.dto.AuthDto;
import org.userservice.entity.AuthorizationData;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthorizationData toAuthData(AuthDto authDto);
    AuthDto toAuthDto(AuthorizationData authorizationData);
}
