package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.dtos.PaymentRegistrationDto;
import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.Fees;
import io.eyano.eyanoschool.feesservice.entities.PaymentRegistration;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.ClassFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
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

import static io.eyano.eyanoschool.feesservice.enums.PaymentMethode.BANK_CARDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for PaymentRegistrationMapper
 * @see PaymentRegistrationMapper
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
class PaymentRegistrationMapperTest {
    @Autowired
    PaymentRegistrationMapper underTest;

    /**
     * Test method for entityFromDTO
     */
    @Test
    void shouldEntityFromDTOTest() {
        TypeFees typeFees = TypeFees.builder()
                .designation("Academic Fees")
                .remove(false)
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

        PaymentRegistration expected = PaymentRegistration.builder()
                .idCandidate(1L)
                .nameFirstnameCandidate("Gedeon EBAMBA")
                .nameFirstnameUser("Merveille Disashi")
                .amount(56)
                .paymentSystem(PaymentSystem.builder()
                        .paymentMethod(BANK_CARDS)
                        .designation("VISA")
                        .clientName("Idris MANDE")
                        .accountNumberOrPhoneNumber("34567865643219")
                        .clientPhoneNumber("0999999999")
                        .institutionName("UBA")
                        .transactionNumber("45TOFE45RZS")
                        .id(2L)
                        .build())
                .fees(fees)
                .idPaymentSystem(2L)
                .remove(false)
                .date(LocalDate.now())
                .idUser(1L)
                .build();
        PaymentRegistrationDto result = underTest.entityFromDTO(expected);

        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id","fees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(expected.getFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getFees());
    }
    /**
        * Test method for dtoFromEntity
     */
    @Test
    void shouldDtoFromEntityTest() {
        TypeFeesDto typeFees = new TypeFeesDto();
        typeFees.setDesignation("Academic Fees");
        typeFees.setRemove(false);

        FeesDto fees = new FeesDto();
        fees.setRemove(false);
        fees.setAmount(2000.0);
        fees.setClassFees(
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
        fees.setIdClassFess(1L);
        fees.setSchoolYear(
                SchoolYear.builder()
                        .id(1L)
                        .designation("2022-2023")
                        .endDAte(LocalDate.now())
                        .startDate(LocalDate.now())
                        .isCurrent(true)
                        .build()
        );
        fees.setIdSchoolYear(1L);
        fees.setTypeFees(typeFees);

        PaymentRegistrationDto expected = new PaymentRegistrationDto();
        expected.setIdCandidate(1L);
        expected.setNameFirstnameCandidate("Gedeon EBAMBA");
        expected.setNameFirstnameUser("Merveille Disashi");
        expected.setAmount(56.0);
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
        expected.setFees(fees);
        expected.setIdPaymentSystem(2L);
        expected.setRemove(false);
        expected.setDate(LocalDate.now());
        expected.setIdUser(1L);

        PaymentRegistration result = underTest.dtoFromEntity(expected);

        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().ignoringFields("id","fees").isEqualTo(result);
        AssertionsForClassTypes.assertThat(expected.getFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.getFees());

    }
    /**
        * Test method for entitiesFromDto
     */
    @Test
    void shouldEntitiesFromDtoTest() {
        List<PaymentRegistration> expected = new ArrayList<>();
        expected.add(getEntities("Jeancy Bukila","Glody Muya"));
        expected.add(getEntities("Diane Tshingila","Junior Bakolo"));
        expected.add(getEntities("Peto Diagenda","patrick Mbuyi"));

        List<PaymentRegistrationDto> result = underTest.entitiesFromDtos(expected);
        for (int i = 0; i < expected.size(); i++) {
            AssertionsForClassTypes.assertThat(expected.get(i)).usingRecursiveComparison().ignoringFields("id","fees").isEqualTo(result.get(i));
            AssertionsForClassTypes.assertThat(expected.get(i).getFees()).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get(i).getFees());
        }
    }

    /**
     * Get paymentRegistration for the test.
     * @param nameFirstnameCandidate : name and firstname of the candidate
     * @param nameFirstnameUser : name and firstname of the user
     * @return PaymentRegistration
     */
    private PaymentRegistration getEntities(String nameFirstnameCandidate,String nameFirstnameUser) {
        TypeFees typeFees = TypeFees.builder()
                .designation("Academic Fees")
                .remove(false)
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

        return PaymentRegistration.builder()
                .idCandidate(1L)
                .nameFirstnameCandidate(nameFirstnameCandidate)
                .nameFirstnameUser(nameFirstnameUser)
                .amount(56)
                .paymentSystem(PaymentSystem.builder()
                        .paymentMethod(BANK_CARDS)
                        .designation("VISA")
                        .clientName("Idris MANDE")
                        .accountNumberOrPhoneNumber("34567865643219")
                        .clientPhoneNumber("0999999999")
                        .institutionName("UBA")
                        .transactionNumber("45TOFE45RZS")
                        .id(2L)
                        .build())
                .fees(fees)
                .idPaymentSystem(2L)
                .remove(false)
                .date(LocalDate.now())
                .idUser(1L)
                .build();
    }
}