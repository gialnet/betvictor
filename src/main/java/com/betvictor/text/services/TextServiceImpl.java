package com.betvictor.text.services;

import com.betvictor.text.data.object.DataStatics;
import com.betvictor.text.utils.GetRandomText;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class TextServiceImpl implements TextService {

    Map<String, Object> response = new HashMap<String, Object>();

    @Override
    public ResponseEntity<?> GetRandomText(Integer p_start, Integer p_end, Integer w_count_min, Integer w_count_max) throws ParseException {

        GetRandomText getRandomText = new GetRandomText();
        List<DataStatics> mfwl = new ArrayList<>();

        String freq_word="";
        Map<String, Integer> mapresult = new HashMap<String, Integer>();

        Integer avg_paragraph_size=0;
        Integer avg_paragraph_processing_time=0;

        long startTime = System.nanoTime();

        for (int i=p_start; i<=p_end; i++) {

            freq_word = getRandomText.MostFrequentWord(i, w_count_min, w_count_max);
            String[] parts = freq_word.split(",");
            mfwl.add(new DataStatics( parts[0], Integer.parseInt(parts[1]),Integer.parseInt(parts[2])) );
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        long convert = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);

        mfwl.stream().forEach(new Consumer<DataStatics>() {
            @Override
            public void accept(DataStatics dataStatics) {
                System.out.println(dataStatics);
            }
        });


        String MostFre = mfwl.stream().map(DataStatics::getFreq_word).collect(Collectors.joining(" "));

        freq_word = getRandomText.MostFrequentWord(MostFre);

        //mfwl.stream().map(DataStatics::getAvg_paragraph_size)

        DoubleStream ds=mfwl.stream()
                 .collect(Collectors.groupingBy(DataStatics::getAvg_paragraph_size,
                         Collectors.averagingDouble(p -> Double.valueOf(p.getAvg_paragraph_size()))))
                 .values()
                 .stream()
                 .mapToDouble(d -> d);


        DoubleStream ds2=mfwl.stream()
                .collect(Collectors.groupingBy(DataStatics::getAvg_paragraph_processing_time,
                        Collectors.averagingDouble(p -> Double.valueOf(p.getAvg_paragraph_processing_time()))))
                .values()
                .stream()
                .mapToDouble(d -> d);

        response.clear();
        response.put("freq_word", freq_word );
        response.put("avg_paragraph_size", ds.average().getAsDouble() );
        response.put("avg_paragraph_processing_time", ds2.average().getAsDouble() );
        response.put("total_processing_time", convert );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
