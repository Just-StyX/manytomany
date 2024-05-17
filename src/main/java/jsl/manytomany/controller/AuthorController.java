package jsl.manytomany.controller;

import jsl.manytomany.entity.Author;
import jsl.manytomany.entity.Book;
import jsl.manytomany.service.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorServices authorServices;

    public AuthorController(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    @PostMapping
    private ResponseEntity<Void> createAuthor(@RequestBody Author author, UriComponentsBuilder uriComponentsBuilder) {
        var savedAuthor = authorServices.saveAuthor(author);
        URI location = uriComponentsBuilder.path("/api/author/{id}").buildAndExpand(savedAuthor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{authorId}")
    private ResponseEntity<Void> addBook(@PathVariable String authorId, @RequestBody Book book) {
        var author = authorServices.addBook(authorId, book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{authorId}")
    private ResponseEntity<Author> fetchAuthor(@PathVariable String authorId) {
        return ResponseEntity.of(authorServices.findById(authorId));
    }
}
