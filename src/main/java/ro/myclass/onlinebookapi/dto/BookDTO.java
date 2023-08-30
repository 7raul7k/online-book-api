package ro.myclass.onlinebookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookDTO {

    private String name;
    private String title;
    private String author;
    private String genre;
    private int year;

}
