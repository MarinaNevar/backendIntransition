package by.Coursepro.course.controllers;

import by.Coursepro.course.entity.Instruction;
import by.Coursepro.course.service.SearchService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/getSearch")
    public List<Instruction> getSearch(String search){
      return this.searchService.fuzzySearch(search);
  }
}
