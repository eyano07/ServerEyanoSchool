package io.eyano.eyanoschool.feesservice.mappers;

import java.util.List;

public interface Mapper <DTO, E>{
    DTO entityFromDTO(E e);
    E dtoFromEntity(DTO dto);
    List<DTO> entitiesFromDtos(List<E> eList);
}
