package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //DB가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor//디폴트 생성자를 추가
@ToString
@Getter
public class Nentity {
    @Id //대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동생성 어노테이션
    private Long id;
    @Column
    private String title;
    @Column
    private String content;



}
