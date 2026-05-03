package dominio.Nodos;

import dominio.Clases.Sucursal;

public class NodoSucursal{
    private Sucursal sucursal;
    private NodoSucursal sig;

    public NodoSucursal(Sucursal sucursal) {
       this.sucursal = sucursal;
    }

    public NodoSucursal getSig() {
        return sig;
    }

    public void setSig(NodoSucursal sig) {
        this.sig = sig;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return sucursal.toString();
    }
}
