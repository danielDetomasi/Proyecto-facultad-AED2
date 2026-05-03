package dominio.Clases;

import java.util.Objects;

public class Sucursal implements Comparable<Sucursal>{
    private String codigo;
    private String nombre;

    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return codigo +";" + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj ==null || getClass() != obj.getClass()) return false;

        Sucursal s =(Sucursal) obj;
        return Objects.equals(codigo, s.codigo);
    }

    @Override
    public int compareTo(Sucursal s) {
        return this.codigo.compareTo(s.codigo);
    }

}
