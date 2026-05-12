package org.pushservice.mapper;

import org.mapstruct.Mapper;
import org.pushservice.dto.PushDto;
import org.pushservice.entity.Push;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PushMapper {
    PushDto pushToDto(Push push);
    List<PushDto> pushToDtoList(List<Push> pushList);
}
