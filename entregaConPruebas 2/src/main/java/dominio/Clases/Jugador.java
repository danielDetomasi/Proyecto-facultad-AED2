package dominio.Clases;

import interfaz.Categoria;

public class Jugador implements Comparable<Jugador>{
    private String alias;
    private String nombre;
    private String apellido;
    private Categoria categoria;
    private String nombreEquipo;

    public Jugador(String alias, String nombre, String apellido, Categoria categoria) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    @Override
    public String toString() {
        return (alias + ";"
                + nombre + ";"
                + apellido + ";"
                + categoria.toString());
    }

    @Override
    public int compareTo(Jugador j) {
        return this.alias.compareTo(j.alias);
    }


}
