package app.curs20.controllers;

import app.curs20.enitities.Materie;
import app.curs20.enitities.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.curs20.repositories.ProfesorRepository;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/profesor")
public class ProfesorController {
    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorController(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Profesor>> getProfesor(@PathVariable Integer id) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()) {
            return ResponseEntity.ok(profesor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/materii/{id}")
    public Set<Materie> getMateriiByProfesorId(@PathVariable Integer id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesorul nu există!"));
        return profesor.getMateriiPredate();
    }
}
