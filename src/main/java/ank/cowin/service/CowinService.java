package ank.cowin.service;

import ank.cowin.model.Session;
import ank.cowin.model.Sessions;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CowinService {

    String COWIN_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){

        HttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    public Sessions getAvailabilityByPin(String pin, String date) throws IOException {
//        Map<String, String> params = new HashMap<>();
//        params.put("pincode", pin);
//        params.put("date", date);
//
//        String url = COWIN_URL + "?pincode="+ pin + "&date="+ date;
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet request = new HttpGet(url);
//        request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "hi_IN");
//        HttpResponse response = httpClient.execute(request);
//        String content = new String(response.getEntity().getContent().readAllBytes());
//        System.out.println("Content: "+ content);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, "hi_IN");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(COWIN_URL)
                .queryParam("pincode", pin)
                .queryParam("date", date);

//        System.out.println("URI:"+ uriBuilder.toUriString());
        HttpEntity entity = new HttpEntity(headers);

//        Sessions sessions = restTemplate.getForObject(
//                uriBuilder.toUriString(),
//                Sessions.class);
        HttpEntity<Sessions> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                Sessions.class
        );
//        for(Session session: response.getBody().getSessions()){
//            System.out.println(session.toString());
//        }
        Sessions sessions = response.getBody();
        System.out.println("Total Sessions:"+ sessions.getSessions().size()+ " for Pin:"+ pin+" on "+ date);
        return sessions;
    }

    public Sessions getAvailableSlotForYoung(String[] pins, String date) throws IOException {
        List<Session> sessions = new ArrayList<>();
        for(String pin : pins){
            Sessions sessForEachPin = getAvailabilityByPin(pin, date);
            sessForEachPin.getSessions().stream().forEach( session -> {
                if(session.getMinAgeLimit()<45 && session.getAvailableCapacity()>0){
                    sessions.add(session);
                }
            });
        }
        Sessions sessionsObj = new Sessions();
        sessionsObj.setSessions(sessions);
        System.out.println("Total Sessions for Young[18-44]:"+ sessionsObj.getSessions().size()+ " on "+ date);
        return sessionsObj;
    }

    public Sessions getAvailableSlotForOld(String[] pins, String date) throws IOException {
        List<Session> sessions = new ArrayList<>();
        for(String pin : pins){
            Sessions sessForEachPin = getAvailabilityByPin(pin, date);
            sessForEachPin.getSessions().stream().forEach( session -> {
                if(session.getMinAgeLimit()==45 && session.getAvailableCapacity()>0){
                    sessions.add(session);
                }
            });
        }
        Sessions sessionsObj = new Sessions();
        sessionsObj.setSessions(sessions);
        System.out.println("Total Sessions for Old[45+]:"+ sessionsObj.getSessions().size()+ " on "+ date);
        return sessionsObj;
    }
}
