package com.rishiraj.productservice.client;

import com.rishiraj.productservice.dto.PaymentRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestClient;
@CrossOrigin(origins = "*")
@Service
public class PaymentClientService {
    private RestClient restClient;
    public PaymentClientService() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }
    public String createPayment(String orderId, Long paymentAmount){
        return restClient.post()
                .uri("/payments")
                .body(new PaymentRequestDto(orderId,paymentAmount))
                .retrieve()
                .body(String.class);
    }
}
