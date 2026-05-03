package dominio.Listas;

import dominio.Nodos.NodoListaEquipo;

public class ListaEquipo {
    private NodoListaEquipo inicio;

    public NodoListaEquipo getInicio() {
        return inicio;
    }

    public void setInicio(NodoListaEquipo dato) {
        this.inicio = dato;
    }

    public void agregarInicio(NodoListaEquipo dato){
        agregarInicioRec(dato, inicio);
    }

    private void agregarInicioRec(NodoListaEquipo dato, NodoListaEquipo n) {
        if(n == null){
            setInicio(dato);
        }else{
            NodoListaEquipo aux = n;
            this.setInicio(dato);
            dato.setSig(aux);
        }
    }

    public String listarDatos(){
        return listarDatosRec(inicio);
    }

    private String listarDatosRec(NodoListaEquipo n) {
        if(n == null){
            return "";
        }else{
            String dato = n.getEquipo().toString();

            if(n.getSig() == null){
                return dato + listarDatosRec(n.getSig());
            }else{
                return dato + "|" + listarDatosRec(n.getSig());
            }
        }
    }
}
