package by.Coursepro.course.configurations;

import by.Coursepro.course.service.SearchService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@EnableAutoConfiguration
public class HibernateConfig {

    private final EntityManager entityManager;

    public HibernateConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    SearchService searchService(){
        SearchService searchService = new SearchService(entityManager);
        searchService.fullTextInit();
        return searchService;
    }
}
