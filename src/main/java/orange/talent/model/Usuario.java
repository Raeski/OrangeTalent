package orange.talent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @Column(unique = true)
    private Long cpf;

    @NotEmpty(message = "email é obrigatório")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "nome é obrigatório")
    private String nome;

    private String dtNascimento;

}
