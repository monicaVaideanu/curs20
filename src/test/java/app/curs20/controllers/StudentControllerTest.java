package app.curs20.controllers;

import app.curs20.dtos.StudentAdressDto;
import app.curs20.enitities.Adresa;
import app.curs20.enitities.Student;
import app.curs20.repositories.AdresaRepository;
import app.curs20.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AdresaRepository adresaRepository;
    @BeforeAll
    public static void setUp() {
    }

    @Test
    void testAddStudent() throws Exception {
        StudentAdressDto studentAddressDto = new StudentAdressDto();
        studentAddressDto.setNume("John");
        studentAddressDto.setPrenume("Doe");
        studentAddressDto.setCnp("12345");

        Adresa adresa = new Adresa();
        adresa.setStrada("Some Street");
        adresa.setNumar("123");
        adresa.setLocalitate("Some City");
        studentAddressDto.setAdresa(adresa);

        Student student = new Student();
        student.setNume(studentAddressDto.getNume());
        student.setPrenume(studentAddressDto.getPrenume());
        student.setCnp(studentAddressDto.getCnp());
        student.setAdresa(adresa);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(adresaRepository.save(any(Adresa.class))).thenReturn(adresa);

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(studentAddressDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Studentul John a fost adaugat cu succes!"));
    }
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
