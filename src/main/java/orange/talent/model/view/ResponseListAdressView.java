package orange.talent.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import orange.talent.model.Usuario;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListAdressView {

    private Usuario usuario;

    private List<ResponseEnderecoView> listaDeEnderecos;

}
