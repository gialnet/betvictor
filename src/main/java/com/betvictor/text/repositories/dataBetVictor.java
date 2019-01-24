package com.betvictor.text.repositories;

import com.betvictor.text.data.object.TableHistiry;

import java.util.List;

public interface dataBetVictor {

    Integer SaveRandomText();
    List<TableHistiry> GetLast10Recors();

}
