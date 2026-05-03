package dominio.Clases;

import dominio.Arboles.ABBJugador;

public class Equipo implements Comparable<Equipo>{
    private String nombre;
    private String manager;
    private int cantJugadores;
    private ABBJugador jugadores;

    public Equipo(String nombre,String manager,int cant) {
        this.nombre = nombre;
        this.manager = manager;
        this.cantJugadores= cant;
        this.jugadores = new ABBJugador();
    }

    public ABBJugador getJugadores() {
        return jugadores;
    }

    public void setJugadores(ABBJugador jugadores) {
        this.jugadores = jugadores;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantJugadores() {
        return cantJugadores;
    }

    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }

    @Override
    public int compareTo(Equipo e) {
        return this.nombre.compareTo(e.getNombre());
    }

    @Override
    public String toString() {
        return (nombre + ";"
                + manager + ";"
                + cantJugadores);
    }
}
