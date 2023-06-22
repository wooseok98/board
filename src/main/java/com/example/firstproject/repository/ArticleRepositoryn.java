package com.example.firstproject.repository;

import com.example.firstproject.entity.Nentity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepositoryn extends CrudRepository<Nentity,Long> {
    @Override
    ArrayList<Nentity> findAll();
}
