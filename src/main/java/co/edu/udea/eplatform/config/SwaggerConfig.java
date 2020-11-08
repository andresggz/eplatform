package co.edu.udea.eplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api")
                .globalOperationParameters(getOperationParameterHeaders()).useDefaultResponseMessages(false)
                .directModelSubstitute(Instant.class, Long.class).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**")).build().securitySchemes(Collections.singletonList(apiKey()))
                .apiInfo(metaData());
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private List<Parameter> getOperationParameterHeaders() {
        return Arrays.asList(new ParameterBuilder().name(HttpHeaders.CONTENT_TYPE)
                .defaultValue(MediaType.APPLICATION_JSON_VALUE).description("The expected media type of the resource.")
                .modelRef(new ModelRef("string")).parameterType("header").required(true).build());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("OS REST API").build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
