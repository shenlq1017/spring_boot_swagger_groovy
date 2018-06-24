package com.lic.portal.swagger.config

import com.google.common.collect.Lists
import io.swagger.annotations.Api
import io.swagger.models.parameters.Parameter
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
@ConditionalOnExpression("\${swagger.enabled:true}")
class SwaggerConfig {

    List<ApiKey> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        return Lists.newArrayList(apiKey);
    }

    List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*\$"))
                .build();
        return Lists.newArrayList(securityContext);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }


    @Bean
    Docket documentation(){
        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").defaultValue("test").required(false).build();
//        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api)) // 对所有 api 进行监控
                .paths(PathSelectors.regex("/.*")) // 对所有路径进行监控
                .build()
                .protocols(new HashSet<String>(Lists.newArrayList("http")))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping("/");
    }

    @Bean
    UiConfiguration uiConfig(){
        return UiConfiguration.DEFAULT;
    }

//    ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("服务API")
//                .description("服务端后台接口说明文档")
//                .build();
//    }
}
