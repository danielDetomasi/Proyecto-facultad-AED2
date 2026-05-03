package dominio.Listas;

import dominio.Clases.Sucursal;
import dominio.Nodos.NodoSucursal;

public class ListaSucursal {
    private NodoSucursal inicio;
    private NodoSucursal fin;

    public NodoSucursal getInicio() {
        return inicio;
    }

    public void setInicio(NodoSucursal inicio) {
        this.inicio = inicio;
    }

    public NodoSucursal getFin() {
        return fin;
    }

    public void setFin(NodoSucursal fin) {
        this.fin = fin;
    }

    private boolean esVacia(){
        return this.getInicio()==null;
    }

    private void agregarInicio(NodoSucursal dato){
        agregarInicioRec(inicio,dato);
    }

    private void agregarInicioRec(NodoSucursal n, NodoSucursal dato) {
        if(esVacia()){
            setInicio(dato);
            setFin(dato);
        }else{
            dato.setSig(inicio);
            inicio = dato;
        }
    }

    public void agregarOrdenado(Sucursal s){
        NodoSucursal dato = new NodoSucursal(s);

        if(esVacia() || dato.getSucursal().compareTo(inicio.getSucursal()) < 0){
            agregarInicio(dato);
        }else{
            if(dato.getSucursal().compareTo(this.inicio.getSucursal()) < 0){
                agregarFinal(dato);
            }else{
                NodoSucursal aux = getInicio();

                while(aux.getSig() != null && aux != null && aux.getSig().getSucursal().compareTo(dato.getSucursal()) < 0){
                    aux=aux.getSig();
                }

                dato.setSig(aux.getSig());
                aux.setSig(dato);
            }
        }
    }

    private void agregarFinal(NodoSucursal dato){
        if(!esVacia()){
            fin.setSig(dato);
            fin = dato;
        }else{
            inicio = dato;
            fin = dato;
        }
    }

    public String listarDatos(){
        return listarDatosRec(inicio);
    }

    private String listarDatosRec(NodoSucursal n){
        if(n == null){
            return "";
        }else{
            String dato = n.getSucursal().toString();

            if(n.getSig() == null){
                return dato + listarDatosRec(n.getSig());
            }else{
                return dato + "|" + listarDatosRec(n.getSig());
            }
        }
    }

}
