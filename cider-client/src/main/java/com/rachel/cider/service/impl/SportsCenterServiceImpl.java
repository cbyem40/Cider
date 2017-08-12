package com.rachel.cider.service.impl;

import com.google.gson.Gson;
import com.rachel.cider.service.SportsCenterService;
import com.rachel.cider.vo.RealTimeVisitor;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.http.ssl.SSLContexts.custom;

@Service
public class SportsCenterServiceImpl implements SportsCenterService {

    private RestTemplate restTemplate;

    @Override
    public List <RealTimeVisitor> getRealTimeVisitor() {
        try{
            setupConnection();
        }catch(Exception e){

        }
        List<String> sportsCenterList = Arrays.asList("dasc", "xysc", "ngsc");
        List <RealTimeVisitor> visitors = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        sportsCenterList.forEach(sc -> {
            String url = "https://" + sc + ".cyc.org.tw/api";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            Gson gson = new Gson();
            RealTimeVisitor visitor = gson.fromJson(response.getBody(), RealTimeVisitor.class);
            visitor.setCenter(sc);
            visitors.add(visitor);
        });
        return visitors;
    }

    private void setupConnection() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = custom().loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
    }
}
