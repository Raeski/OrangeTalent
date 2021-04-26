package orange.talent.service;

import orange.talent.exception.BadRequestException;
import orange.talent.model.Adress;
import orange.talent.model.User;
import orange.talent.model.ViaCep;
import orange.talent.model.view.AdressView;
import orange.talent.model.view.ListAdressView;
import orange.talent.repository.AdressRepository;
import orange.talent.repository.UserRepository;
import orange.talent.viaCep.ViaCEPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ViaCEPClient viaCEPClient;

    @Transactional
    public User saveUsuario(User user) throws BadRequestException{
        try{
            Optional<User> byId = userRepository.findById(user.getCpf());
            List<User> byEmail = userRepository.findByEmail(user.getEmail());

            if(!byId.isPresent() && byEmail.isEmpty()) {
                return userRepository.save(user);
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
            return adressRepository.save(adress);
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }

    public ViaCep validateCep(Adress adress) {
        try {
            return this.viaCEPClient.buscaEndereco(adress.getCEP());
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException("CEP inválido");
        }
    }

    public ListAdressView listAll(long cpfUsuario) {
        try {
            User user = new User();
            user.setCpf(cpfUsuario);
            List<Adress> bycpfUsuario = adressRepository.findBycpfUsuario(user);
            ListAdressView listAdressView = convertToView(bycpfUsuario);
            if(bycpfUsuario.isEmpty()) {
                throw new BadRequestException("Falha ao listar endereços, verifique se o CPF está correto.");
            }
            return listAdressView;
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException(badRequestException, badRequestException.getMessage());
        }
    }


    public List<AdressView> convertToViewEndereco(List<Adress> adressList) {
        List<AdressView> viewList = new ArrayList<>();
        for(Adress adress : adressList) {
            AdressView adressView = new AdressView();
            adressView.setId(adress.getId());
            adressView.setBairro(adress.getBairro());
            adressView.setCEP(adress.getCEP());
            adressView.setCidade(adress.getCidade());
            adressView.setEstado(adress.getEstado());
            adressView.setNumero(adress.getNumero());
            adressView.setComplemento(adress.getComplemento());
            adressView.setLogradouro(adress.getLogradouro());
            viewList.add(adressView);
        }
        return viewList;
    }


    public ListAdressView convertToView(List<Adress> adresses) {
        try {
            ListAdressView listAdressView = new ListAdressView();
            List<AdressView> adressView = convertToViewEndereco(adresses);
            listAdressView.setListaDeEnderecos(adressView);
            if(adresses.size() <= 0) {
                throw new BadRequestException("Falha ao listar endereços, esse CPF não possue endereço cadastrado");
            }
            listAdressView.setUsuario(adresses.get(0).getCpfUsuario());
            return listAdressView;
        } catch (BadRequestException badRequestException) {
            throw new BadRequestException(badRequestException, badRequestException.getMessage());
        }

    }

}
