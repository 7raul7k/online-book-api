package ro.myclass.onlinebookapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "book_db")
@Entity(name = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "id"
    )
    private long id;
    @Column(name = "book_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(name = "book_genre",
            nullable = false,
            columnDefinition = "TEXT")
    private String genre;
    @Column(name = "book_year",
            nullable = false,
            columnDefinition = "INT")
    private int year;
    @Column(name = "book_author",
            nullable = false,
            columnDefinition = "TEXT")
    private String author;

    @Override
    public String toString(){
        return id+","+name+","+genre+","+year+","+author;
    }

    @Override
    public boolean equals(Object o){

        Book book = (Book) o;

        if(this.id == book.getId() && this.name.equals(book.getName()) && this.genre.equals(book.getGenre()) && this.year == book.getYear() && this.author.equals(book.getAuthor())) {
            return true;
        }
        return false;
    }
}
