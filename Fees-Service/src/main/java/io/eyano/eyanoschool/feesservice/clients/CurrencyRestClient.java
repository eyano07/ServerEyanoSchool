package io.eyano.eyanoschool.feesservice.clients;

import io.eyano.eyanoschool.feesservice.entitiesExt.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * The CurrencyRestClient class allows us to communicate with the Financial-service module to retrieve the Currency entity.
 * The Currency entity is used to store the currency of the fees.
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 * @see Currency
 */
@FeignClient(name = "FINANCIAL-SERVICE")
public interface CurrencyRestClient {
    /**
     * Get the currency by its identifier
     * @param id : Long
     * @return Currency
     */
    @GetMapping("/api/currency/{id}")
    public Currency getById(@PathVariable("id") Long id);
}
