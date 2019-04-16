package com.epam.javalab.travelagency.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationDetails {
    private int currentPage;
    private int lastPage;
    private int perPage;
}
