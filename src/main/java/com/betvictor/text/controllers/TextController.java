package com.betvictor.text.controllers;

import com.betvictor.text.services.TextService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @RequestMapping(value = "/v1/text/{start}/{end}/{min_pa}/{max_pa}", method = RequestMethod.GET)
    public ResponseEntity<?> GetRandomText(@PathVariable Integer start, @PathVariable Integer end,
                                           @PathVariable Integer min_pa, @PathVariable Integer max_pa) throws ParseException {


        return textService.GetRandomText(start, end, min_pa, max_pa);

    }

    // history
    @RequestMapping(value = "/v1/history", method = RequestMethod.GET)
    public ResponseEntity<?> GetHistory(){


        return null;

    }
}
