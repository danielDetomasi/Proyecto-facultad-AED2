package dominio.Arboles;


import dominio.Clases.Jugador;
import dominio.Listas.ListaJugador;
import dominio.Nodos.NodoJugador;
import dominio.Nodos.NodoListaJugador;

public class ABBJugador {
    private NodoJugador raiz;

    public NodoJugador getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoJugador raiz) {
        this.raiz = raiz;
    }

    public void registrarJugador(Jugador j){
        NodoJugador n = new NodoJugador(j);
        this.raiz = registrarJugadorRec(raiz,n);
    }

    private NodoJugador registrarJugadorRec(NodoJugador n, NodoJugador j) {
        if(n == null){
            n = j;
            return n;
        }else if(esMayor(j.getJugador().getAlias(), n.getJugador().getAlias())){
            n.setDer(registrarJugadorRec(n.getDer(), j));
            return n;
        }else{
            n.setIzq(registrarJugadorRec(n.getIzq(), j));
            return n;
        }
    }

    public String buscarJugador(String alias){
        return buscarJugadorRec(raiz, alias);
    }

    public int cantidadHastaJugador(String alias){
        return cantidadNodosHastaJugador(raiz, alias, 1);
    }

    private String buscarJugadorRec(NodoJugador n, String alias){
        if(n == null){
            return "";
        }else if(jugadorEncontrado(alias, n.getJugador().getAlias())){
            String ali = n.getJugador().getAlias();
            String nom = n.getJugador().getNombre();
            String ape = n.getJugador().getApellido();
            String cat = n.getJugador().getCategoria().name();

            return ali + ";" + nom + ";" + ape + ";" + cat;
        }else if(esMayor(alias, n.getJugador().getAlias())){
            return buscarJugadorRec(n.getDer(), alias);
        }else{
            return buscarJugadorRec(n.getIzq(), alias);
        }
    }

    private int cantidadNodosHastaJugador(NodoJugador n,String alias, int contador){
        if(n == null){
            return 0;
        }else if(jugadorEncontrado(alias, n.getJugador().getAlias())){
            return contador;
        }else if(esMayor(alias, n.getJugador().getAlias())){
            return cantidadNodosHastaJugador(n.getDer(), alias, contador + 1);
        }else{
            return cantidadNodosHastaJugador(n.getIzq(), alias, contador + 1);
        }
    }

    public Jugador devolverJugador(String alias){
        NodoJugador nodo = devolverJugadorRec(raiz, alias);
        if(nodo==null)return  null;

        Jugador j = nodo.getJugador();
        return j;
    }

    private NodoJugador devolverJugadorRec(NodoJugador n, String alias) {
        if(n == null){
            return null;
        }else if(esMenor(alias,n.getJugador().getAlias())){
                NodoJugador izq = devolverJugadorRec(n.getIzq(), alias);

                return izq;
        }else if(esMayor(alias, n.getJugador().getAlias())){
                NodoJugador der = devolverJugadorRec(n.getDer(), alias);

                return der;
        }

        return n;
    }

    public boolean existeJugador(String alias){
        return existeJugadorRec(raiz,alias);
    }

    private boolean existeJugadorRec(NodoJugador n, String alias) {
        if(n == null){
            return false;
        }else if(jugadorEncontrado(alias, n.getJugador().getAlias())){
            return true;
        }else if(esMayor(alias, n.getJugador().getAlias())){
            return existeJugadorRec(n.getDer(), alias);
        }else{
            return existeJugadorRec(n.getIzq(), alias);
        }
    }

    public String listarJugadoresAscendete(){
        ListaJugador l = new ListaJugador();

        listarJugadoresAscendeteRec(raiz, l);

        return l.listarDatos();
    }

    private void listarJugadoresAscendeteRec(NodoJugador n,ListaJugador l) {
        if(n == null){
            return;
        }

        listarJugadoresAscendeteRec(n.getDer(), l);

        NodoListaJugador nodoLista = new NodoListaJugador();
        nodoLista.setJugador(n.getJugador());

        l.agregarInicio(nodoLista);

        listarJugadoresAscendeteRec(n.getIzq(), l);
    }

    private boolean jugadorEncontrado(String aliasUno, String aliasDos){return aliasUno.compareTo(aliasDos) == 0;}
    private boolean esMenor(String aliasUno, String aliasDos){
        return aliasUno.compareTo(aliasDos) < 0;
    }
    private boolean esMayor(String aliasUno,String aliasDos){
        return aliasUno.compareTo(aliasDos) > 0;
    }
}
