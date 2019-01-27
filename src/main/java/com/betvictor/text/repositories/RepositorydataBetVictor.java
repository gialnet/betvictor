package com.betvictor.text.repositories;

import com.betvictor.text.data.object.TableHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorydataBetVictor extends CrudRepository<TableHistory, Long> {

    /*Integer SaveRandomText();*/
    //List<TableHistory> GetLast10Recors();

    default Page<TableHistory> FindLast10(Long id, Pageable pageable) {
        return null;
    }

}
