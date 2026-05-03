package dominio.Arboles;

import dominio.Clases.Equipo;
import dominio.Clases.Jugador;
import dominio.Listas.ListaEquipo;
import dominio.Nodos.NodoEquipo;
import dominio.Nodos.NodoListaEquipo;

public class ABBEquipo {
    private NodoEquipo raiz;

    public NodoEquipo getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoEquipo raiz) {
        this.raiz = raiz;
    }

    public void registrarEquipo(Equipo e){
        NodoEquipo n = new NodoEquipo(e);

        this.raiz = registrarEquipoRec(raiz,n);
    }

    private NodoEquipo registrarEquipoRec(NodoEquipo n, NodoEquipo e) {
        if(n == null){
            n = e ;
            return n;
        }else if(esMayor(e.getEquipo().getNombre(), n.getEquipo().getNombre())){

            n.setDer(registrarEquipoRec(n.getDer(), e));
            return n;
        }else{

            n.setIzq(registrarEquipoRec(n.getIzq(), e));
            return n;
        }
    }

    public String listarEquipos(){
        ListaEquipo l = new ListaEquipo();
        listarEquiposRec(raiz, l);

        return l.listarDatos();
    }

    private void listarEquiposRec(NodoEquipo n, ListaEquipo l) {
        if(n == null){
            return;
        }else{
            listarEquiposRec(n.getIzq(), l);

            NodoListaEquipo nodo = new NodoListaEquipo();
            nodo.setEquipo(n.getEquipo());

            l.agregarInicio(nodo);

            listarEquiposRec(n.getDer(), l);
        }
    }

    public boolean existeEquipo(String nombre){
        return existeEquipoRec(raiz,nombre);
    }

    private boolean existeEquipoRec(NodoEquipo n, String nombre) {
        if(n == null){
            return false;
        }else if(equipoEncontrado(n.getEquipo().getNombre(), nombre)){
            return true;
        }else if(esMayor(nombre, n.getEquipo().getNombre())){
            return existeEquipoRec(n.getDer(), nombre);
        }else{
            return existeEquipoRec(n.getIzq(), nombre);
        }
    }

    public void agregarJugadorAEquipo(Jugador j,Equipo e){
        agregarJugadorAEquipoRec(raiz, j, e);
    }

    private void agregarJugadorAEquipoRec(NodoEquipo n, Jugador j,Equipo e) {
        if(n == null){
            return;
        }else{
            e.getJugadores().registrarJugador(j);
            j.setNombreEquipo(n.getEquipo().getNombre());
        }
    }

    public Equipo buscarEquipoNombre(String nombre){
        return buscarEquipoNombreRec(nombre,raiz);
    }

    private Equipo buscarEquipoNombreRec(String nombre, NodoEquipo n) {
        if(n == null){
            return null;
        }else if(equipoEncontrado(n.getEquipo().getNombre(), nombre)){
            return n.getEquipo();
        }else if(esMenor(nombre, n.getEquipo().getNombre())){
            return buscarEquipoNombreRec(nombre, n.getIzq());
        }else{
            return buscarEquipoNombreRec(nombre, n.getDer());
        }
    }

    private boolean equipoEncontrado(String datoUno, String datoDos){
        return datoUno.compareTo(datoDos) == 0;
    }

    private boolean esMenor(String datoUno, String datoDos){
        return datoUno.compareTo(datoDos) < 0;
    }

    private boolean esMayor(String datoUno,String datoDos){
        return datoUno.compareTo(datoDos) > 0;
    }
}
