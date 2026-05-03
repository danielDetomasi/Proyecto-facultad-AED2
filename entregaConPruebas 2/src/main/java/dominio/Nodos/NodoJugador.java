package dominio.Nodos;

import dominio.Clases.Jugador;

public class NodoJugador{
    private Jugador jugador;
    private NodoJugador izq,der;

    public NodoJugador(Jugador j) {
        this.jugador=j;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public NodoJugador getIzq() {
        return izq;
    }

    public void setIzq(NodoJugador izq) {
        this.izq = izq;
    }

    public NodoJugador getDer() {
        return der;
    }

    public void setDer(NodoJugador der) {
        this.der = der;
    }

    @Override
    public String toString() {
        return jugador.toString();
    }
}
