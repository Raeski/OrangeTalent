package orange.talent.service;

import lombok.RequiredArgsConstructor;
import orange.talent.exception.BadRequestException;
import orange.talent.model.Endereco;
import orange.talent.model.Usuario;
import orange.talent.model.ViaCep;
import orange.talent.model.view.AdressView;
import orange.talent.model.view.ListAdressView;
import orange.talent.repository.EnderecoRepository;
import orange.talent.repository.UsuarioRepository;
import orange.talent.viaCep.ViaCEPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCEPClient viaCEPClient;

    @Transactional
    public Usuario saveUsuario(Usuario usuario) throws BadRequestException{
        try{
            Optional<Usuario> byId = usuarioRepository.findById(usuario.getCpf());
            List<Usuario> byEmail = usuarioRepository.findByEmail(usuario.getEmail());

            if(!byId.isPresent() && byEmail.isEmpty()) {
                return usuarioRepository.save(usuario);
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
            return enderecoRepository.save(endereco);
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }


    public ListAdressView listAll(long cpfUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCpf(cpfUsuario);
            List<Endereco> bycpfUsuario = enderecoRepository.findBycpfUsuario(usuario);
            ListAdressView listAdressView = convertToView(bycpfUsuario);
            if(bycpfUsuario.isEmpty()) {
                throw new BadRequestException("Falha ao listar endereços, verifique se o CPF está correto.");
            }
            return listAdressView;
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException(badRequestException, "Falha ao listar endereços, verifique se o CPF está correto.");

        }

    }

    public ViaCep validateCep(Endereco endereco) {
        try {
            return this.viaCEPClient.buscaEndereco(endereco.getCEP());
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }

    public List<AdressView> convertToViewEndereco(List<Endereco> enderecoList) {
        List<AdressView> viewList = new ArrayList<>();
        for(Endereco endereco: enderecoList) {
            AdressView enderecoView = new AdressView();
            enderecoView.setId(endereco.getId());
            enderecoView.setBairro(endereco.getBairro());
            enderecoView.setCEP(endereco.getCEP());
            enderecoView.setCidade(endereco.getCidade());
            enderecoView.setEstado(endereco.getEstado());
            enderecoView.setNumero(endereco.getNumero());
            enderecoView.setComplemento(endereco.getComplemento());
            enderecoView.setLogradouro(endereco.getLogradouro());
            viewList.add(enderecoView);
        }
        return viewList;
    }


    public ListAdressView convertToView(List<Endereco> enderecos) {
        ListAdressView listAdressView = new ListAdressView();
        List<AdressView> enderecoView = convertToViewEndereco(enderecos);
        listAdressView.setListaDeEnderecos(enderecoView);
        listAdressView.setUsuario(enderecos.get(0).getCpfUsuario());
        return listAdressView;
    }

}
