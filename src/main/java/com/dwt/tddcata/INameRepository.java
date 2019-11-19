package com.dwt.tddcata;

import java.util.List;

public interface INameRepository {

    List<String> getAll();
    void add(List<String> names);
    void remove(List<String> names);
}
