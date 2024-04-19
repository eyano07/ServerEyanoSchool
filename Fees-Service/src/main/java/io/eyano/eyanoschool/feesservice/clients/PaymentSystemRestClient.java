package io.eyano.eyanoschool.feesservice.clients;

import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
/**
 * The PaymentSystemRestClient class allows us to communicate with the Financial-service module to retrieve the PaymentSystem entity.
 * The PaymentSystem entity is used to store the payment system of the fees.
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 * @see PaymentSystem
 */
@FeignClient(name = "FINANCIAL-SERVICE")
public interface PaymentSystemRestClient {
    /**
     * Get the payment system by its identifier
     * @param id : Long
     * @return PaymentSystem
     */
    @GetMapping("/api/paymentSystems/{id}")
    public PaymentSystem getById(@PathVariable("id") Long id);

}
