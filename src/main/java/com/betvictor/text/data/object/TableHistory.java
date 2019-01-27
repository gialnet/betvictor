package com.betvictor.text.data.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HISTORY")
public class TableHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String freq_word;
    private Integer avg_paragraph_size;
    private Integer avg_paragraph_processing_time;
    private Integer total_processing_time;

}
