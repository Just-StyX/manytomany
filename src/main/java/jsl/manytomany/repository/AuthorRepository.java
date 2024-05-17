package jsl.manytomany.repository;

import jsl.manytomany.entity.Author;
import jsl.manytomany.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
