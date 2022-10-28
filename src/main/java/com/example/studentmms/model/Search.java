package com.example.studentmms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Search {
    private String index;
    private String year;
    private Term term;
}
