package com.betvictor.text.utils;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class GetRandomText {

    private static final String base_url = "http://www.randomtext.me/api/giberish/";

    public static String CallExternalService(Integer num_parag, Integer mini, Integer maxi) throws ParseException {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new  RestTemplate();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        JSONParser parser = new JSONParser();

        StringBuffer str_url= new StringBuffer();

        str_url.append(base_url).append("p-").append(num_parag.toString())
                .append("/").append(mini.toString()).append("-")
                .append(maxi.toString());

        System.out.println(str_url);


        ResponseEntity<String> result = restTemplate.exchange(str_url.toString(), HttpMethod.GET,entity, String.class);
        //System.out.println(result.getBody());
        //System.out.println(result.toString());
        JSONObject json = (JSONObject) parser.parse(result.getBody());

        String text = Jsoup.parse(String.valueOf(json.get("text_out"))).text();
        System.out.println(text);
        return text;
    }
}
