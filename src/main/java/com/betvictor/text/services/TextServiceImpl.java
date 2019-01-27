package com.betvictor.text.services;

import com.betvictor.text.data.object.DataStatics;
import com.betvictor.text.data.object.TableHistory;
import com.betvictor.text.repositories.RepositorydataBetVictor;
import com.betvictor.text.utils.GetRandomText;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class TextServiceImpl implements TextService {

    private final RepositorydataBetVictor repositorydataBetVictor;

    Map<String, Object> response = new HashMap<String, Object>();

    public TextServiceImpl(RepositorydataBetVictor repositorydataBetVictor) {
        this.repositorydataBetVictor = repositorydataBetVictor;
    }

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

        Double avg_para = ds.average().getAsDouble();
        Integer avg_paraInt = avg_para.intValue();
        Double avg_proce = ds2.average().getAsDouble();
        Integer avg_proceInt = avg_proce.intValue();

        repositorydataBetVictor.save(new TableHistory((long) 5, freq_word, avg_paraInt,avg_proceInt, (int) convert));

        response.clear();
        response.put("freq_word", freq_word );
        response.put("avg_paragraph_size", avg_paraInt );
        response.put("avg_paragraph_processing_time", avg_proce );
        response.put("total_processing_time", convert );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> GetLast10Reords() {

        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "id");
        Page<TableHistory> topPage = repositorydataBetVictor.FindLast10(1L,pageable);
        List<TableHistory> tableHistories = topPage.getContent();

        response.clear();
        response.put("data",tableHistories);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
