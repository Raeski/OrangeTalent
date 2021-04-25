package orange.talent.repository;

import orange.talent.exception.BadRequestException;
import orange.talent.model.Endereco;
import orange.talent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findBycpfUsuario(Usuario cpfUsuario) throws BadRequestException;
}
