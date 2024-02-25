package app.curs20.dtos;

import app.curs20.enitities.Adresa;
import app.curs20.enitities.Student;
import lombok.Data;

@Data
public class StudentAdressDto {
    private String nume;
    private String prenume;
    private String cnp;
    private Adresa adresa;
}
