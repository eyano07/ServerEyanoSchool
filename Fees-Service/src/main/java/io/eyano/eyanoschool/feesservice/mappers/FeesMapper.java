package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.entities.Fees;
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
 * Mapper class for the entity Fees and its DTO FeesDto.
 * @see FeesDto
 * @see Fees
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Service @Transactional
@AllArgsConstructor
public class FeesMapper implements Mapper<FeesDto, Fees> {
    TypeFeesRepository typeFeesRepository;
    TypeFeesMapper typeFeesMapper;
    ModelMapper modelMapper;

    /**
     * Convert a Fees entity to a FeesDto
     * @param fees : Fees entity
     * @return FeesDto
     */
    @Override
    public FeesDto entityFromDTO(Fees fees) {
        FeesDto feesDto = modelMapper.map(fees, FeesDto.class);

        // TODO: 04/02/2024 implement data recovery in the external modules
        //  Class, Utility, Finance to complete the method.
        
        return feesDto;
    }
    /**
     * Convert a FeesDto to a Fees entity
     * @param feesDto : FeesDto
     * @return Fees
     */
    @Override
    public Fees dtoFromEntity(FeesDto feesDto) {
        return modelMapper.map(feesDto,Fees.class);
    }

    /**
     * Convert a list Fees entities to list of FeesDto
     * @param feesList : List of Fees entities
     * @return List of FeesDtos
     */
    @Override
    public List<FeesDto> entitiesFromDtos(List<Fees> feesList) {
        List<FeesDto> feesDtoList = new ArrayList<>();
        for (Fees fees : feesList) {
            FeesDto feesDto =  entityFromDTO(fees);
            feesDtoList.add(feesDto);
        }
        return feesDtoList;
    }

    /**
     * Convert a Page of Fees entities to a Page of FeesDto
     * @param feesPage : Page of Fees entities
     * @return Page of FeesDto
     */
    public Map<String,Object> entitiesFromDtosPage(Page<Fees> feesPage) {
        Map<String,Object> entitiesDtoPage = new HashMap<>();
        List<FeesDto> contents = entitiesFromDtos(feesPage.getContent());

        Integer totalPages = feesPage.getTotalPages();
        Long totalElements = feesPage.getTotalElements();
        String toString = feesPage.toString();
        Boolean isLast = feesPage.isLast();
        Boolean isFist = feesPage.isFirst();
        Boolean isEmpty = feesPage.isEmpty();
        Integer number = feesPage.getNumber();
        Integer numberOfElements = feesPage.getNumberOfElements();
        Integer size = feesPage.getSize();
        Boolean hasContent = feesPage.hasContent();
        Boolean hasNext = feesPage.hasNext();
        Boolean hasPrevious = feesPage.hasPrevious();

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
