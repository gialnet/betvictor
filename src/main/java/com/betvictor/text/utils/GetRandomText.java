package com.betvictor.text.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
        //System.out.println(text);
        return text;
    }

    /**
     *
     * @param num_parag
     * @param mini
     * @param maxi
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("Duplicates")
    public String MostFrequentWord(Integer num_parag, Integer mini, Integer maxi) throws ParseException {

        String mfw="";

        long startTime = System.nanoTime();

        StringTokenizer tokens = new StringTokenizer(CallExternalService(num_parag,mini,maxi));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        Long convert = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);

        //System.err.println(tokens.countTokens());
        Integer paragraph_size = tokens.countTokens();

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; tokens.hasMoreTokens(); i++) {

            String s = tokens.nextToken();
            if (!map.containsKey(s)) {  // first time we've seen this string
                map.put(s, 1);
            }
            else {
                int count = map.get(s);
                map.put(s, count + 1);
            }
        }

        int maxValueInMap=(Collections.max(map.values()));  // This will return max value in the Hashmap
        //System.out.println(maxValueInMap);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                //System.out.println(entry.getKey());     // Print the key with max value
                mfw=entry.getKey();
            }
        }

        mfw=mfw+","+paragraph_size.toString()+","+convert.toString();
        return mfw;
    }

    @SuppressWarnings("Duplicates")
    public String MostFrequentWord(String ListWords) throws ParseException {

        String mfw="";

        StringTokenizer tokens = new StringTokenizer(ListWords);

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; tokens.hasMoreTokens(); i++) {

            String s = tokens.nextToken();
            if (!map.containsKey(s)) {  // first time we've seen this string
                map.put(s, 1);
            }
            else {
                int count = map.get(s);
                map.put(s, count + 1);
            }
        }

        int maxValueInMap=(Collections.max(map.values()));  // This will return max value in the Hashmap

        for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {

                mfw=entry.getKey();
            }
        }

        return mfw;
    }
}
