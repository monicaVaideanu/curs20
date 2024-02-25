package app.curs20.controllers;

import app.curs20.enitities.Student;
import app.curs20.repositories.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @PersistenceContext
    EntityManager entityManager;

    private final StudentRepository studentRepository;

    @Autowired
    public DemoController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = "/student/all")
    public @ResponseBody Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/student")
    public @ResponseBody Optional<Student> getStudentByNumeAndPrenume(
            @RequestParam(name = "nume") String nume,
            @RequestParam(name = "prenume", required = false) String prenume
    ) {
        if (prenume == null) {
            return studentRepository.findByNume(nume);
        }
        return studentRepository.findByNumeAndPrenume(nume, prenume);
    }

    @GetMapping(path = "/student/localitate")
    public @ResponseBody List<Optional<Student>> getStudentByNumeAndLocalitate(
            @RequestParam(name = "nume") String nume,
            @RequestParam(name = "localitate") String localitate
    ) {
        return studentRepository.findByNumeAndLocalitate(nume, localitate);
    }

    @GetMapping(path = "/student/cnp/{cnp}")
    public @ResponseBody Iterable<Student> getStudent(@PathVariable String cnp) {
        Session session = (Session) entityManager.getDelegate();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cnp"), cnp));

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}