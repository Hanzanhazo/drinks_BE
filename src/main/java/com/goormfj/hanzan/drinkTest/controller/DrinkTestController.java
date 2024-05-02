package com.goormfj.hanzan.drinkTest.controller;

import com.goormfj.hanzan.drinkTest.domain.TestResult;
import com.goormfj.hanzan.drinkTest.dto.TestResultDto;
import com.goormfj.hanzan.drinkTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drinkTest")
public class DrinkTestController {
    private final TestService testService;

    public DrinkTestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/start/{userId}")
    public ResponseEntity<?> startTest(@PathVariable Long userId) {
        try {
            testService.startTest(userId);
            return ResponseEntity.ok(userId + "님! 술 취향테스트 시작해볼까요?");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("유저가 아니므로 테스트를 진행할 수 없습니다.");
        }
    }

    //페이지마다 저장
    @PostMapping("/save")
    public ResponseEntity<?> saveResponse(@RequestBody TestResultDto testResultDto) {
        try {
            testService.saveResponse(testResultDto);
            return ResponseEntity.ok(testResultDto.getUserId() + "님, 좋습니다! 이대로 결과까지 가볼까요?");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("선택사항을 저장하는데 오류가 발생했습니다.");
        }
    }

    @PostMapping("/complete/{userId}")
    public ResponseEntity<?> completeTest(@PathVariable Long userId) {
        try {
            TestResult resultDto = testService.completeTest(userId);
            return ResponseEntity.ok("두근두근~" + resultDto.getUserId() + "님, 결과를 확인해보세요!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("테스트를 완료하는데 오류가 발생했습니다.");
        }
    }

    @GetMapping("/results/{userId}")
    public ResponseEntity<?> getResults(@PathVariable Long userId) {
        try {
            TestResult resultDto = testService.getResults(userId);
            return ResponseEntity.ok(resultDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("테스트 결과에 오류가 발생했습니다.");
        }
    }
}
