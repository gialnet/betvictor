package com.betvictor.text.data.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableHistiry {

    private Integer id;
    private String freq_word;
    private Integer avg_paragraph_size;
    private Integer avg_paragraph_processing_time;
    private Integer total_processing_time;
}
