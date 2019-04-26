package com.qiaozhy.standardjava.util;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/26 11:44 AM
 */
@AllArgsConstructor
public abstract class FilterRestTemplate{
    // TODO: 2019/4/26
    @Delegate
    protected volatile RestTemplate restTemplate;

    /*public <T> RestResponseDTO<T> postForEntityWithNoException(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        RestResponseDTO<T> restResponseDTO = new RestResponseDTO<T>();
        ResponseEntity<T> tResponseEntity;
        try {
            tResponseEntity = restTemplate.postForEntity(url, request, responseType, uriVariables);
            restResponseDTO.setData(tResponseEntity.getBody());
            restResponseDTO.setMessage(tResponseEntity.getStatusCode().name());
            restResponseDTO.setStatusCode(tResponseEntity.getStatusCodeValue());
        }catch (Exception e){
            restResponseDTO.setStatusCode(RestResponseDTO.UNKNOWN_ERROR);
            restResponseDTO.setMessage(e.getMessage());
            restResponseDTO.setData(null);
        }
        return restResponseDTO;
    }*/
}
