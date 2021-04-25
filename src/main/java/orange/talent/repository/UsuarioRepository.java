package orange.talent.repository;

import orange.talent.exception.BadRequestException;
import orange.talent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByEmail(String email);

    List<Usuario> findByCpf(Long cpf) throws BadRequestException;
}
