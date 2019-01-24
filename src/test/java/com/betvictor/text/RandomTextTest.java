package com.betvictor.text;

import com.betvictor.text.utils.GetRandomText;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RandomTextTest {

    @Autowired
    GetRandomText getRandomText;

    @Test
    public void getRamdonText() throws ParseException {

        System.err.println(getRandomText.CallExternalService(1,2,5));
        //System.err.println(GetRandomText.CallExternalService(1,2,5));
    }

}
