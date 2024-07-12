package com.microservice.currenency_converter.proxy;

import com.microservice.currenency_converter.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange", url = "localhost:8001")
@FeignClient(name="currency-exchange", url = "http://localhost:8765/CURRENCY-EXCHANGE/exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion getMethod(
            @PathVariable String from,
            @PathVariable String to
    );
}
