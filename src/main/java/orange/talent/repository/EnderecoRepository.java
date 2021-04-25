package orange.talent.repository;

import orange.talent.exception.BadRequestException;
import orange.talent.model.Adress;
import orange.talent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Adress, Long> {

    List<Adress> findBycpfUsuario(Usuario cpfUsuario) throws BadRequestException;
}
