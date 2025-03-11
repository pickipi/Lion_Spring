package com.example.swaggerexam.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration // Configuration을 제외하고 모두 Swagger가 제공하는 어노테이션
@OpenAPIDefinition(
        info = @Info(title = "모임 및 일정 관리 API 문서",
        version = "1.0",
        description = "모임 및 일정을 관리하기 위한 API 문서"
        )
)
public class SwaggerConfig {

}
