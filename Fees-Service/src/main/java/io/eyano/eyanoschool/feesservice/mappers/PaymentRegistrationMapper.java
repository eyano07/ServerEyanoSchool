package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.clients.CurrencyRestClient;
import io.eyano.eyanoschool.feesservice.dtos.PaymentRegistrationDto;
import io.eyano.eyanoschool.feesservice.entities.PaymentRegistration;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * Mapper class for the entity PaymentRegistration and its DTO PaymentRegistrationDto.
 * @see PaymentRegistrationDto
 * @see PaymentRegistration
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Service @Transactional
@AllArgsConstructor
public class PaymentRegistrationMapper implements Mapper<PaymentRegistrationDto, PaymentRegistration> {
    //CurrencyRestClient currencyRestClient;

    FeesMapper feesMapper;
    ModelMapper modelMapper = new ModelMapper();

    /**
     * Convert a PaymentRegistration entity to a PaymentRegistrationDto
     * @param paymentRegistration : PaymentRegistration entity
     * @return PaymentRegistrationDto
     */
    @Override
    public PaymentRegistrationDto entityFromDTO(PaymentRegistration paymentRegistration) {
        PaymentRegistrationDto paymentRegistrationDto = modelMapper.map(paymentRegistration, PaymentRegistrationDto.class);

        // TODO: 05/02/2024 implement data recovery in the external modules
        //  Class, Registration, User, Finance to complete the method.

        return paymentRegistrationDto;
    }

    /**
     * Convert a PaymentRegistrationDto to a PaymentRegistration entity
     * @param paymentRegistrationDto : PaymentRegistrationDto
     * @return PaymentRegistration
     */
    @Override
    public PaymentRegistration dtoFromEntity(PaymentRegistrationDto paymentRegistrationDto) {
        return modelMapper.map(paymentRegistrationDto, PaymentRegistration.class);
    }
    /**
     * Convert a list of PaymentRegistration entities to a list of PaymentRegistrationDto
     * @param paymentRegistrations : List of PaymentRegistration entities
     * @return List of PaymentRegistrationDto
     */
    @Override
    public List<PaymentRegistrationDto> entitiesFromDtos(List<PaymentRegistration> paymentRegistrations) {
        List<PaymentRegistrationDto> paymentRegistrationDtosList = new ArrayList<>();
        for (PaymentRegistration paymentRegistration : paymentRegistrations) {
            paymentRegistrationDtosList.add(entityFromDTO(paymentRegistration));
        }
        return paymentRegistrationDtosList;
    }
}
