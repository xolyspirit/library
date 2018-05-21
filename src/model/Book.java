package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    @Column(name = "cost")
    private double cost;
    @Column(name = "isbn")
    private int ISBN;
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Author a:authors) {
            sb.append("Author: " + a.getFirstName());
            sb.append(" " + a.getLastName() + '\n');
        }
        sb.append("Book name: " + name + '\n');
        sb.append("ISBN: " + ISBN + '\n');
        sb.append("Cost: " + cost + '\n');

        return sb.toString();
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Double.compare(book.cost, cost) == 0 &&
                Objects.equals(name, book.name) &&
                Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cost, ISBN, name);
    }


}
