package by.Coursepro.course.Controller;

import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

  @GetMapping("/getSearch")
    public List<Instruction> getSearch(String search){
      return this.searchService.fuzzySearch(search);
  }
}
