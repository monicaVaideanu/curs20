package app.curs20.repositories;

import app.curs20.enitities.Materie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterieRepository extends JpaRepository<Materie, Integer> {
}
