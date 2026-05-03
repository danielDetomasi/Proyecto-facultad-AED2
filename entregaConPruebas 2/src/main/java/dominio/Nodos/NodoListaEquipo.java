package dominio.Nodos;

import dominio.Clases.Equipo;

public class NodoListaEquipo {
    private Equipo equipo;

    private NodoListaEquipo sig;

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public NodoListaEquipo getSig() {
        return sig;
    }

    public void setSig(NodoListaEquipo sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return equipo.toString();
    }
}
