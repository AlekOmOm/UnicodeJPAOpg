package dk.kea.unicodejpaopg.repository;

import dk.kea.unicodejpaopg.model.Unicode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnicodeRepository extends JpaRepository<Unicode, Integer> {

}
