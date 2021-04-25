package orange.talent.repository;

import orange.talent.exception.BadRequestException;
import orange.talent.model.Adress;
import orange.talent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdressRepository extends JpaRepository<Adress, Long> {

    List<Adress> findBycpfUsuario(User cpfUser) throws BadRequestException;
}
