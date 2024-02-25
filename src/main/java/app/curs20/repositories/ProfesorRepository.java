package app.curs20.repositories;

import app.curs20.enitities.Materie;
import app.curs20.enitities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    Optional<Profesor> findById(Integer id);
}
