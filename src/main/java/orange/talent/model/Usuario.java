package orange.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
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

    public Usuario() {
    }

    public Usuario(Long cpf, @NotEmpty(message = "email é obrigatório") String email, @NotEmpty(message = "nome é obrigatório") String nome, String dtNascimento) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
}
