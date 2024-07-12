package com.microservice.currenency_converter.controller;

import com.microservice.currenency_converter.bean.CurrencyConversion;
import com.microservice.currenency_converter.bean.Demo;
import com.microservice.currenency_converter.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/conversion")
public class CurrencyConverterController {
    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConverter(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){
        HashMap<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurrencyConversion> currencyConversion = new RestTemplate().getForEntity("http://localhost:8765/CURRENCY-EXCHANGE/exchange/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class,uriVariables);
        return new CurrencyConversion(currencyConversion.getBody().getId(), from,to,currencyConversion.getBody().getConversionMultiple(),quantity,quantity.multiply(currencyConversion.getBody().getConversionMultiple()),currencyConversion.getBody().getEnvironment()+" "+"Rest Template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConverterFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ){
        CurrencyConversion currencyConversion = proxy.getMethod(from,to);
        return new CurrencyConversion(currencyConversion.getId(), from,to,currencyConversion.getConversionMultiple(),quantity,quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment()+" "+"feign");
    }
}
