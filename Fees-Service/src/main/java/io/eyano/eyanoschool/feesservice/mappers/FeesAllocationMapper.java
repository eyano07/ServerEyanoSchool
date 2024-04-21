package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dtos.FeesAllocationDto;
import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for the entity FeesAllocation and its DTO FeesAllocationDto.
 * @author : Pascal Tshingila
 * @see FeesAllocationDto
 * @see FeesAllocation
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Service @Transactional
@AllArgsConstructor
@NoArgsConstructor
public class FeesAllocationMapper implements Mapper<FeesAllocationDto, FeesAllocation> {
    ModelMapper modelMapper = new ModelMapper();
    TypeFeesMapper typeFeesMapper;

    /**
     * Convert a FeesAllocation entity to a FeesAllocationDto
     * @param feesAllocation : FeesAllocation entity
     * @return FeesAllocationDto
     */
    @Override
    public FeesAllocationDto entityFromDTO(FeesAllocation feesAllocation) {

        FeesAllocationDto feesAllocationDto = modelMapper.map(feesAllocation,FeesAllocationDto.class);
        // TODO: 04/02/2024 implement data recovery in the external modules
        //  Utility to complete the method.

        return feesAllocationDto ;
    }
    /**
     * Convert a FeesAllocationDto to a FeesAllocation entity
     * @param feesAllocationDto : FeesAllocationDto
     * @return FeesAllocation
     */
    @Override
    public FeesAllocation dtoFromEntity(FeesAllocationDto feesAllocationDto) {
        return modelMapper.map(feesAllocationDto, FeesAllocation.class);
    }

    /**
     * Convert a list of FeesAllocation entities to a list of FeesAllocationDto
     * @param feesAllocations : List of FeesAllocation entities
     * @return List of FeesAllocationDto
     */
    @Override
    public List<FeesAllocationDto> entitiesFromDtos(List<FeesAllocation> feesAllocations) {
        List<FeesAllocationDto> feesAllocationDtoList = new ArrayList<>();
        for (FeesAllocation feesAllocation : feesAllocations) {
            FeesAllocationDto feesAllocationDto = entityFromDTO(feesAllocation);
            feesAllocationDtoList.add(feesAllocationDto);
        }

        return feesAllocationDtoList;
    }
}
