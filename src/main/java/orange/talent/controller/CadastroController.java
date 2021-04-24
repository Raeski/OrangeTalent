package orange.talent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import orange.talent.model.Endereco;
import orange.talent.model.Usuario;
import orange.talent.model.view.ResponseListAdressView;
import orange.talent.service.CadastroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cadastro")
@Log4j2
@RequiredArgsConstructor
public class CadastroController {

    private final CadastroService cadastroService;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity(cadastroService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/endereco")
    public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity(cadastroService.saveEndereco(endereco), HttpStatus.CREATED);
    }

    @GetMapping(path= "/{cpfUsuario}")
    public ResponseEntity<ResponseListAdressView> list(@PathVariable("cpfUsuario") long cpf) {
        return ResponseEntity.ok(cadastroService.listAll(cpf));
    }
}
