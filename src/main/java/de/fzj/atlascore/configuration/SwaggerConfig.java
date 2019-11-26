package de.fzj.atlascore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * Configuration for the swagger documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(
                        new Tag("Allen brain", "Data from allen brain api"),
                        new Tag("Knowledge Graph", "Data from the knowledge graph"),
                        new Tag("TVB", "TVB data for simulations")
                )
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(authorizationHeaderScheme()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Atlas Core REST API",
                "Description of all endpoints provided by the atlas core",
                "0.0.1",
                "/tos",
                new Contact("Vadim Marcenko", "", "v.marcenko@fz-juelich.de"),
                "License ...",
                "License URL ...",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(authorizationHeaderReference()))
                .forPaths(PathSelectors.any())
                .build();
    }

    //region === Scheme and reference for setting Authorization header in swagger ui
    private SecurityScheme authorizationHeaderScheme() {
        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
    }

    private SecurityReference authorizationHeaderReference() {
        return new SecurityReference(AUTHORIZATION_HEADER, new AuthorizationScope[0]);
    }
    //endregion
}
