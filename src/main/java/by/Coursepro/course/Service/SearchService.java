package by.Coursepro.course.Service;

import by.Coursepro.course.Entity.Instruction;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final EntityManager entityManager;

    public void fullTextInit(){
        try {
        FullTextEntityManager fullTextEntityManager= Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Instruction> fuzzySearch(String searchTerm){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Instruction.class).get();
        Query luceneQuery=queryBuilder.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1)
                .onFields("name","description","text","publish_date")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Instruction.class);

        List<Instruction> instructions=null;

        instructions=jpaQuery.getResultList();
        return instructions;

    }


}
