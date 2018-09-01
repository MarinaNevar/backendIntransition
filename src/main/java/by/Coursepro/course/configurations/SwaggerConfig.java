package by.Coursepro.course.configurations;

//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {

//    @Bean
//    public Docket swaggerSettings() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("^(?!/error).*$"))
//                .build()
//                .pathMapping("/");
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("REST API COurseproj")
//                .description("Developers: Sanchenko")
//                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
//                .contact("Sanchenko Roman")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
//                .version("2.1")
//                .build();
//    }
//
//}