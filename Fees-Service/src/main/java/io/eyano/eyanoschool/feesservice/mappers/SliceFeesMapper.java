package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Mapper class for the entity SliceFees and its DTO SliceFeesDto.
 * @see SliceFeesDto
 * @see SliceFees
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Service @Transactional
@AllArgsConstructor
public class SliceFeesMapper implements Mapper<SliceFeesDto, SliceFees> {
    ModelMapper modelMapper;
    /**
     * Convert a SliceFees entity to a SliceFeesDto
     * @param sliceFees : SliceFees entity
     * @return SliceFeesDto
     */
    @Override
    public SliceFeesDto entityFromDTO(SliceFees sliceFees) {
        SliceFeesDto sliceFeesDto = new SliceFeesDto();
        BeanUtils.copyProperties(sliceFees,sliceFeesDto);

        // TODO: 05/02/2024 implement data recovery in the external modules
        //  Utility to complete the method.

        return sliceFeesDto;
    }
    /**
     * Convert a SliceFeesDto to a SliceFees entity
     * @param sliceFeesDto : SliceFeesDto
     * @return SliceFees
     */
    @Override
    public SliceFees dtoFromEntity(SliceFeesDto sliceFeesDto) {
        SliceFees sliceFees = new SliceFees();
        BeanUtils.copyProperties(sliceFeesDto,sliceFees);
        return sliceFees;
    }

    /**
     * Convert a list of SliceFees entities to a list of SliceFeesDto
     * @param sliceFees : List of SliceFees entities
     * @return List of SliceFeesDto
     */
    @Override
    public List<SliceFeesDto> entitiesFromDtos(List<SliceFees> sliceFees) {
        List<SliceFeesDto> sliceFeesDtoList = new ArrayList<>();
        for (SliceFees sliceFee : sliceFees) {
            sliceFeesDtoList.add(entityFromDTO(sliceFee));
        }
        return sliceFeesDtoList;
    }
    /**
     * Convert a Page of SliceFees entities to a Page of SliceFeesDto
     * @param sliceFeesPage : Page of SliceFees entities
     * @return Page of SliceFeesDto
     */
    public Map<String,Object> entitiesFromDtosPage(Page<SliceFees> sliceFeesPage) {
        Map<String,Object> entitiesDtoPage = new HashMap<>();
        List<SliceFeesDto> contents = entitiesFromDtos(sliceFeesPage.getContent());
        Integer totalPages = sliceFeesPage.getTotalPages();
        Long totalElements = sliceFeesPage.getTotalElements();
        String toString = sliceFeesPage.toString();
        Boolean isLast = sliceFeesPage.isLast();
        Boolean isFist = sliceFeesPage.isFirst();
        Boolean isEmpty = sliceFeesPage.isEmpty();
        Integer number = sliceFeesPage.getNumber();
        Integer numberOfElements = sliceFeesPage.getNumberOfElements();
        Integer size = sliceFeesPage.getSize();
        Boolean hasContent = sliceFeesPage.hasContent();
        Boolean hasNext = sliceFeesPage.hasNext();
        Boolean hasPrevious = sliceFeesPage.hasPrevious();

        entitiesDtoPage.put("totalPages",totalPages);
        entitiesDtoPage.put("contents",contents);
        entitiesDtoPage.put("totalElements",totalElements);
        entitiesDtoPage.put("toString",toString);
        entitiesDtoPage.put("isLast",isLast);
        entitiesDtoPage.put("isFist",isFist);
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
