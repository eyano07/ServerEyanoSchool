package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dtos.FeesAllocationDto;
import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for FeesAllocationMapper
 * @see FeesAllocationMapper
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
class FeesAllocationMapperTest {
    @Autowired
    FeesAllocationMapper underTest;

    /**
     * Test method for entityFromDTO
     */
    @Test
    void shouldEntityFromDTOTest() {
        FeesAllocation feesAllocation = FeesAllocation.builder()
                .id(1L)
                .designation("personal charge")
                .percentage(25)
                .schoolYear(SchoolYear.builder()
                        .id(1L)
                        .startDate(LocalDate.now())
                        .endDAte(LocalDate.now())
                        .isCurrent(true)
                        .designation("2023-2024")
                        .build())
                .idSchoolYear(1L)
                .typeFees(TypeFees.builder()
                        .id(1L)
                        .designation("Scholar fees")
                        .remove(false)
                        .build())
                .remove(false)
                .build();

        FeesAllocationDto result = underTest.entityFromDTO(feesAllocation);

        AssertionsForClassTypes.assertThat(feesAllocation).usingRecursiveComparison().ignoringFields("id","typeFees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(feesAllocation.getTypeFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getTypeFees());
        AssertionsForClassTypes.assertThat(feesAllocation.getSchoolYear()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getSchoolYear());
    }

    /**
     * Test method for dtoFromEntity
     */
    @Test
    void shouldDtoFromEntityTest() {
        FeesAllocationDto feesAllocationDto = new FeesAllocationDto();
        feesAllocationDto.setDesignation("Operational charge");
        feesAllocationDto.setPercentage(25);
        feesAllocationDto.setSchoolYear(SchoolYear.builder()
                .id(1L)
                .startDate(LocalDate.now())
                .endDAte(LocalDate.now())
                .isCurrent(true)
                .designation("2023-2024")
                .build());
        feesAllocationDto.setIdSchoolYear(1L);

        TypeFeesDto typeFeesDto = new TypeFeesDto();
        typeFeesDto.setId(1L);
        typeFeesDto.setDesignation("Scholar fees");
        typeFeesDto.setRemove(false);

        feesAllocationDto.setTypeFees(typeFeesDto);
        feesAllocationDto.setRemove(false);

        FeesAllocation result = underTest.dtoFromEntity(feesAllocationDto);

        AssertionsForClassTypes.assertThat(feesAllocationDto).usingRecursiveComparison().ignoringFields("id","typeFees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(feesAllocationDto.getTypeFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getTypeFees());
        AssertionsForClassTypes.assertThat(feesAllocationDto.getSchoolYear()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getSchoolYear());
    }
    /**
     * Test method for entitiesFromDtos
     */
    @Test
    void shouldEntitiesFromDtoTest() {
        FeesAllocation feesAllocation = FeesAllocation.builder()
                .id(1L)
                .designation("personal charge")
                .percentage(25)
                .schoolYear(SchoolYear.builder()
                        .id(1L)
                        .startDate(LocalDate.now())
                        .endDAte(LocalDate.now())
                        .isCurrent(true)
                        .designation("2023-2024")
                        .build())
                .idSchoolYear(1L)
                .typeFees(TypeFees.builder()
                        .id(1L)
                        .designation("scholar fees")
                        .remove(false)
                        .build())
                .remove(false)
                .build();

        FeesAllocation feesAllocation2 = FeesAllocation.builder()
                .id(1L)
                .designation("operational charge")
                .percentage(25)
                .schoolYear(SchoolYear.builder()
                        .id(1L)
                        .startDate(LocalDate.now())
                        .endDAte(LocalDate.now())
                        .isCurrent(true)
                        .designation("2023-2024")
                        .build())
                .idSchoolYear(1L)
                .typeFees(TypeFees.builder()
                        .id(1L)
                        .designation("Scholar fees")
                        .remove(false)
                        .build())
                .remove(false)
                .build();

        FeesAllocation feesAllocation3 = FeesAllocation.builder()
                .id(1L)
                .designation("other charge")
                .percentage(25)
                .schoolYear(SchoolYear.builder()
                        .id(1L)
                        .startDate(LocalDate.now())
                        .endDAte(LocalDate.now())
                        .isCurrent(false)
                        .designation("2024-2025")
                        .build())
                .idSchoolYear(1L)
                .typeFees(TypeFees.builder()
                        .id(1L)
                        .designation("scholar charge")
                        .remove(false)
                        .build())
                .remove(false)
                .build();

        List<FeesAllocation> feesAllocations = new ArrayList<>();
        feesAllocations.add(feesAllocation);
        feesAllocations.add(feesAllocation2);
        feesAllocations.add(feesAllocation3);
        List<FeesAllocationDto> result = underTest.entitiesFromDtos(feesAllocations);

        for (int i = 0; i < result.size(); i++) {
            AssertionsForClassTypes.assertThat(feesAllocations.get(i)).usingRecursiveComparison().ignoringFields("id","typeFees").isEqualTo(result.get(i));
            AssertionsForClassTypes.assertThat(feesAllocations.get(i).getTypeFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i).getTypeFees());
            AssertionsForClassTypes.assertThat(feesAllocations.get(i).getSchoolYear()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i).getSchoolYear());
        }


    }
}