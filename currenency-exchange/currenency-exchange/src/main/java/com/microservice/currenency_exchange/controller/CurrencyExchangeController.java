package com.microservice.currenency_exchange.controller;

import com.microservice.currenency_exchange.bean.CurrencyExchange;
import com.microservice.currenency_exchange.bean.Demo;
import com.microservice.currenency_exchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    private HashMap<String,Double> map = new HashMap<>();
    @Autowired
    private Environment environment;

//    @PostMapping("/currency-exchange")
//    public CurrencyExchange getExchangeValue(
//            @RequestBody Demo demo
//    ){
//        System.out.println("inside currency exchange");
//        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(demo.getFrom(), demo.getTo());
//        if(currencyExchange == null){
//            throw new RuntimeException("From and to doesnt exist");
//        }
//        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
//        return currencyExchange;
//    }

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getMethod(
            @PathVariable String from,
            @PathVariable String to

    ){
          map.put("USD-INR",85.0);
          map.put("EUR-INR",75.0);
//        System.out.println(user);
          double ans = map.get(from+"-"+to);
          CurrencyExchange currencyExchange = new CurrencyExchange(from=="USD" ? 1 : 2,from,to, BigDecimal.valueOf(ans));
        if(currencyExchange == null){
            throw new RuntimeException("From and to doesnt exist");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }


}