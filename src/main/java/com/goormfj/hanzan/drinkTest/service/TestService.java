package com.goormfj.hanzan.drinkTest.service;

import com.goormfj.hanzan.drinkTest.entity.DrinkPreferenceType;

import java.util.List;

public class TestService {
    // 모든 질문과 대답에 대한 결과를 기록한 배열
    private final String[][] results = {
            {"A", "A", "A", "D"}, // 타입 1
            {"B", "C", "C", "A"}, // 타입 2
            {"C", "B", "D", "C"}, // 타입 3
            {"D", "D", "B", "B"}, // 타입 4
            {"A", "A", "A", "B"}, // 타입 5
            {"B", "C", "B", "B"}, // 타입 6
            {"C", "B", "C", "D"}, // 타입 7
            {"D", "D", "D", "C"}  // 타입 8
    };

    // 술 취향 결정
    public DrinkPreferenceType processTest(List<String> answers) {
        // 사용자의 답변과 결과(result.length)를 비교하여 술 취향 타입을 결정
        for (int i = 0; i < results.length; i++) {
            if (isMatchingResult(answers, results[i])) { //answer와 result배열과 같다면
                return DrinkPreferenceType.values()[i];
            }
        }
        //어떤 타입에도 해당되지 않는 경우, 예외처리
        throw new IllegalArgumentException("어떤 타입에도 해당하지 않습니다!");
    }

    // isMatchingResult 정의
    private boolean isMatchingResult(List<String> answers, String[] result) {
        for (int i = 0; i < answers.size(); i++) {
            if (!answers.get(i).equals(result[i])) {//같지 않으면
                return false;//거짓
            }
        }
        return true;//그게 아니라면 참
    }
}
