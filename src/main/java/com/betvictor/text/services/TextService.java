package com.betvictor.text.services;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public interface TextService  {

    // http://www.randomtext.me
    // http://www.randomtext.me/api/giberish/p-3/2-5
    ResponseEntity<?> GetRandomText(Integer p_start, Integer p_end, Integer w_count_min, Integer w_count_max) throws ParseException;
    ResponseEntity<?> GetLast10Reords();
}
