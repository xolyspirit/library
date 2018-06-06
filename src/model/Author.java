package model;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;
/**Сущность автора
 * @version 1.0
 * @author Xolyspirit */
@Entity
@Table(name = "author")
public class Author {
    /**id обьекта в БД*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    /**Фамилия автора */
    @Column(name = "last_name")
    private String lastName;
    /**Книги {@link Book}, в написании которых автор имел участие*/
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "authors")
    private List<Book> books;
    /**Стандартные геттеры и сеттеры*/
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}
