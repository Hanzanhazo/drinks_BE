package com.goormfj.hanzan.drinkTest.controller;

import com.goormfj.hanzan.drinkTest.entity.DrinkPreferenceType;
import com.goormfj.hanzan.drinkTest.entity.Question;
import com.goormfj.hanzan.drinkTest.entity.User;
import com.goormfj.hanzan.drinkTest.service.DrinkTestService;

import com.goormfj.hanzan.drinkTest.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/drinkTest")
public class DrinkTestController {

    UserService userService;

    public DrinkTestService drinkTestService;

    //테스트 시작페이지
    @GetMapping("/start-test")
    public String StartTestPage(HttpSession session, Model model) {


        //유저확인
        User user = (User) session.getAttribute("user");
        if (user == null) { //사용자가 로그인되어 있지 않으면 로그인페이지로 redirection
            return "redirect:/user/login";
        }

        //테스트를 이미 한 유저
        if (user.getDrinkPreferenceType() != null) {
            user.setDrinkPreferenceType(null); //이전 테스트 초기화
            userService.save(user); //저장
        }

        //질문 가져오기
        List<Question> questions = DrinkTestService.getAllQuestions();//모든 질문
        model.addAttribute("questions", questions); //뷰로 전달
        return "/start-test"; //테스트페이지로
    }

    //테스트 결과 제출
    @PostMapping("/submit")
    public String submitDrinkTest(HttpSession session, @RequestParam List<String> answers) {
        //동일하게 유저 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        //타입 결정
        DrinkPreferenceType drinkPreferenceType = drinkTestService.processTest(answers); //사용자 답변
        user.setDrinkPreferenceType(drinkPreferenceType); //유저 술 취향 업데이트
        userService.save(user); //저장
        return "redirect:/result";
    }


    //테스트 결과
    @GetMapping("/result")
    public String showResult(HttpSession session, Model model) {
        //결과페이지 보여주기
        //로그인한 유저 가져오기
        User user = (User) session.getAttribute("user");

        //로그인한 유저가 아닐 경우
        if (user == null || user.getDrinkPreferenceType() == null) {
            return "redirect:/test";
        }
        //술 취향 type에 저장
        DrinkPreferenceType drinkPreferenceType = DrinkPreferenceType.valueOf(String.valueOf(user.getDrinkPreferenceType()));
        model.addAttribute("type", drinkPreferenceType);//뷰 전달
        return "result";
    }

}
