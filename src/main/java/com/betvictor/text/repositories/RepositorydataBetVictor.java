package com.betvictor.text.repositories;

import com.betvictor.text.data.object.TableHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorydataBetVictor extends CrudRepository<TableHistory, Long> {


    @Query(
            value = "SELECT * FROM History ORDER BY id DESC LIMIT 10",
            nativeQuery = true)

    List<TableHistory> findAll();
    

}
