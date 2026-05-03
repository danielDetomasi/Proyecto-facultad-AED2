package dominio.Nodos;

import dominio.Clases.Jugador;

public class NodoListaJugador{
    private Jugador jugador;

    private NodoListaJugador sig;

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public NodoListaJugador getSig() {
        return sig;
    }

    public void setSig(NodoListaJugador sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return jugador.toString();
    }
}
