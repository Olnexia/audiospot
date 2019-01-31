package com.epam.audiospot.entity;

import java.io.Serializable;

/**
 * This interface must be implemented by all entity classes of the application because all of them must support
 * serialization and have accessor method for {@code ID} field
 */

public interface Entity extends Serializable {
    void setId(Long id);

    Long getId();
}
