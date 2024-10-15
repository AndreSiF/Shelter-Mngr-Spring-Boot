package com.br.csi.gda.model;

public class Riscos {
    private boolean frio;
    private boolean nutricao;
    private boolean desidrat;
    private boolean machucado;

    public Riscos() {}

    public Riscos(boolean frio, boolean nutricao, boolean desidrat, boolean machucado) {
        this.frio = frio;
        this.nutricao = nutricao;
        this.desidrat = desidrat;
        this.machucado = machucado;
    }

    public boolean isFrio() {
        return frio;
    }

    public void setFrio(boolean frio) {
        this.frio = frio;
    }

    public boolean isNutricao() {
        return nutricao;
    }

    public void setNutricao(boolean nutricao) {
        this.nutricao = nutricao;
    }

    public boolean isDesidrat() {
        return desidrat;
    }

    public void setDesidrat(boolean desidrat) {
        this.desidrat = desidrat;
    }

    public boolean isMachucado() {
        return machucado;
    }

    public void setMachucado(boolean machucado) {
        this.machucado = machucado;
    }
}
