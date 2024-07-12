package com.microservice.currenency_exchange.bean;

public class Demo {
    private String from;
    private String to;

    public Demo() {
    }

    public Demo(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}