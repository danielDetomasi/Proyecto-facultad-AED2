package dominio.Nodos;

import dominio.Clases.Equipo;

public class NodoEquipo {
    private Equipo equipo;
    private NodoEquipo izq,der;


    public NodoEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public NodoEquipo getDer() {
        return der;
    }

    public void setDer(NodoEquipo der) {
        this.der = der;
    }

    public NodoEquipo getIzq() {
        return izq;
    }

    public void setIzq(NodoEquipo izq) {
        this.izq = izq;
    }

    @Override
    public String toString() {
        return equipo.toString();
    }
}
