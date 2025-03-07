package com.thmz.order.product;

import com.thmz.order.exceptions.BussinesException;
import com.thmz.order.order.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    // Without Feign Client
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request){
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(request, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                String.format("%s/purchase",productUrl),
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()){
            throw new BussinesException("An error occurred while processing purchase request" + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }


}
