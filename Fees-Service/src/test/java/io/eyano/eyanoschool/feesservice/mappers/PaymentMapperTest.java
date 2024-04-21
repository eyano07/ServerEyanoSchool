package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dao.FeesRepository;
import io.eyano.eyanoschool.feesservice.dao.PaymentRepository;
import io.eyano.eyanoschool.feesservice.dao.SliceFeesRepository;
import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.dtos.PaymentDto;
import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.Fees;
import io.eyano.eyanoschool.feesservice.entities.Payment;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.ClassFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import io.eyano.eyanoschool.feesservice.entitiesExt.Pupil;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
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

import static io.eyano.eyanoschool.feesservice.enums.PaymentMethode.BANK_CARDS;
/**
 * Test class for the PaymentMapper class.
 * @see PaymentMapper
 * @version 1.0
 * @since 2021-04-18, Sun
 * @author Pascal Tshingila
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
class PaymentMapperTest {
    @Autowired
    PaymentMapper underTest;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TypeFeesRepository typeFeesRepository;
    @Autowired
    SliceFeesRepository sliceFeesRepository;
    @Autowired
    FeesRepository feesRepository;

    @Test
    void shouldEntityFromDTOTest() {
        TypeFees typeFees = TypeFees.builder()
                .designation("Academic Fees")
                .remove(false)
                .build();

        SliceFees sliceFees = SliceFees.builder()
                .remove(false)
                .designation("1er Tranche")
                .percentage(30)
                .date(LocalDate.now())
                .id(1L)
                .build();

        Fees fees = Fees.builder()
                .remove(false)
                .amount(2000)
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

        Payment expected = Payment.builder()
                .remove(false)
                .amount(35)
                .sliceFees(sliceFees)
                .pupil(
                        Pupil.builder()
                                .birthDate(LocalDate.now())
                                .firstname("Biga")
                                .gender('M')
                                .lastname("KIMA")
                                .name("Samuele")
                                .phone("5679876R78")
                                .registration("")
                                .tutorPhone("4567898")
                                .build()
                )
                .idPupil(1L)
                .date(LocalDate.now())
                .idPaymentSystem(2L)
                .paymentSystem(PaymentSystem.builder()
                        .paymentMethod(BANK_CARDS)
                        .designation("VISA")
                        .clientName("Idris MANDE")
                        .accountNumberOrPhoneNumber("34567865643219")
                        .clientPhoneNumber("0999999999")
                        .institutionName("UBA")
                        .transactionNumber("45TOFE45RZS")
                        .id(2L)
                        .build()
                )
                .nameFirstnameUser("Merveille Disashi")
                .idUser(1L)
                .fees(fees)
                .build();

        PaymentDto result = underTest.entityFromDTO(expected);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id","fees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(expected.getFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getFees());

    }

    @Test
    void shouldDtoFromEntityTest() {
        TypeFeesDto typeFeesDto = new TypeFeesDto();
        typeFeesDto.setDesignation("Academic Fees");
        typeFeesDto.setRemove(false);

        SliceFeesDto sliceFeesDto = new SliceFeesDto();
        sliceFeesDto.setPercentage(30.0);
        sliceFeesDto.setDesignation("1st Semester");
        sliceFeesDto.setRemove(false);
        sliceFeesDto.setDate(LocalDate.now());

        FeesDto feesDto = new FeesDto();
        feesDto.setRemove(false);
        feesDto.setAmount(2000.0);
        feesDto.setClassFees(
                ClassFees.builder()
                        .id(1L)
                        .local("B")
                        .ability(50)
                        .designation("6th")
                        .cycle("Primary")
                        .nameFirstnameFullProfessor("Francy MAYALA")
                        .idFullProfessor(1L)
                        .build()
        );
        feesDto.setIdClassFess(1L);
        feesDto.setSchoolYear(
                SchoolYear.builder()
                        .id(1L)
                        .designation("2022-2023")
                        .endDAte(LocalDate.now())
                        .startDate(LocalDate.now())
                        .isCurrent(true)
                        .build()
        );
        feesDto.setIdSchoolYear(1L);
        feesDto.setTypeFees(typeFeesDto);

        PaymentDto expected = new PaymentDto();
        expected.setRemove(false);
        expected.setAmount(35.0);
        expected.setSliceFees(sliceFeesDto);
        expected.setPupil(
                Pupil.builder()
                        .birthDate(LocalDate.now())
                        .firstname("Biga")
                        .gender('M')
                        .lastname("KIMA")
                        .name("Samuel")
                        .phone("5679876R78")
                        .registration("")
                        .tutorPhone("4567898")
                        .build()
        );
        expected.setIdPupil(1L);
        expected.setDate(LocalDate.now());
        expected.setIdPaymentSystem(2L);
        expected.setPaymentSystem(PaymentSystem.builder()
                .paymentMethod(BANK_CARDS)
                .designation("VISA")
                .clientName("Idris MANDE")
                .accountNumberOrPhoneNumber("34567865643219")
                .clientPhoneNumber("0999999999")
                .institutionName("UBA")
                .transactionNumber("45TOFE45RZS")
                .id(2L)
                .build());
        expected.setNameFirstnameUser("Merveille Disashi");
        expected.setIdUser(1L);
        expected.setFees(feesDto);

        Payment result = underTest.dtoFromEntity(expected);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id","fees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(expected.getFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getFees());

    }

    @Test
    void shouldEntitiesFromDtoTest() {
        List<Payment> expected = new ArrayList<>();
        expected.add(getEntities(1L,"Glody","Bosasi Franck", "Merveille Disashi"));
        expected.add(getEntities(2L,"Diane Tshingila","Junior","Kabongo Arnold"));
        expected.add(getEntities(3L,"Peto Diagenda","patrick","Augustin Kabuya"));

        List<PaymentDto> result = underTest.entitiesFromDtos(expected);

        for (int i = 0; i < result.size(); i++) {
            AssertionsForClassTypes.assertThat(expected.get(i)).usingRecursiveComparison().ignoringFields("id","fees").isEqualTo(result.get(i));
            AssertionsForClassTypes.assertThat(expected.get(i).getFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i).getFees());
        }

    }

    @Test
    void shouldEntitiesFromDtoPageTest() {
        paymentRepository.deleteAll();
        feesRepository.deleteAll();
        typeFeesRepository.deleteAll();
        sliceFeesRepository.deleteAll();

        List<Payment> payments = new ArrayList<>();
        payments.add(getEntities(1L,"Jeancy Bukila","Glody","Bosasi Franck"));
        payments.add(getEntities(2L,"Diane Tshingila","Junior","Kabongo Arnold"));
        payments.add(getEntities(3L,"Peto Diagenda","patrick","Augustin Kabuya"));
        paymentRepository.saveAll(payments);

        Map<String, Object> expected = underTest.entitiesFromDtoPage(
                paymentRepository.findAll(PageRequest.of(0, 3)
                ));

        List<PaymentDto> paymentDtoList = paymentRepository.findAll().stream().map(underTest::entityFromDTO).toList();

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
        AssertionsForClassTypes.assertThat(expected.get("contents")).usingRecursiveComparison().isEqualTo(paymentDtoList);
        AssertionsForClassTypes.assertThat(expected.get("totalPages").toString()).isEqualTo("1");
        AssertionsForClassTypes.assertThat(expected.get("hasPrevious").toString()).isEqualTo("false");
        AssertionsForClassTypes.assertThat(expected.get("toString").toString()).isEqualTo("Page 1 of 1 containing io.eyano.eyanoschool.feesservice.entities.Payment instances");

        paymentRepository.deleteAll();
        feesRepository.deleteAll();
        typeFeesRepository.deleteAll();
        sliceFeesRepository.deleteAll();

    }

    /**
     * Get entities for the test.
     * @param nameFirstnameUser : Name and firstname of the user
     * @param pupilName : Name of the pupil
     * @param nameFirstnameFullProfessor : Name and firstname of the full professor
     * @return Payment
     */
    private Payment getEntities(Long idPs,String nameFirstnameUser, String pupilName, String nameFirstnameFullProfessor){
        TypeFees typeFees = typeFeesRepository.save(TypeFees.builder()
                .designation("Academic Fees")
                .remove(false)
                .build());

        SliceFees sliceFees = sliceFeesRepository.save(SliceFees.builder()
                .remove(false)
                .designation("1er Tranche")
                .percentage(30)
                .date(LocalDate.now())
                .endDatePayment(LocalDate.now())
                .datePayment(LocalDate.now())
                .id(1L)
                .build());

        Fees fees = feesRepository.save(Fees.builder()
                .designation("Academic Fees")
                .remove(false)
                .amount(2000)
                .classFees(
                        ClassFees.builder()
                                .id(1L)
                                .local("B")
                                .ability(50)
                                .designation("6th")
                                .cycle("Primary")
                                .nameFirstnameFullProfessor(nameFirstnameFullProfessor)
                                .idFullProfessor(1L)
                                .build()
                )
                .idClassFess(1L)
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
                .build());

        return Payment.builder()
                .remove(false)
                .amount(35)
                .sliceFees(sliceFees)
                .pupil(
                        Pupil.builder()
                                .birthDate(LocalDate.now())
                                .firstname("Biga")
                                .gender('M')
                                .lastname("KIMA")
                                .name(pupilName)
                                .phone("5679876R78")
                                .registration("")
                                .tutorPhone("4567898")
                                .build()
                )
                .idPupil(1L)
                .date(LocalDate.now())
                .idPaymentSystem(idPs)
                .paymentSystem(PaymentSystem.builder()
                        .paymentMethod(BANK_CARDS)
                        .designation("VISA")
                        .clientName("Idris MANDE")
                        .accountNumberOrPhoneNumber("34567865643219")
                        .clientPhoneNumber("0999999999")
                        .institutionName("UBA")
                        .transactionNumber("45TOFE45RZS")
                        .id(2L)
                        .build()
                )
                .nameFirstnameUser(nameFirstnameUser)
                .idUser(1L)
                .fees(fees)
                .build();
    }

}