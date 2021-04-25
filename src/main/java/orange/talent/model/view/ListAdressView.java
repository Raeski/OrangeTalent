package orange.talent.model.view;

import orange.talent.model.User;

import java.util.List;


public class ListAdressView {

    private User user;

    private List<AdressView> listaDeEnderecos;

    public ListAdressView() {
    }

    public ListAdressView(User user, List<AdressView> listaDeEnderecos) {
        this.user = user;
        this.listaDeEnderecos = listaDeEnderecos;
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User user) {
        this.user = user;
    }

    public List<AdressView> getListaDeEnderecos() {
        return listaDeEnderecos;
    }

    public void setListaDeEnderecos(List<AdressView> listaDeEnderecos) {
        this.listaDeEnderecos = listaDeEnderecos;
    }
}
