package dev.giovalgas.personmanager.controller;

import dev.giovalgas.personmanager.model.Filter;
import dev.giovalgas.personmanager.entity.person.PersonEntity;
import dev.giovalgas.personmanager.model.PageData;
import dev.giovalgas.personmanager.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @PostMapping
  public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity personEntity) {
    return ResponseEntity.ok(personService.createPerson(personEntity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonEntity> editPerson(@RequestBody PersonEntity personEntity, @PathVariable Long id) {
    return ResponseEntity.ok(personService.editPerson(id, personEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PersonEntity> deletePerson(@PathVariable Long id) {
    personService.logicallyDeletePerson(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id) {
    return ResponseEntity.ok(personService.getPersonById(id));
  }

  @GetMapping
  public ResponseEntity<List<PersonEntity>> getAllPeopleByFilter(
          @RequestBody(required = false) Filter filter,
          @RequestParam(name = "pageData", required = false) PageData pageData
  ) {

    if(pageData == null) {
      pageData = new PageData();
    }

    if(filter == null) {
      filter = new Filter();
    }

    return ResponseEntity.ok(personService.getAllPeopleByFilter(filter, pageData));
  }

}
