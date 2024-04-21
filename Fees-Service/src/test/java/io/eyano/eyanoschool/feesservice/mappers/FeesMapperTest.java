package io.eyano.eyanoschool.feesservice.mappers;
/**
 * Test class for FeesMapper
 * @see FeesMapper
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */

import io.eyano.eyanoschool.feesservice.dao.*;
import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.Fees;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.ClassFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
class FeesMapperTest {
    @Autowired
    FeesMapper underTest;
    @Autowired
    FeesRepository feesRepository;
    @Autowired
    SliceFeesRepository sliceFeesRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentRegistrationRepository paymentRegistrationRepository;
    @Autowired
    TypeFeesRepository typeFeesRepository;

    /**
     * Test method for entityFromDTO
     */
    @Test
    void shouldEntityFromDTOTest() {
        TypeFees typeFees = TypeFees.builder()
                .id(1L)
                .designation("Academic Fees")
                .remove(false)
                .build();

        SliceFees sliceFees = SliceFees.builder()
                .id(1L)
                .remove(false)
                .designation("1er Tranche")
                .percentage(30)
                .date(LocalDate.now())
                .build();

        Fees fees = Fees.builder()
                .id(1L)
                .remove(false)
                .amount(2000)
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();

        FeesDto result = underTest.entityFromDTO(fees);

        AssertionsForClassTypes.assertThat(fees).usingRecursiveComparison().ignoringFields("id","typeFees","sliceFees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(fees.getTypeFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getTypeFees());
        AssertionsForClassTypes.assertThat(fees.getSliceFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getSliceFees());
    }

    @Test
    void shouldDtoFromEntityTest() {
        SliceFeesDto sliceFeesDto = new SliceFeesDto();
        sliceFeesDto.setPercentage(30.0);
        sliceFeesDto.setDesignation("1st Semester");
        sliceFeesDto.setRemove(false);
        sliceFeesDto.setDate(LocalDate.now());

        TypeFeesDto typeFeesDto = new TypeFeesDto();
        typeFeesDto.setRemove(false);
        typeFeesDto.setDesignation("Scholar fees");

        FeesDto feesDto = new FeesDto();
        feesDto.setAmount(200.0);
        feesDto.setTypeFees(typeFeesDto);
        feesDto.setSchoolYear(SchoolYear.builder()
                .id(1L)
                .designation("2022-2023")
                .endDAte(LocalDate.now())
                .startDate(LocalDate.now())
                .isCurrent(true)
                .build());
        feesDto.setIdSchoolYear(1L);
        feesDto.setClassFees(ClassFees.builder()
                .idFullProfessor(1L)
                .nameFirstnameFullProfessor("BIGA KIMA")
                .cycle("Primary")
                .designation("2nd")
                .local("B2")
                .ability(35)
                .build());
        feesDto.setSliceFees(sliceFeesDto);
        feesDto.setIdClassFess(1L);
        feesDto.setRemove(false);

        Fees result = underTest.dtoFromEntity(feesDto);

        AssertionsForClassTypes.assertThat(feesDto).usingRecursiveComparison().ignoringFields("id","typeFees","sliceFees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(feesDto.getTypeFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getTypeFees());
        AssertionsForClassTypes.assertThat(feesDto.getSliceFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getSliceFees());
    }

    @Test
    void shouldEntitiesFromDtoTest() {
        TypeFees typeFees = TypeFees.builder()
                .id(1L)
                .designation("Academic Fees")
                .remove(false)
                .build();

        SliceFees sliceFees = SliceFees.builder()
                .id(1L)
                .remove(false)
                .designation("1er Tranche")
                .percentage(30)
                .date(LocalDate.now())
                .build();


        Fees fees = Fees.builder()
                .id(1L)
                .remove(false)
                .amount(5000)
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("B")
                                .ability(50)
                                .designation("7th")
                                .cycle("Secondary")
                                .nameFirstnameFullProfessor("Nathaly Mbungo")
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .schoolYear(
                        SchoolYear.builder()
                                .id(1L)
                                .designation("2022-2023")
                                .endDAte(LocalDate.now())
                                .startDate(LocalDate.now())
                                .isCurrent(true)
                                .build()
                )
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();

        Fees fees2 = Fees.builder()
                .id(1L)
                .remove(false)
                .amount(4500)
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("C")
                                .ability(50)
                                .designation("2nd")
                                .cycle("Primary")
                                .nameFirstnameFullProfessor("Francy MAYALA")
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .schoolYear(
                        SchoolYear.builder()
                                .id(1L)
                                .designation("2022-2023")
                                .endDAte(LocalDate.now())
                                .startDate(LocalDate.now())
                                .isCurrent(true)
                                .build()
                )
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();

        Fees fees3 = Fees.builder()
                .id(1L)
                .remove(false)
                .amount(2000)
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("B")
                                .ability(50)
                                .designation("6th")
                                .cycle("Primary")
                                .nameFirstnameFullProfessor("Biga kima")
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .schoolYear(
                        SchoolYear.builder()
                                .id(1L)
                                .designation("2022-2023")
                                .endDAte(LocalDate.now())
                                .startDate(LocalDate.now())
                                .isCurrent(true)
                                .build()
                )
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();
        List<Fees> expected = new ArrayList<>();
        expected.add(fees);
        expected.add(fees2);
        expected.add(fees3);

        List<FeesDto> result = underTest.entitiesFromDtos(expected);

        for (int i = 0; i < expected.size(); i++) {
            AssertionsForClassTypes.assertThat(expected.get(i)).usingRecursiveComparison().ignoringFields("id","typeFees","sliceFees").isEqualTo(result.get(i));
            AssertionsForClassTypes.assertThat(expected.get(i).getTypeFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i).getTypeFees());
            AssertionsForClassTypes.assertThat(expected.get(i).getSliceFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i).getSliceFees());
        }
    }

    @Test
    void shouldEntitiesFromDtoPageTest() {
        paymentRepository.deleteAll();
        paymentRegistrationRepository.deleteAll();
        feesRepository.deleteAll();
        sliceFeesRepository.deleteAll();

        TypeFees typeFees = typeFeesRepository.save(TypeFees.builder()
                .designation("Academic Fees")
                .remove(false)
                .build());


        SliceFees sliceFees = sliceFeesRepository.save(SliceFees.builder()
                .remove(false)
                .designation("1er Tranche")
                .percentage(30)
                .date(LocalDate.now())
                .datePayment(LocalDate.now())
                .endDatePayment(LocalDate.now())
                .build());


        Fees fees1 = Fees.builder()
                .remove(false)
                .amount(5000)
                .designation("1st Semester")
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("B")
                                .ability(50)
                                .designation("7th")
                                .cycle("Secondary")
                                .nameFirstnameFullProfessor("Nathaly Mbungo")
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .schoolYear(
                        SchoolYear.builder()
                                .id(1L)
                                .designation("2022-2023")
                                .endDAte(LocalDate.now())
                                .startDate(LocalDate.now())
                                .isCurrent(true)
                                .build()
                )
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();

        Fees fees2 = Fees.builder()
                .remove(false)
                .amount(4500)
                .designation("2nd Semester")
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("C")
                                .ability(50)
                                .designation("2nd")
                                .cycle("Primary")
                                .nameFirstnameFullProfessor("Francy MAYALA")
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .schoolYear(
                        SchoolYear.builder()
                                .id(1L)
                                .designation("2022-2023")
                                .endDAte(LocalDate.now())
                                .startDate(LocalDate.now())
                                .isCurrent(true)
                                .build()
                )
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();

        Fees fees3 = Fees.builder()
                .remove(false)
                .amount(2000)
                .designation("3rd Semester")
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("B")
                                .ability(50)
                                .designation("6th")
                                .cycle("Primary")
                                .nameFirstnameFullProfessor("Francy MAYALA")
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
                .sliceFees(sliceFees)
                .schoolYear(
                        SchoolYear.builder()
                                .id(1L)
                                .designation("2022-2023")
                                .endDAte(LocalDate.now())
                                .startDate(LocalDate.now())
                                .isCurrent(true)
                                .build()
                )
                .idSchoolYear(1L)
                .typeFees(typeFees)
                .build();
        List<Fees> feesList = new ArrayList<>();
        feesList.add(fees1);
        feesList.add(fees2);
        feesList.add(fees3);

        feesRepository.saveAll(feesList);
        Page<Fees> feesPage = feesRepository.findAll(PageRequest.of(0, 3));
        Map<String,Object> expected = underTest.entitiesFromDtoPage(feesPage);


        List<FeesDto> feesDtoList = feesRepository.findAll().stream().map(underTest::entityFromDTO).toList();

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
        AssertionsForClassTypes.assertThat(expected.get("contents")).usingRecursiveComparison().ignoringFields("id").isEqualTo(feesDtoList);
        AssertionsForClassTypes.assertThat(expected.get("totalPages").toString()).isEqualTo("1");
        AssertionsForClassTypes.assertThat(expected.get("hasPrevious").toString()).isEqualTo("false");
        AssertionsForClassTypes.assertThat(expected.get("toString").toString()).isEqualTo("Page 1 of 1 containing io.eyano.eyanoschool.feesservice.entities.Fees instances");

        paymentRepository.deleteAll();
        paymentRegistrationRepository.deleteAll();
        feesRepository.deleteAll();
        sliceFeesRepository.deleteAll();
    }
}