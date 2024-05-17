package jsl.manytomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "author_many")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new LinkedHashSet<>();

    public void addBook(Book book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    public void removeBooks() {
        Iterator<Book> iterator = this.books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            book.getAuthors().remove(this);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return age == author.age && Objects.equals(id, author.id) && Objects.equals(genre, author.genre) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, genre, name);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
