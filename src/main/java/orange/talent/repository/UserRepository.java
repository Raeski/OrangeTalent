package orange.talent.repository;

import orange.talent.exception.BadRequestException;
import orange.talent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    List<User> findByCpf(Long cpf) throws BadRequestException;
}
