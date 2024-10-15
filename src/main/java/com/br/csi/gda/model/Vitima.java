package br.csi.model;


public class Vitima extends Usuario{
    private String ultimoEnd;
    private boolean presente;
    private Riscos riscos;

    public Vitima(Usuario usuario, Riscos riscos, String ultimoEnd, boolean presente) {
        super(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getData_cad(), usuario.getIdade(), usuario.getPermissao());
        this.ultimoEnd = ultimoEnd;
        this.presente = presente;
        this.riscos = riscos;
    }

    public Vitima(String nome, String cpf, int idade, Permissao permissao, String ultimoEnd, boolean presente) {
        super(nome, cpf, idade, permissao);
        this.ultimoEnd = ultimoEnd;
        this.presente = presente;
    }

    public Riscos getRiscos() {
        return riscos;
    }

    public void setRiscos(Riscos riscos) {
        this.riscos = riscos;
    }

    public String getUltimoEnd() {
        return ultimoEnd;
    }

    public void setUltimoEnd(String ultimoEnd) {
        this.ultimoEnd = ultimoEnd;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
