package app.curs20.controllers;

import app.curs20.dtos.StudentAdressDto;
import app.curs20.enitities.Adresa;
import app.curs20.enitities.Materie;
import app.curs20.enitities.Student;
import app.curs20.enitities.StudentiToMaterie;
import app.curs20.repositories.AdresaRepository;
import app.curs20.repositories.MaterieRepository;
import app.curs20.repositories.StudentRepository;
import app.curs20.repositories.StudentiToMaterieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")

public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentiToMaterieRepository studentiToMaterieRepository;
    private final MaterieRepository materieRepository;
    private final AdresaRepository adresaRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, AdresaRepository adresaRepository,
                             MaterieRepository materieRepository, StudentiToMaterieRepository studentiToMaterieRepository) {
        this.studentRepository = studentRepository;
        this.studentiToMaterieRepository = studentiToMaterieRepository;
        this.materieRepository = materieRepository;
        this.adresaRepository = adresaRepository;
    }

    @PostMapping()
    @Transactional
    public String addStudent(@RequestBody StudentAdressDto studentAddressDto) {
        Student student = new Student();
        student.setNume(studentAddressDto.getNume());
        student.setPrenume(studentAddressDto.getPrenume());
        student.setCnp(studentAddressDto.getCnp());

        Adresa adresa = new Adresa();
        adresa.setStrada(studentAddressDto.getAdresa().getStrada());
        adresa.setNumar(studentAddressDto.getAdresa().getNumar());
        adresa.setLocalitate(studentAddressDto.getAdresa().getLocalitate());
        student.setAdresa(adresa);

        studentRepository.save(student);
        adresaRepository.save(adresa);

        return "Studentul " + student.getNume() + " a fost adaugat cu succes!";
    }

    @PostMapping("/enroll")
    public String enrollStudentInMaterie(@RequestParam Integer studentId, @RequestParam("materieId") Integer materieId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Studentul nu există!"));

        Materie materie = materieRepository.findById(materieId)
                .orElseThrow(() -> new RuntimeException("Materia nu există!"));

        studentiToMaterieRepository.save(new StudentiToMaterie(null, student, materie));

        return "Studentul " + student.getNume() + " a fost înscris la materia " + materie.getNume();
    }
    @PutMapping("/{studentId}/adresa")
    public String updateAdress(@RequestBody Adresa adresa, @PathVariable Integer studentId){
        Adresa adresa1 = adresaRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Adresa nu există!"));
        adresa1.setStrada(adresa.getStrada());
        adresa1.setNumar(adresa.getNumar());
        adresa1.setLocalitate(adresa.getLocalitate());
        adresaRepository.save(adresa1);
        return "Adresa a fost actualizata cu succes!";
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Integer studentId) {
        studentRepository.deleteById(studentId);
        return "Studentul a fost sters cu succes!";
    }
}
