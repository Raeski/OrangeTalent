package orange.talent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViaCep {

    private String cep;

    private String logradouro;

    private String localidade;

    private String bairro;

}
