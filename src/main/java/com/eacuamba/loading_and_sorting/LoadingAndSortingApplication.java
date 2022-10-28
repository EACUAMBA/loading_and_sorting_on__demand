package com.eacuamba.loading_and_sorting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.eacuamba.loading_and_sorting"})
@EntityScan
public class LoadingAndSortingApplication extends SpringBootServletInitializer {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(MinimalWarApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(LoadingAndSortingApplication.class);
	}
}
