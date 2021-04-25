package orange.talent.service;

import orange.talent.exception.BadRequestException;
import orange.talent.model.Adress;
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
    public Adress saveEndereco(Adress adress) throws BadRequestException{
        try{
            validateCep(adress);
            return enderecoRepository.save(adress);
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }


    public ListAdressView listAll(long cpfUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCpf(cpfUsuario);
            List<Adress> bycpfUsuario = enderecoRepository.findBycpfUsuario(usuario);
            ListAdressView listAdressView = convertToView(bycpfUsuario);
            if(bycpfUsuario.isEmpty()) {
                throw new BadRequestException("Falha ao listar endereços, verifique se o CPF está correto.");
            }
            return listAdressView;
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException(badRequestException, "Falha ao listar endereços, verifique se o CPF está correto.");

        }

    }

    public ViaCep validateCep(Adress adress) {
        try {
            return this.viaCEPClient.buscaEndereco(adress.getCEP());
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }

    public List<AdressView> convertToViewEndereco(List<Adress> adressList) {
        List<AdressView> viewList = new ArrayList<>();
        for(Adress adress : adressList) {
            AdressView enderecoView = new AdressView();
            enderecoView.setId(adress.getId());
            enderecoView.setBairro(adress.getBairro());
            enderecoView.setCEP(adress.getCEP());
            enderecoView.setCidade(adress.getCidade());
            enderecoView.setEstado(adress.getEstado());
            enderecoView.setNumero(adress.getNumero());
            enderecoView.setComplemento(adress.getComplemento());
            enderecoView.setLogradouro(adress.getLogradouro());
            viewList.add(enderecoView);
        }
        return viewList;
    }


    public ListAdressView convertToView(List<Adress> adresses) {
        ListAdressView listAdressView = new ListAdressView();
        List<AdressView> enderecoView = convertToViewEndereco(adresses);
        listAdressView.setListaDeEnderecos(enderecoView);
        listAdressView.setUsuario(adresses.get(0).getCpfUsuario());
        return listAdressView;
    }

}
