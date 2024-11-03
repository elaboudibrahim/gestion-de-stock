//package com.gestion_stock.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration{
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .description("Gestion de stock api documentation")
//                        .title("Gestion de stock api")
//                        .build())
//                .groupName("REST API V1")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gestion_stock"))
//                .paths(PathSelectors.ant("/**"))
//                .build();
//    }
//}
