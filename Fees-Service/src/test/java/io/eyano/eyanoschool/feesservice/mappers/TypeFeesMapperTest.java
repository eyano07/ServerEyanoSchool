package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for the TypeFeesMapper.
 * @see TypeFeesMapper
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
class TypeFeesMapperTest {
    @Autowired
    TypeFeesMapper underTest;
    @Autowired
    TypeFeesRepository typeFeesRepository;

    /**
     * Test the entityFromDTO method.
     */
    @Test
    void shouldEntityFromDTOTest() {
        TypeFees expected = TypeFees.builder()
                .designation("Academic Fees")
                .remove(false)
                .build();

        TypeFeesDto result = underTest.entityFromDTO(expected);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }

    @Test
    void shouldDtoFromEntityTest() {
        TypeFeesDto expected = new TypeFeesDto();
        expected.setDesignation("Academic Fees");
        expected.setRemove(false);

        TypeFees result = underTest.dtoFromEntity(expected);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }

    @Test
    void shouldEntitiesFromDtoTest() {
        List<TypeFees> expected = new ArrayList<>();
        expected.add(getEntities("Academic fees"));
        expected.add(getEntities("state fees"));
        expected.add(getEntities("manual labor costs"));

        List<TypeFeesDto> result = underTest.entitiesFromDtos(expected);
        for (int i = 0; i < expected.size(); i++) {
            AssertionsForClassTypes.assertThat(expected.get(i)).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i));
        }
    }

    @Test
    void shouldEntitiesFromDtoPageTest() {
        typeFeesRepository.deleteAll();

        List<TypeFees> typeFeesList = new ArrayList<>();
        typeFeesList.add(getEntities("Academic fees"));
        typeFeesList.add(getEntities("state fees"));
        typeFeesList.add(getEntities("manual labor costs"));
        typeFeesRepository.saveAll(typeFeesList);
        Map<String,Object> expected = underTest.entitiesFromDtosPage(typeFeesRepository.findAll(PageRequest.of(0,3)));
        List<TypeFeesDto> typeFees = typeFeesRepository.findAll().stream().map(underTest::entityFromDTO).toList();
        System.err.println(expected);
        AssertionsForClassTypes.assertThat(expected).isNotNull();
        AssertionsForClassTypes.assertThat(expected.get("isLast").toString()).isEqualTo("true");
        AssertionsForClassTypes.assertThat(expected.get("hasContent").toString()).isEqualTo("true");
        AssertionsForClassTypes.assertThat(expected.get("isEmpty").toString()).isEqualTo("false");
        AssertionsForClassTypes.assertThat(expected.get("hasNext").toString()).isEqualTo("false");
        AssertionsForClassTypes.assertThat(expected.get("isFirst").toString()).isEqualTo("true");
        AssertionsForClassTypes.assertThat(expected.get("totalElements").toString()).isEqualTo("3");
        AssertionsForClassTypes.assertThat(expected.get("number").toString()).isEqualTo("0");
        AssertionsForClassTypes.assertThat(expected.get("numberOfElements").toString()).isEqualTo("3");
        AssertionsForClassTypes.assertThat(expected.get("size").toString()).isEqualTo("3");
        AssertionsForClassTypes.assertThat(expected.get("contents")).usingRecursiveComparison().isEqualTo(typeFees);
        AssertionsForClassTypes.assertThat(expected.get("totalPages").toString()).isEqualTo("1");
        AssertionsForClassTypes.assertThat(expected.get("hasPrevious").toString()).isEqualTo("false");
        AssertionsForClassTypes.assertThat(expected.get("toString").toString()).isEqualTo("Page 1 of 1 containing io.eyano.eyanoschool.feesservice.entities.TypeFees instances");

        typeFeesRepository.deleteAll();
    }

    /**
     * Get TypeFees entities for testing
     * @param typeFeesDesignation : TypeFees designation
     * @return TypeFees
     */
    private TypeFees getEntities(String typeFeesDesignation){
        return TypeFees.builder()
                .designation(typeFeesDesignation)
                .remove(false)
                .build();
    }
}