package com.betvictor.text;

import com.betvictor.text.utils.GetRandomText;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RandomTextTest {

    @Autowired
    GetRandomText getRandomText;

    @Test
    public void getRamdonText() throws ParseException {

        System.err.println(getRandomText.CallExternalService(3,5,25));
        //System.err.println(GetRandomText.CallExternalService(1,2,5));
    }

    @Test
    public void TokenizeTest() throws ParseException {
        StringTokenizer tokens = new StringTokenizer(getRandomText.CallExternalService(3,5,25));
        System.err.println(tokens.countTokens());
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
        System.out.println(maxValueInMap);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
            }
        }
    }

}
