package orange.talent.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import orange.talent.model.Endereco;
import orange.talent.model.Usuario;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdressView {

    private Usuario usuario;

    private List<AdressView> listaDeEnderecos;

}
