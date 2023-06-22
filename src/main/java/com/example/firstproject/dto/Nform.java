package com.example.firstproject.dto;

import com.example.firstproject.entity.Fentity;
import com.example.firstproject.entity.Nentity;
import lombok.AllArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@ToString
public class Nform {

    private Long id;
    private String title;
    private String content;

    public Nentity toEntity() {
        return new Nentity(id,title,content);
    }
}
