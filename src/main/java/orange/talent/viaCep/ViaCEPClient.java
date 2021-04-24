package orange.talent.viaCep;

import orange.talent.model.ViaCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCEPClient {

    @GetMapping("/{cep}/json")
    ViaCep buscaEndereco(@PathVariable("cep") String cep);



}
