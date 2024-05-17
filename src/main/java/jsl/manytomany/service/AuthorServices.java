package jsl.manytomany.service;

import jsl.manytomany.entity.Author;
import jsl.manytomany.entity.Book;
import jsl.manytomany.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServices {
    private final AuthorRepository authorRepository;

    public AuthorServices(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author addBook(String authorId, Book book) {
        var author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            var foundAuthor = author.get();
            foundAuthor.addBook(book);
            return authorRepository.save(foundAuthor);
        }
        return null;
    }
    public Optional<Author> findById(String authorId) {
        return authorRepository.findById(authorId);
    }
}
