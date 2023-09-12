package org.zan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SampleProjectCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(SampleProjectCrudApplication.class, args);
	}

}
