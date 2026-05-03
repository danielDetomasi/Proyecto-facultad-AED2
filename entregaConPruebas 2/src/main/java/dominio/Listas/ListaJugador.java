package dominio.Listas;

import dominio.Nodos.NodoListaJugador;

public class ListaJugador {
    private NodoListaJugador inicio;

    public NodoListaJugador getInicio() {
        return inicio;
    }

    public void setInicio(NodoListaJugador dato) {
        this.inicio = dato;
    }

    public void agregarInicio(NodoListaJugador datoInsertar){
        agregarInicioRec(datoInsertar, inicio);
    }

    private void agregarInicioRec(NodoListaJugador datoInsertar, NodoListaJugador n) {
        if(n == null){
           setInicio(datoInsertar);
        }else{
            NodoListaJugador aux = n;
            this.setInicio(datoInsertar);
            datoInsertar.setSig(aux);
        }
    }

    public String listarDatos(){
        return listarDatosRec(inicio);
    }

    private String listarDatosRec(NodoListaJugador n){
        if(n == null){
            return "";
        }else{
            String dato = n.getJugador().toString();

            if(n.getSig() == null){
                return dato + listarDatosRec(n.getSig());
            }else{
                return dato + "|" + listarDatosRec(n.getSig());
            }
        }
    }

}
