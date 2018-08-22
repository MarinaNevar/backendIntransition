package by.Coursepro.course.Config;

import by.Coursepro.course.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@EnableAutoConfiguration
@Configuration
@RequiredArgsConstructor
public class HiberConfig {

    private final EntityManager entityManager;

    @Bean
    SearchService searchService(){
        SearchService searchService = new SearchService(entityManager);
        searchService.fullTextInit();
        return searchService;
    }
}
