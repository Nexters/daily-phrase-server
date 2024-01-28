package com.nexters.dailyphrase.config;

import java.util.*;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import com.nexters.dailyphrase.common.annotation.ApiErrorCodeExample;
import com.nexters.dailyphrase.common.exception.BaseErrorCode;
import com.nexters.dailyphrase.common.exception.ErrorReason;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info =
                new Info()
                        .title("Daily Phrase API 명세서") // 타이틀
                        .version("1.0.0") // 문서 버전
                        .description("잘못된 부분이나 오류 발생 시 바로 말씀해주세요."); // 문서 설명

        String jwtSchemeName = "AccessToken";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components =
                new Components()
                        .addSecuritySchemes(
                                jwtSchemeName,
                                new SecurityScheme()
                                        .name(jwtSchemeName)
                                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                                        .scheme("bearer")
                                        .bearerFormat("JWT"));

        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(components)
                // API 마다 Security 인증 컴포넌트 설정
                .addSecurityItem(securityRequirement)
                .info(info);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorCodeExample apiErrorCodeExample =
                    handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);
            // ApiErrorCodeExample 어노테이션 단 메소드 적용
            if (apiErrorCodeExample != null) {
                Class<? extends BaseErrorCode>[] errorCodes = apiErrorCodeExample.value();
                generateErrorCodeResponseExample(operation, errorCodes);
            }
            return operation;
        };
    }

    private void generateErrorCodeResponseExample(
            Operation operation, Class<? extends BaseErrorCode>[] errorCodeList) {
        ApiResponses responses = operation.getResponses();
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders = new HashMap<>();

        for (Class<? extends BaseErrorCode> type : errorCodeList) {
            BaseErrorCode[] errorCodes = type.getEnumConstants();
            // 400, 401, 404 등 에러코드의 상태코드들로 리스트로 모읍니다.
            // 400 같은 상태코드에 여러 에러코드들이 있을 수 있습니다.
            List<ExampleHolder> exampleHolders =
                    Arrays.stream(errorCodes)
                            .map(
                                    baseErrorCode -> {
                                        try {
                                            ErrorReason errorReason =
                                                    baseErrorCode.getErrorReason();
                                            ErrorReason errorReasonToView =
                                                    baseErrorCode.getErrorReason();
                                            return ExampleHolder.builder()
                                                    .holder(
                                                            getSwaggerExample(
                                                                    baseErrorCode.getExplainError(),
                                                                    errorReasonToView))
                                                    .code(errorReason.getStatus())
                                                    .name(errorReason.getCode())
                                                    .build();
                                        } catch (NoSuchFieldException e) {
                                            throw new RuntimeException(e);
                                        }
                                    })
                            .toList();

            // statusWithExampleHolders에 현재 루프의 결과를 추가합니다.
            exampleHolders.forEach(
                    exampleHolder ->
                            statusWithExampleHolders
                                    .computeIfAbsent(
                                            exampleHolder.getCode(), k -> new ArrayList<>())
                                    .add(exampleHolder));
        }

        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    private Example getSwaggerExample(String value, ErrorReason errorReason) {
        Example example = new Example();
        example.description(value);
        example.setValue(errorReason);
        return example;
    }

    private void addExamplesToResponses(
            ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();
                    v.forEach(
                            exampleHolder ->
                                    mediaType.addExamples(
                                            exampleHolder.getName(), exampleHolder.getHolder()));
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(status.toString(), apiResponse);
                });
    }
}
