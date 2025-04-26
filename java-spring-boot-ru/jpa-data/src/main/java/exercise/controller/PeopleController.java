package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping
    public List<Person> persons() {
        return this.personRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        this.personRepository.save(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(person);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable long id) {
        this.personRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(id);
    }
    // END
}
