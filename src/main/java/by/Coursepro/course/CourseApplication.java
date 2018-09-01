package by.Coursepro.course;

import by.Coursepro.course.configurations.SecurityConfig;
import by.Coursepro.course.configurations.CORSConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SecurityConfig.class, CORSConfiguration.class })
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}


}
