package orange.talent.controller;

import orange.talent.model.Adress;
import orange.talent.model.User;
import orange.talent.model.view.ListAdressView;
import orange.talent.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cadastro")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/usuario")
    public ResponseEntity<User> saveUsuario(@RequestBody @Valid User user) {
        return new ResponseEntity(registerService.saveUsuario(user), HttpStatus.CREATED);
    }

    @PostMapping("/endereco")
    public ResponseEntity<Adress> saveEndereco(@RequestBody Adress adress) {
        return new ResponseEntity(registerService.saveEndereco(adress), HttpStatus.CREATED);
    }

    @GetMapping(path= "/{cpfUsuario}")
    public ResponseEntity<ListAdressView> list(@PathVariable("cpfUsuario") long cpf) {
        return ResponseEntity.ok(registerService.listAll(cpf));
    }
}
