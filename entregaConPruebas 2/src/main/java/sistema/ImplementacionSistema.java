package sistema;

import dominio.Arboles.ABBEquipo;
import dominio.Arboles.ABBJugador;
import dominio.Clases.Equipo;
import dominio.Clases.Jugador;
import dominio.Clases.Sucursal;
import dominio.Grafos.Grafo;
import dominio.Nodos.NodoEquipo;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    ABBJugador abbJugador;
    ABBEquipo abbEquipo ;
    ABBJugador abbProfesional;
    ABBJugador abbEstandar;
    ABBJugador abbPrincipiante;

    Grafo grafo;
    int maximoSucursales;

    public ImplementacionSistema(){}

    @Override
    public Retorno inicializarSistema(int maxSucursales) {

        if(maxSucursales <= 3){
            return Retorno.error1("El maximo de sucursales tiene que ser mayor a 3");
        }

        maximoSucursales = maxSucursales;

        abbJugador = new ABBJugador();
        abbEquipo = new ABBEquipo();
        abbProfesional = new ABBJugador();
        abbEstandar = new ABBJugador();
        abbPrincipiante = new ABBJugador();
        grafo = new Grafo(maxSucursales);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if(estaVacioElString(alias) || estaVacioElString(nombre) || estaVacioElString(apellido) || categoria == null){
            return Retorno.error1("Todos los datos deben ser completados");
        }else if(abbJugador.existeJugador(alias)){
            return Retorno.error2("Ya hay un jugador con ese alias");
        }else{
            Jugador j = new Jugador(alias,nombre,apellido,categoria);
            abbJugador.registrarJugador(j);

            switch(categoria){
                case PROFESIONAL :
                    abbProfesional.registrarJugador(j);
                    break;
                case PRINCIPIANTE:
                    abbPrincipiante.registrarJugador(j);
                    break;
                case ESTANDARD:
                    abbEstandar.registrarJugador(j);
                    break;
            }

            return Retorno.ok();
        }
    }

    @Override
    public Retorno buscarJugador(String alias){
        if(estaVacioElString(alias)){
            return Retorno.error1("El alias no puede ser vacia");
        }else if(abbJugador.buscarJugador(alias).isEmpty()){
            return Retorno.error2("No hay un jugador con ese alias");
        }else{
            return Retorno.ok(abbJugador.cantidadHastaJugador(alias) ,abbJugador.buscarJugador(alias));
        }
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.ok(abbJugador.listarJugadoresAscendete());
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria){
        String listar = null;

        if(unaCategoria == Categoria.PRINCIPIANTE){
            listar = abbPrincipiante.listarJugadoresAscendete();
        }else if(unaCategoria == Categoria.PROFESIONAL){
            listar = abbProfesional.listarJugadoresAscendete();
        }else{
            listar = abbEstandar.listarJugadoresAscendete();
        }

        return Retorno.ok(listar);
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        if(estaVacioElString(nombre) || estaVacioElString(manager)){
            return Retorno.error1("Todos los datos deben completarse");
        }else if(abbEquipo.existeEquipo(nombre)){
            return Retorno.error2("Ya existe un equipo con este nombre");
        }else{
            Equipo e = new Equipo(nombre, manager, 0);

            abbEquipo.registrarEquipo(e);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {

        if(estaVacioElString(nombreEquipo) || estaVacioElString(aliasJugador)){
            return Retorno.error1("Todos los datos deben completarse");
        }else{
            Equipo e  = abbEquipo.buscarEquipoNombre(nombreEquipo);
            Jugador j = abbJugador.devolverJugador(aliasJugador);

            if(e == null){
                return Retorno.error2("No existen equipo con ese nombre");
            }else if(j == null){
                return Retorno.error3("No existe un jugador con ese alias");
            }else if(e.getCantJugadores() == 5){
                return Retorno.error4("El equipo ya cuenta con 5 jugadores");
            }else if(j.getCategoria() != Categoria.PROFESIONAL){
                return Retorno.error5("El jugador ingresado no es profesional");
            }else if(j.getNombreEquipo() != null){
                return Retorno.error6("El jugador ya pertenece a otro equipo");
            }else{
                abbEquipo.agregarJugadorAEquipo(j, e);
                e.setCantJugadores(e.getCantJugadores() + 1);

                return Retorno.ok();
            }
        }
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {

        if(estaVacioElString(nombreEquipo)){
            return Retorno.error1("El nombre del equipo no puede ser vacio");
        }else{
            Equipo e = abbEquipo.buscarEquipoNombre(nombreEquipo);

            if(e == null){
                return Retorno.error2("El equipo ingresado no existe");
            }

            return Retorno.ok(e.getJugadores().listarJugadoresAscendete());
        }
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.ok(abbEquipo.listarEquipos());
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {

        if(maximoSucursales == grafo.getCantSuc()){
            return Retorno.error1("Ya se alcanzo el maximo de sucursales");
        }else if(estaVacioElString(codigo) || estaVacioElString(nombre)){
            return Retorno.error2("Todos los datos deben completarse");
        }else {
            Sucursal s = new Sucursal(codigo,nombre);

            if(grafo.buscarIndice(s) >= 0){
                return Retorno.error3("Ya existe esa sucursal");
            }

            grafo.agregarVertice(s);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0){
            return Retorno.error1("La latencia debe ser positiva");
        }else if(estaVacioElString(codigoSucursal1) || estaVacioElString(codigoSucursal2)){
            return Retorno.error2("Todos los datos deben completarse");
        }else if(grafo.buscarSucursalPorCodigo(codigoSucursal1) == null || grafo.buscarSucursalPorCodigo(codigoSucursal2) == null){
            return Retorno.error3("No existen las sucursales");
        }else if(grafo.existeConexion(codigoSucursal1, codigoSucursal2)){
            return Retorno.error4("Ya existe una conexion entre esas sucursales");
        }else{
            Sucursal sUno = grafo.buscarSucursalPorCodigo(codigoSucursal1);
            Sucursal sDos = grafo.buscarSucursalPorCodigo(codigoSucursal2);

            grafo.registrarArista(sUno,sDos, latencia);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia){
        if(latencia < 0){
            return Retorno.error1("La latencia debe ser positiva");
        }else if(estaVacioElString(codigoSucursal1) || estaVacioElString(codigoSucursal2)){
            return Retorno.error2("Todos los datos deben completarse");
        }else if(grafo.buscarSucursalPorCodigo(codigoSucursal1) == null || grafo.buscarSucursalPorCodigo(codigoSucursal2) == null){
            return Retorno.error3("No existen las sucursales");
        }else if(!grafo.existeConexion(codigoSucursal1, codigoSucursal2)){
            return Retorno.error4("No existe una conexion entre esas sucursales");
        }else{

            grafo.actulizarConexion(codigoSucursal1,codigoSucursal2, latencia);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        if(estaVacioElString(codigoSucursal)){
            return Retorno.error1("Se deben completar todos los campos");
        } else if(grafo.buscarSucursalPorCodigo(codigoSucursal) == null){
            return Retorno.error2("No existe una sucursal con ese codigo");
        }else{
            return Retorno.ok(grafo.analizarSuc(codigoSucursal));
        }
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        if (estaVacioElString(codigoSucursalAnfitriona)) {
            return Retorno.error1("Todos los datos deben completarse");
        } else if (this.grafo.buscarSucursalPorCodigo(codigoSucursalAnfitriona) == null) {
            return Retorno.error2("La sucursal ingresada no existe");
        } else if(latenciaLimite <= 0){
            return Retorno.error3("La latencia ingresada no puede ser igual o menor a cero");
        }else{
            return Retorno.ok(grafo.costoMasAlto(codigoSucursalAnfitriona, latenciaLimite), grafo.resultadoDijkstra(codigoSucursalAnfitriona, latenciaLimite));
        }
    }

    private static boolean estaVacioElString(String dato) {
        return dato == null || dato.isEmpty();
    }
}
