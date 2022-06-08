package com.example.shoptest.dao;

import com.example.shoptest.ws.greeting.Greeting;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

@Component
public class GreetingRepository {
    public Greeting getGreeting(String name) throws
            DatatypeConfigurationException {
        Assert.notNull(name, "name can't be null");
        Greeting greeting = new Greeting();
        greeting.setText(String.format("Hello, %s", name));
        greeting.setDate(DatatypeFactory.newInstance().
                newXMLGregorianCalendar(new GregorianCalendar()));
        return greeting;
    }
}

