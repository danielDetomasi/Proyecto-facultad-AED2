package sistema;

import interfaz.Categoria;
import interfaz.Sistema;


public class Main {
    public static void main(String [] args){
        Sistema sistema=new ImplementacionSistema();

        System.out.println("ERROR SISTEMA");
        System.out.println(sistema.inicializarSistema(1));//ERROR 1
        System.out.println(sistema.inicializarSistema(5));//OK

        //REGISTRO OK
        System.out.println("REGISTRO DE JUGADORES OK");
        System.out.println(sistema.registrarJugador("uno","jugadorUno","jugadorUno", Categoria.PROFESIONAL));//OK
        System.out.println(sistema.registrarJugador("dos","jugadorDos","JugadorDos", Categoria.PROFESIONAL));
        System.out.println(sistema.registrarJugador("tres","jugadorTres","jugadorTres", Categoria.PROFESIONAL));
        System.out.println(sistema.registrarJugador("cuatro","jugadorCuatro","jugadorCuatro", Categoria.PROFESIONAL));
        System.out.println(sistema.registrarJugador("vquinto","JugadorVCinco","JugadorVCInco", Categoria.PROFESIONAL));
        System.out.println(sistema.registrarJugador("elSexto","sexto","sexto", Categoria.PROFESIONAL));
        System.out.println(sistema.registrarJugador("dSeptimo","septimo","septimo", Categoria.PROFESIONAL));
        System.out.println(sistema.registrarJugador("fOctavo","octavo","octavo", Categoria.PRINCIPIANTE));

        //ERRORES 1 Y 2
        System.out.println("ERRORES REGISTRO JUGADOR");
        System.out.println(sistema.registrarJugador("","","",Categoria.ESTANDARD));
        System.out.println(sistema.registrarJugador("dos","error2","dos",Categoria.ESTANDARD));

        System.out.println("FIN PRUEBA REGISTRO");
        System.out.println();

        //BUSCAR JUGADOR POR ALIAS
        System.out.println("BUSQUEDA DE JUGADORES OK");
        System.out.println(sistema.buscarJugador("dos"));//OK

        //ERRORES 1 Y 2
        System.out.println("ERRORES BUSQUEDA JUGADOR");

        System.out.println(sistema.buscarJugador(""));
        System.out.println(sistema.buscarJugador("nadie"));

        System.out.println("FIN PRUEBA BUSCAR JUGADOR POR ALIAS");
        System.out.println();

        //FIN PARTE JUGADORES

        System.out.println("REGISTRO EQUIPO OK");

        System.out.println(sistema.registrarEquipo("EquipoUno","ManagerUno"));
        System.out.println(sistema.registrarEquipo("EquipoDos","ManagerDos"));
        System.out.println(sistema.registrarEquipo("EquipoTres","ManagerTres"));

        System.out.println("ERRORES REGISTRO");

        System.out.println(sistema.registrarEquipo("",""));
        System.out.println(sistema.registrarEquipo("EquipoUno", "Error2"));

        System.out.println("FIN REGISTRO EQUIPOS");

        System.out.println("AGREGAR JUGADORES A EQUIPO OK");
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno","tres"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno","uno"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno","dos"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno", "cuatro"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno", "vquinto"));

        System.out.println("ERRORES AGREGAR JUGADOR A EQUIPO");

        System.out.println(sistema.agregarJugadorAEquipo("",""));
        System.out.println(sistema.agregarJugadorAEquipo("noExiste", "uno"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno","nadie"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoUno","uno"));
        System.out.println(sistema.agregarJugadorAEquipo("EquipoDos","uno"));

        System.out.println("FIN PRUEBA AGREGAR JUGADORES A EQUIPO");

        //LISTADO DE TODOS LOS EQUIPOS,JUGADORES,JUGADORES POR CATEGORIA,JUGADORES DE UN EQUIPO
        System.out.println("LISTADOS");

        System.out.println("JUGADORES");
        System.out.println(sistema.listarJugadoresAscendente().toString());

        System.out.println("EQUIPOS");
        System.out.println(sistema.listarEquiposDescendente().toString());

        System.out.println("JUGADORES POR CATEGORIA");
        System.out.println(sistema.listarJugadoresPorCategoria(Categoria.ESTANDARD));

        System.out.println("JUGADORES DE EQUIPO");
        System.out.println(sistema.listarJugadoresDeEquipo("EquipoUno").toString());

        System.out.println("ERRORES LISTADO JUGADORES DE EQUIPO");

        System.out.println(sistema.listarJugadoresDeEquipo(""));
        System.out.println(sistema.listarJugadoresDeEquipo("NOEXISTE"));

        //GRAFOS
        sistema.registrarSucursal("a", "A");
        sistema.registrarSucursal("b", "B");
        sistema.registrarSucursal("c", "C");
        sistema.registrarSucursal("d", "D");
        sistema.registrarSucursal("e", "E");

        sistema.registrarConexion("a","b",12);
        sistema.registrarConexion("a","d",12);
        sistema.registrarConexion("a","e",12);

        sistema.registrarConexion("b","c",12);
        sistema.registrarConexion("b","e",12);

        sistema.registrarConexion("c","d",12);
        sistema.registrarConexion("c","e",12);

        sistema.registrarConexion("d","a",12);
        sistema.registrarConexion("d","e",12);

        sistema.actualizarConexion("a","b",1);

        sistema.analizarSucursal("a");

        sistema.sucursalesParaTorneo("a",10);
    }
}
