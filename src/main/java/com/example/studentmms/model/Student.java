package com.example.studentmms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String id;
    private String name;
    private String index_no;
    private String school;
    private String teacher;
    private Subject subjects[];
}
