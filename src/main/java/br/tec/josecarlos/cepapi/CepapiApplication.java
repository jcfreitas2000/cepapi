package br.tec.josecarlos.cepapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CepapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepapiApplication.class, args);
	}

}
