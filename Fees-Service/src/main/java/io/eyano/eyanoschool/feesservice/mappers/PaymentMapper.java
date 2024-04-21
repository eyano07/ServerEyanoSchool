package io.eyano.eyanoschool.feesservice.mappers;

import io.eyano.eyanoschool.feesservice.clients.CurrencyRestClient;
import io.eyano.eyanoschool.feesservice.clients.PaymentSystemRestClient;
import io.eyano.eyanoschool.feesservice.dao.FeesRepository;
import io.eyano.eyanoschool.feesservice.dao.SliceFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.PaymentDto;
import io.eyano.eyanoschool.feesservice.entities.Payment;
import io.eyano.eyanoschool.feesservice.entitiesExt.Currency;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Mapper class for the entity Payment and its DTO PaymentDto.
 * @author : Pascal Tshingila
 * @see PaymentDto
 * @see Payment
 * @see PaymentMapper
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Service @Transactional
@AllArgsConstructor
public class PaymentMapper implements Mapper<PaymentDto, Payment> {
    //PaymentSystemRestClient paymentSystemRestClient;
    //CurrencyRestClient currencyRestClient;
    ModelMapper modelMapper = new ModelMapper();
    SliceFeesRepository sliceFeesRepository;
    FeesRepository feesRepository;
    FeesMapper feesMapper;
    SliceFeesMapper sliceFeesMapper;

    /**
     * Convert a Payment entity to a PaymentDto
     * @param payment : Payment entity
     * @return PaymentDto
     */
    @Override
    public PaymentDto entityFromDTO(Payment payment) {
        PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);

//        PaymentSystem paymentSystem = paymentSystemRestClient.getById(paymentDto.getIdPaymentSystem());
//        paymentDto.setPaymentSystem(paymentSystem);
//        Currency currency = currencyRestClient.getById(paymentDto.getIdCurrency());
//        paymentDto.setCurrency(currency);
        // TODO: 04/02/2024 implement data recovery in the external modules
        //  Pupil, User to complete the method.

        return paymentDto;
    }
    /**
     * Convert a PaymentDto to a Payment entity
     * @param paymentDto : PaymentDto
     * @return Payment
     */
    @Override
    public Payment dtoFromEntity(PaymentDto paymentDto) {
        return modelMapper.map(paymentDto, Payment.class);
    }
    /**
     * Convert a list of PaymentDto to a list of Payment entities
     * @param payments : List of Payment entities
     * @return List of Payment entities
     */
    @Override
    public List<PaymentDto> entitiesFromDtos(List<Payment> payments) {
        List<PaymentDto> paymentDtoList = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentDto paymentDto = entityFromDTO(payment);
            paymentDtoList.add(paymentDto);
        }
        return paymentDtoList;
    }

    /**
     * Convert a Page of Payment entities to a Map of PaymentDto entities
     * @param paymentsPage : Page of Payment entities
     * @return Map of PaymentDto entities
     */
    public Map<String, Object> entitiesFromDtoPage(Page<Payment> paymentsPage){
        Map<String, Object> map = new java.util.HashMap<>();
        List<Payment> payments = paymentsPage.getContent();
        List<PaymentDto> contents = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDto paymentDto = entityFromDTO(payment);
            contents.add(paymentDto);
        }

        map.put("contents", contents);
        map.put("toString", paymentsPage.toString());
        map.put("isLast", paymentsPage.isLast());
        map.put("isFirst", paymentsPage.isFirst());
        map.put("isEmpty", paymentsPage.isEmpty());
        map.put("hasContent", paymentsPage.hasContent());
        map.put("hasNext", paymentsPage.hasNext());
        map.put("hasPrevious", paymentsPage.hasPrevious());
        map.put("number", paymentsPage.getNumber());
        map.put("numberOfElements", paymentsPage.getNumberOfElements());
        map.put("size", paymentsPage.getSize());
        map.put("currentPage", paymentsPage.getNumber());
        map.put("totalElements", paymentsPage.getTotalElements());
        map.put("totalPages", paymentsPage.getTotalPages());
        return map;
    }
}
