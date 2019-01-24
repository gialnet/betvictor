package com.betvictor.text.services;

import com.betvictor.text.services.TextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class TextServiceImpl implements TextService {

    Map<String, Object> response = new HashMap<String, Object>();

    @Override
    public ResponseEntity<?> GetRandomText(Integer p_start, Integer p_end, Integer w_count_min, Integer w_count_max) {

        Integer freq_word=0;
        Integer avg_paragraph_size=0;
        Integer avg_paragraph_processing_time=0;
        Integer total_processing_time=0;

        /*Response (JSON):
        {
            "freq_word": <most_frequent_word>
                "avg_paragraph_size":<avg_paragraph_size>
                "avg_paragraph_processing_time": <avg_paragraph_processing_time>
                "total_processing_time": <total_processing_time>
        }*/

        response.clear();
        response.put("freq_word", freq_word );
        response.put("avg_paragraph_size", avg_paragraph_size );
        response.put("avg_paragraph_processing_time", avg_paragraph_processing_time );
        response.put("total_processing_time", total_processing_time );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
