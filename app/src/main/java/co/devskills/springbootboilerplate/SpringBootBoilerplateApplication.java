package co.devskills.springbootboilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableJpaRepositories(basePackages ={"co.devskills.springbootboilerplate.repository"})
//@EntityScan("co.devskills.springbootboilerplate.model.entity")
@ComponentScan(basePackages = {"co.devskills.springbootboilerplate.model.entity"})
public class SpringBootBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBoilerplateApplication.class, args);
	}

}
