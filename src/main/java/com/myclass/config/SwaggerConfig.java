package com.myclass.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.val;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        		.forCodeGeneration(true)
                .globalOperationParameters(globalParameterList())
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	private List<Parameter> globalParameterList() {
	    val authTokenHeader =
	        (springfox.documentation.service.Parameter) new ParameterBuilder()
		    .name("Authorization") // name of the header
		    .modelRef(new ModelRef("string")) // data-type of the header
		    .required(false) // required/optional
		    .parameterType("header") // for query-param, this value can be 'query'
		    .description("Basic Auth Token")
		    .build();

	    return Collections.singletonList(authTokenHeader);
	}
}
