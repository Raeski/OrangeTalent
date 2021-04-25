package orange.talent.controller;

import orange.talent.model.Endereco;
import orange.talent.model.Usuario;
import orange.talent.model.view.ListAdressView;
import orange.talent.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity(cadastroService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/endereco")
    public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity(cadastroService.saveEndereco(endereco), HttpStatus.CREATED);
    }

    @GetMapping(path= "/{cpfUsuario}")
    public ResponseEntity<ListAdressView> list(@PathVariable("cpfUsuario") long cpf) {
        return ResponseEntity.ok(cadastroService.listAll(cpf));
    }
}
