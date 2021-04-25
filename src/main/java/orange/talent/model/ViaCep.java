package orange.talent.model;

public class ViaCep {

    private String cep;

    private String logradouro;

    private String localidade;

    private String bairro;

    public ViaCep() {
    }

    public ViaCep(String cep, String logradouro, String localidade, String bairro) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.localidade = localidade;
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
