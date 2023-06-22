package com.example.firstproject.repository;

import com.example.firstproject.entity.Fentity;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ArticleRepositoryf extends CrudRepository<Fentity, Long> {

    @Override
    ArrayList<Fentity> findAll();
LocalDate current=LocalDate.now();

}
