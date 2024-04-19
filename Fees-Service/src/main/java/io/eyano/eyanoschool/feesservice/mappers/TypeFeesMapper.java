package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Mapper class for the entity TypeFees and its DTO TypeFeesDto.
 * @see TypeFeesDto
 * @see TypeFees
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Service @Transactional
@AllArgsConstructor
public class TypeFeesMapper implements Mapper<TypeFeesDto, TypeFees> {
    ModelMapper modelMapper;
    /**
     * Convert a TypeFees entity to a TypeFeesDto
     * @param typeFees : TypeFees entity
     * @return TypeFeesDto
     */
    @Override
    public TypeFeesDto entityFromDTO(TypeFees typeFees) {
        TypeFeesDto dto = modelMapper.map(typeFees,TypeFeesDto.class);

        return dto;
    }
    /**
     * Convert a TypeFeesDto to a TypeFees entity
     * @param typeFeesDto : TypeFeesDto
     * @return TypeFees
     */
    @Override
    public TypeFees dtoFromEntity(TypeFeesDto typeFeesDto) {
        return modelMapper.map(typeFeesDto, TypeFees.class);
    }
    /**
     * Convert a list of TypeFees entities to a list of TypeFeesDto
     * @param typeFeesList : List of TypeFees entities
     * @return List of TypeFeesDto
     */
    @Override
    public List<TypeFeesDto> entitiesFromDtos(List<TypeFees> typeFeesList) {
        List<TypeFeesDto> feesDtoList = new ArrayList<>();
        for (TypeFees typeFees : typeFeesList) {
          TypeFeesDto typeFeesDto = entityFromDTO(typeFees);
            feesDtoList.add(typeFeesDto);
        }
        return feesDtoList;
    }
    /**
     * Convert a Page of TypeFees entities to a Map of String and Object
     * @param typeFeesPage : Page of TypeFees entities
     * @return Map of String and Object
     */
    public Map<String, Object> entitiesFromDtosPage(Page<TypeFees> typeFeesPage) {
        Map<String,Object> entitiesDtoPage = new HashMap<>();
        List<TypeFeesDto> contents = entitiesFromDtos(typeFeesPage.getContent());
        Integer totalPages = typeFeesPage.getTotalPages();

        Long totalElements = typeFeesPage.getTotalElements();
        String toString = typeFeesPage.toString();
        Boolean isLast = typeFeesPage.isLast();
        Boolean isFist = typeFeesPage.isFirst();
        Boolean isEmpty = typeFeesPage.isEmpty();
        Integer number = typeFeesPage.getNumber();
        Integer numberOfElements = typeFeesPage.getNumberOfElements();
        Integer size = typeFeesPage.getSize();
        Boolean hasContent = typeFeesPage.hasContent();
        Boolean hasNext = typeFeesPage.hasNext();
        Boolean hasPrevious = typeFeesPage.hasPrevious();

        entitiesDtoPage.put("totalPages",totalPages);
        entitiesDtoPage.put("contents",contents);
        entitiesDtoPage.put("totalElements",totalElements);
        entitiesDtoPage.put("toString",toString);
        entitiesDtoPage.put("isLast",isLast);
        entitiesDtoPage.put("isFirst",isFist);
        entitiesDtoPage.put("isEmpty",isEmpty);
        entitiesDtoPage.put("number",number);
        entitiesDtoPage.put("numberOfElements",numberOfElements);
        entitiesDtoPage.put("size",size);
        entitiesDtoPage.put("hasContent",hasContent);
        entitiesDtoPage.put("hasPrevious",hasPrevious);
        entitiesDtoPage.put("hasNext",hasNext);


        return entitiesDtoPage;
    }
}
