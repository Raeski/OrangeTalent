package orange.talent.model.view;

import orange.talent.model.Usuario;

import java.util.List;


public class ListAdressView {

    private Usuario usuario;

    private List<AdressView> listaDeEnderecos;

    public ListAdressView() {
    }

    public ListAdressView(Usuario usuario, List<AdressView> listaDeEnderecos) {
        this.usuario = usuario;
        this.listaDeEnderecos = listaDeEnderecos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<AdressView> getListaDeEnderecos() {
        return listaDeEnderecos;
    }

    public void setListaDeEnderecos(List<AdressView> listaDeEnderecos) {
        this.listaDeEnderecos = listaDeEnderecos;
    }
}
