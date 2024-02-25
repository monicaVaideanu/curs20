package app.curs20.repositories;

import app.curs20.enitities.StudentiToMaterie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentiToMaterieRepository extends JpaRepository<StudentiToMaterie, Integer> {
}
