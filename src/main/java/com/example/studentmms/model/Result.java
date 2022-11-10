package com.example.studentmms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String index_no;
    private String student;
    private String year;
    private Term term;
    private List marks;
}



