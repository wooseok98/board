package com.example.firstproject.controller;
//hello

import com.example.firstproject.dto.Fform;
import com.example.firstproject.dto.Nform;
import com.example.firstproject.entity.Fentity;
import com.example.firstproject.entity.Nentity;
import com.example.firstproject.repository.ArticleRepositoryf;
import com.example.firstproject.repository.ArticleRepositoryn;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j //로깅을 위한 골뱅이(어노테이션)
public class ArticleController<Localdate> {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepositoryf articleRepository1;
    @Autowired
    private ArticleRepositoryn articleRepository2;
    //private ArticleRepositoryf articleRepository;

    // 공지 추가란
    @GetMapping("/notice")
    public String create(){return "notice";}
    // 자유 추가란
    @GetMapping("/free")
    public String free(){return "free";}
    // 공지 수정



    //메인 화면
    @GetMapping("/main")
    public String newArticleForm( Model model){
        // 1:모둔 Article을 가져온다!
        List<Fentity> articleEntityListf = articleRepository1.findAll();
        List<Nentity> articleEntityListn = articleRepository2.findAll();


        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleListf",articleEntityListf);
        model.addAttribute("articleListn",articleEntityListn);
        // 3: 뷰 페이지를 설정!

        return "main";
    }

    //자유게시판 추가
    @PostMapping("/createn")
    public String ncreate(Nform form){
        log.info(form.toString());
        Nentity article=form.toEntity();
        log.info(article.toString());
        Nentity saved = articleRepository2.save(article);
        log.info(saved.toString());
        return "redirect:/createn/"+saved.getId();
    }

    //공지게시판 추가
    @PostMapping("/createf")
    public String ncreate(@NotNull Fform form){
        log.info(form.toString());
        Fentity article=form.toEntity();
        log.info(article.toString());
        Fentity saved = articleRepository1.save(article);
        log.info(saved.toString());
        return "redirect:/createf/"+saved.getId();
    }
    //자유 글쓴거 확인
    @GetMapping("/createn/{id}")
    //@PathVariable 넣는이유는 GetMapping에서 id를 받아온다는 의미
    public String showing1(@PathVariable Long id, Model model){
        log.info("id="+id);
        //id로 데이터를 가져옴
        Nentity articleEntity = articleRepository2.findById(id).orElse(null);
        //가져온데이터를 모델에등록
        model.addAttribute("article",articleEntity);
        //보여줄 페이지
        return "noticeBulletin";
    }

    //공지 글쓴거 확인
    @GetMapping("/createf/{id}")
    //@PathVariable 넣는이유는 GetMapping에서 id를 받아온다는 의미
    public String showing2(@PathVariable Long id, Model model){
        log.info("id="+id);
        //id로 데이터를 가져옴
        Fentity articleEntity = articleRepository1.findById(id).orElse(null);
        //가져온데이터를 모델에등록
        model.addAttribute("article",articleEntity);
        //보여줄 페이지
        return "freeBulletin";
    }

    //공지 수정
    @GetMapping("/editf/{id}")
    public String editf(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기
         Fentity articleEntity=articleRepository1.findById(id).orElse(null);

        //모델에 데이터를 등록!
        model.addAttribute("article",articleEntity);
        //뷰 페이지 설정
        return "fmodify";
    }
    @GetMapping("/editn/{id}")
    public String editn(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기
        Nentity articleEntity=articleRepository2.findById(id).orElse(null);

        //모델에 데이터를 등록!
        model.addAttribute("article",articleEntity);
        //뷰 페이지 설정
        return "nmodify";
    }
    @PostMapping("/updatef")
    public String updatef(Fform form){
        log.info(form.toString());
        // 1: DTO를 엔티티로 변환한다!
        Fentity articleEntity=form.toEntity();
        log.info(articleEntity.toString());
        // 2: 엔티티를 DB로 저장한다!
        // 2-1: DB에 기존 데이터를 가져온다!
        Fentity target = articleRepository1.findById(articleEntity.getId()).orElse(null);
        // 2-2: 기존 데이터에 값을 갱신한다!
        if (target !=null){
            articleRepository1.save(articleEntity); //엔티티가 db로 갱신
        }

        // 3: 수정 결과 페이지로 리다이렉트 한다
        return "redirect:/createf/" + articleEntity.getId();

    }

    @PostMapping("/updaten")
    public String updaten(Nform form){
        log.info(form.toString());
        // 1: DTO를 엔티티로 변환한다!
        Nentity articleEntity=form.toEntity();
        log.info(articleEntity.toString());
        // 2: 엔티티를 DB로 저장한다!
        // 2-1: DB에 기존 데이터를 가져온다!
        Nentity target = articleRepository2.findById(articleEntity.getId()).orElse(null);
        // 2-2: 기존 데이터에 값을 갱신한다!
        if (target !=null){
            articleRepository2.save(articleEntity); //엔티티가 db로 갱신
        }

        // 3: 수정 결과 페이지로 리다이렉트 한다
        return "redirect:/createn/" + articleEntity.getId();

    }
    @GetMapping("/deletef/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다");

        // 1: 삭제 대상을 가져온다
        Fentity target=articleRepository1.findById(id).orElse(null);
        log.info(target.toString());
        // 2: 대상을 삭제한다
        if(target != null){
            articleRepository1.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다");
        }
        // 3: 결과 페이지로 리다이렉트 한다

        return "redirect:/main";
    }
    @GetMapping("/deleten/{id}")
    public String delete2(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다");

        // 1: 삭제 대상을 가져온다
        Nentity target=articleRepository2.findById(id).orElse(null);
        log.info(target.toString());
        // 2: 대상을 삭제한다
        if(target != null){
            articleRepository2.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다");
        }
        // 3: 결과 페이지로 리다이렉트 한다

        return "redirect:/main";
    }





}
