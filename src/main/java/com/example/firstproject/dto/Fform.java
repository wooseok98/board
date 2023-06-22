package com.example.firstproject.dto;

import com.example.firstproject.entity.Fentity;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class Fform {

    private Long id;
    private String title;
    private String content;



    public Fentity toEntity() {
        return new Fentity(id,title,content);
    }
}
