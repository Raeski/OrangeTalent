package orange.talent.service;

import lombok.RequiredArgsConstructor;
import orange.talent.exception.BadRequestException;
import orange.talent.model.Endereco;
import orange.talent.model.Usuario;
import orange.talent.model.ViaCep;
import orange.talent.model.view.ResponseListAdressView;
import orange.talent.model.view.ResponseEnderecoView;
import orange.talent.repository.EnderecoRepository;
import orange.talent.repository.UsuarioRepository;
import orange.talent.viaCep.ViaCEPClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final UsuarioRepository usuarioRepository;

    private final EnderecoRepository enderecoRepository;

    private final ViaCEPClient viaCEPClient;

    @Transactional
    public Usuario saveUsuario(Usuario usuario) throws BadRequestException{
        try{
            Optional<Usuario> byId = usuarioRepository.findById(usuario.getCpf());
            List<Usuario> byEmail = usuarioRepository.findByEmail(usuario.getEmail());

            if(!byId.isPresent() && byEmail.isEmpty()) {
                usuarioRepository.save(usuario);
                return usuario;
            } else if ( byId.isPresent()) {
                throw new BadRequestException("CPF já cadastrado");
            } else {
                throw new BadRequestException("E-mail já cadastrado");
            }
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException(badRequestException, badRequestException.getMessage());
        }
    }

    @Transactional
    public Endereco saveEndereco(Endereco endereco) throws BadRequestException{
        try{
            validateCep(endereco);
            enderecoRepository.save(endereco);
            return endereco;
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }

    public ViaCep validateCep(Endereco endereco) {
        try {
            return this.viaCEPClient.buscaEndereco(endereco.getCEP());

        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }

    public ResponseListAdressView listAll(long cpfUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCpf(cpfUsuario);

            List<Endereco> bycpfUsuario = enderecoRepository.findBycpfUsuario(usuario);

            ResponseListAdressView responseListAdressView = convertToView(bycpfUsuario);

            if(bycpfUsuario.isEmpty()) {
                throw new BadRequestException("Falha ao listar endereços, verifique se o CPF está correto.");
            }
            return responseListAdressView;

        } catch (BadRequestException badRequestException) {
            throw new BadRequestException(badRequestException, "Falha ao listar endereços, verifique se o CPF está correto.");

        }

    }

    public List<ResponseEnderecoView> convertToViewEndereco(List<Endereco> enderecoList) {


        List<ResponseEnderecoView> viewList = new ArrayList<>();
        for(Endereco endereco: enderecoList) {
            ResponseEnderecoView responseEnderecoView = new ResponseEnderecoView();
            responseEnderecoView.setBairro(endereco.getBairro());
            responseEnderecoView.setCEP(endereco.getCEP());
            responseEnderecoView.setCidade(endereco.getCidade());
            responseEnderecoView.setEstado(endereco.getEstado());
            responseEnderecoView.setNumero(endereco.getNumero());
            responseEnderecoView.setComplemento(endereco.getComplemento());
            responseEnderecoView.setLogradouro(endereco.getLogradouro());
            viewList.add(responseEnderecoView);
        }
        return viewList;
    }


    public ResponseListAdressView convertToView(List<Endereco> enderecos) {
        ResponseListAdressView responseListAdressView = new ResponseListAdressView();
        List<ResponseEnderecoView> responseEnderecoView = convertToViewEndereco(enderecos);
        responseListAdressView.setListaDeEnderecos(responseEnderecoView);
        responseListAdressView.setUsuario(enderecos.get(0).getCpfUsuario());
        return responseListAdressView;
    }

}
