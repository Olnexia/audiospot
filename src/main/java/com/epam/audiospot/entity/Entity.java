package com.epam.audiospot.entity;

import java.io.Serializable;

public interface Entity extends Serializable {
    void setId(Long id);
    Long getId();
}
