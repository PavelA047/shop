package com.example.shoptest.endpoint;

import com.example.shoptest.dao.GreetingRepository;
import com.example.shoptest.ws.greeting.GetGreetingRequest;
import com.example.shoptest.ws.greeting.GetGreetingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GreetingEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/shoptest/ws/greeting";
    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingEndpoint(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGreetingRequest")
    @ResponsePayload
    public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest
                                                   request) throws DatatypeConfigurationException {
        GetGreetingResponse response = new GetGreetingResponse();
        response.setGreeting(greetingRepository.getGreeting(request.getName()));
        return response;
    }
}

