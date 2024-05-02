package com.goormfj.hanzan.drinkTest.service;

import com.goormfj.hanzan.drinkTest.domain.DrinkPreferenceType;
import com.goormfj.hanzan.drinkTest.domain.Question;
import com.goormfj.hanzan.drinkTest.domain.Response;
import com.goormfj.hanzan.drinkTest.domain.TestResult;
import com.goormfj.hanzan.drinkTest.dto.TestResultDto;
import com.goormfj.hanzan.drinkTest.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {
    private final TestResultRepository testResultRepository;

    @Autowired
    public TestService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    public void startTest(Long userId) {
        // 테스트 시작 전에 기존에 저장된 사용자의 테스트 결과를 초기화
        testResultRepository.deleteByUserId(userId);
    }

    public void saveResponse(TestResultDto testResultDto) {
        // 사용자의 각 질문에 대한 응답을 데이터베이스에 저장
        // 이 예제에서는 간단히 DTO를 바로 저장하고 있다고 가정합니다.
        TestResult testResult = convertDtoToEntity(testResultDto);
        testResultRepository.save(testResult);
    }

    private TestResult convertDtoToEntity(TestResultDto dto) {
        return TestResult.builder()
                .userId(dto.getUserId())
                .responses(dto.getResponses())
                .preferenceType(dto.getDrinkPreferenceType())
                .build();
    }


    public TestResult completeTest(Long userId) {
        // 모든 응답을 수집하여 최종 술 취향 타입을 결정
        TestResult testResult = testResultRepository.findByUserId(userId);
        DrinkPreferenceType preferenceType = determinePreferenceType(testResult.getResponses());
        testResult = TestResult.builder()
                .resultId(testResult.getResultId())
                .userId(testResult.getUserId())
                .responses(testResult.getResponses())
                .preferenceType(preferenceType)
                .build();
        // 최종 결과를 데이터베이스에 저장
        testResultRepository.save(testResult);
        return testResult;
    }

    private DrinkPreferenceType determinePreferenceType(Map<Question, Response> responses) {
        // 응답을 분석하여 술 취향 타입을 결정하는 로직
        // 예를 들어, 특정 조합의 응답에 따라 DrinkPreferenceType을 반환합니다.
        // 여기서는 단순화를 위해 직접 구현하지 않았습니다.
        Map<DrinkPreferenceType, Map<Question, Response>> preferencePatterns = new HashMap<>();

        preferencePatterns.put(DrinkPreferenceType.TYPE_1, Map.of(
                Question.FLAVOR_PREFERENCE, Response.A,
                Question.ALCOHOL_LEVEL, Response.A,
                Question.SITUATION, Response.A,
                Question.DRAMA_OR_MOVIE_SCENE, Response.D
        ));

        preferencePatterns.put(DrinkPreferenceType.TYPE_2, Map.of(
                Question.FLAVOR_PREFERENCE, Response.B,
                Question.ALCOHOL_LEVEL, Response.C,
                Question.SITUATION, Response.C,
                Question.DRAMA_OR_MOVIE_SCENE, Response.A
        ));

        preferencePatterns.put(DrinkPreferenceType.TYPE_3, Map.of(
                Question.FLAVOR_PREFERENCE, Response.C,
                Question.ALCOHOL_LEVEL, Response.B,
                Question.SITUATION, Response.D,
                Question.DRAMA_OR_MOVIE_SCENE, Response.C
        ));

        preferencePatterns.put(DrinkPreferenceType.TYPE_4, Map.of(
                Question.FLAVOR_PREFERENCE, Response.D,
                Question.ALCOHOL_LEVEL, Response.D,
                Question.SITUATION, Response.B,
                Question.DRAMA_OR_MOVIE_SCENE, Response.B
        ));

        preferencePatterns.put(DrinkPreferenceType.TYPE_5, Map.of(
                Question.FLAVOR_PREFERENCE, Response.A,
                Question.ALCOHOL_LEVEL, Response.A,
                Question.SITUATION, Response.A,
                Question.DRAMA_OR_MOVIE_SCENE, Response.B
        ));
        preferencePatterns.put(DrinkPreferenceType.TYPE_6, Map.of(
                Question.FLAVOR_PREFERENCE, Response.B,
                Question.ALCOHOL_LEVEL, Response.C,
                Question.SITUATION, Response.B,
                Question.DRAMA_OR_MOVIE_SCENE, Response.B
        ));
        preferencePatterns.put(DrinkPreferenceType.TYPE_7, Map.of(
                Question.FLAVOR_PREFERENCE, Response.C,
                Question.ALCOHOL_LEVEL, Response.B,
                Question.SITUATION, Response.C,
                Question.DRAMA_OR_MOVIE_SCENE, Response.D
        ));
        preferencePatterns.put(DrinkPreferenceType.TYPE_8, Map.of(
                Question.FLAVOR_PREFERENCE, Response.D,
                Question.ALCOHOL_LEVEL, Response.D,
                Question.SITUATION, Response.D,
                Question.DRAMA_OR_MOVIE_SCENE, Response.C
        ));

        //이하 다른 타입 동일하게 추가 가능

        for (Map.Entry<DrinkPreferenceType, Map<Question, Response>> entry : preferencePatterns.entrySet()) {
            if (entry.getValue().equals(responses)) {
                return entry.getKey();
            }
        }

        // 기본적으로 타입을 결정할 수 없는 경우, 기본 타입을 반환하거나 null을 반환할 수 있습니다.
        return null;

    }

    public TestResult getResults(Long userId) {
        // 사용자 ID에 따라 테스트 결과를 검색하여 반환
        return testResultRepository.findByUserId(userId);
    }
}
