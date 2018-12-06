package com.epam.audiospot.repository;

import java.util.List;

public interface Specification {
    String toSql();
    List<String> getParameters();
}