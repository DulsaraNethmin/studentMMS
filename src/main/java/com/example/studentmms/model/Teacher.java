package com.example.studentmms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data /*develop getters and setters*/
@Document /*Like a row in collection*/
@AllArgsConstructor /*Constructor with all variables*/
@NoArgsConstructor /*constructors without any argument*/
public class Teacher {
    @Id /*Act as primary key and assign it to id in database*/
    private String Id;
    private String teacher_id;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private Student students[];
}
