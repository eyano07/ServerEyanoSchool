package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dao.SliceFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for the SliceFeesMapper.
 * @see SliceFeesMapper
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
class SliceFeesMapperTest {
    @Autowired
    SliceFeesMapper underTest;
    @Autowired
    SliceFeesRepository sliceFeesRepository;
    /**
     * Test the entityFromDTO method.
     */
    @Test
    void shouldEntityFromDTOTest() {
        SliceFees expected = SliceFees.builder()
                .remove(false)
                .designation("1er Tranche")
                .percentage(30)
                .endDatePayment(LocalDate.now())
                .date(LocalDate.now())
                .date(LocalDate.now())
                .build();

        SliceFeesDto result = underTest.entityFromDTO(expected);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }
    /**
     * Test the dtoFromEntity method.
     */
    @Test
    void shouldDtoFromEntityTest() {
        SliceFeesDto expected = new SliceFeesDto();
        expected.setPercentage(30.0);
        expected.setDesignation("1st Semester");
        expected.setRemove(false);
        expected.setEndDatePayment(LocalDate.now());
        expected.setDatePayment(LocalDate.now());
        expected.setDate(LocalDate.now());

        SliceFees result = underTest.dtoFromEntity(expected);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }
    /**
     * Test the entitiesFromDto method.
     */
    @Test
    void shouldEntitiesFromDtoTest() {
        List<SliceFees> expected = new ArrayList<>();
        expected.add(getEntities("1ere Tranche", 40));
        expected.add(getEntities("2th Tranche", 30));
        expected.add(getEntities("3th Tranche", 30));

        List<SliceFeesDto> result = underTest.entitiesFromDtos(expected);

        for (int i = 0; i < expected.size(); i++) {
            AssertionsForClassTypes.assertThat(expected.get(i)).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i));
        }

    }
    /**
        * Test the entitiesFromDtoPage method.
     */
    @Test
    void shouldEntitiesFromDtoPageTest() {
        List<SliceFees> sliceFeesList = new ArrayList<>();
        sliceFeesRepository.deleteAll();
        sliceFeesList.add(SliceFees.builder()
                .remove(false)
                .datePayment(LocalDate.now())
                .endDatePayment(LocalDate.now())
                .designation("1st Slice")
                .percentage(100)
                .date(LocalDate.now())
                .build());
        sliceFeesList.add(SliceFees.builder()
                .remove(false)
                .designation("2nd Slice")
                .datePayment(LocalDate.now())
                .endDatePayment(LocalDate.now())
                .percentage(100)
                .date(LocalDate.now())
                .build());

        sliceFeesRepository.saveAll(sliceFeesList);
        Map<String,Object> entitiesDtoPage = underTest.entitiesFromDtosPage(
                sliceFeesRepository.findAll(PageRequest.of(0,2)));
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("isLast")).isEqualTo(true);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("hasContent")).isEqualTo(true);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("isEmpty")).isEqualTo(false);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("hasNext")).isEqualTo(false);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("isFist")).isEqualTo(true);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("totalElements")).isEqualTo(2L);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("number")).isEqualTo(0);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("numberOfElements")).isEqualTo(2);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("size")).isEqualTo(2);

        List<SliceFeesDto> sliceFeesDtos = (ArrayList<SliceFeesDto>) entitiesDtoPage.get("contents");

        for (int i = 0; i < sliceFeesDtos.size(); i++) {
            AssertionsForClassTypes.assertThat(sliceFeesDtos.get(i))
                    .usingRecursiveComparison().ignoringFields("id").isEqualTo(sliceFeesList.get(i));
        }

        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("totalPages")).isEqualTo(1);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("hasPrevious")).isEqualTo(false);
        AssertionsForClassTypes.assertThat(entitiesDtoPage.get("toString")).isEqualTo("Page 1 of 1 containing io.eyano.eyanoschool.feesservice.entities.SliceFees instances");
        sliceFeesRepository.deleteAll();
    }
    /**
     * Get a SliceFeesDto object.
     *
     * @param sliceDesignation : String
     * @param slicePercentage  : long
     * @return SliceFeesDto
     */
    private SliceFeesDto getEntitiesDto(String sliceDesignation, double slicePercentage){

        SliceFeesDto sliceFees = new SliceFeesDto();
        sliceFees.setPercentage(slicePercentage);
        sliceFees.setDesignation(sliceDesignation);
        sliceFees.setPercentage(30.0);
        sliceFees.setEndDatePayment(LocalDate.now());
        sliceFees.setDatePayment(LocalDate.now());
        sliceFees.setDesignation("1st Semester");
        sliceFees.setRemove(false);
        sliceFees.setDate(LocalDate.now());

        return sliceFees;
    }
    /**
     * Get a SliceFees object.
     *
     * @param SliceDesignation : String
     * @param SlicePercentage  : long
     * @return SliceFees
     */
    private SliceFees getEntities(String SliceDesignation, long SlicePercentage){

        return SliceFees.builder()
                .remove(false)
                .designation(SliceDesignation)
                .percentage(SlicePercentage)
                .datePayment(LocalDate.now())
                .endDatePayment(LocalDate.now())
                .date(LocalDate.now())
                .build();
    }
}