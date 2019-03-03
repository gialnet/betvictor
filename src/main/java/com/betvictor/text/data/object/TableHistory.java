package com.betvictor.text.data.object;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HISTORY")
public class TableHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String freq_word;
    @NonNull
    private Integer avg_paragraph_size;
    @NonNull
    private Integer avg_paragraph_processing_time;
    @NonNull
    private Integer total_processing_time;

}
