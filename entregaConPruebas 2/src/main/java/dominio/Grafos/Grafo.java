package dominio.Grafos;

import dominio.Clases.Conexion;
import dominio.Clases.Sucursal;
import dominio.Listas.ListaSucursal;

public class Grafo {

    private Sucursal [] vertices;
    private Arista [][] matrizADY;
    private int largo;
    private int cantSuc;

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public Arista[][] getMatrizADY() {
        return matrizADY;
    }

    public void setMatrizADY(Arista[][] matrizADY) {
        this.matrizADY = matrizADY;
    }

    public Sucursal[] getVertices() {
        return vertices;
    }

    public void setVertices(Sucursal[] vertices) {
        this.vertices = vertices;
    }

    public int getCantSuc() {
        return cantSuc;
    }

    public void setCantSuc(int cantSuc) {
        this.cantSuc = cantSuc;
    }

    private class Arista{
        private Conexion conexion;
        private boolean existe;

        public Conexion getConexion() {
            return conexion;
        }

        public void setConexion(Conexion conexion) {
            this.conexion = conexion;
        }

        public boolean isExiste() {
            return existe;
        }

        public void setExiste(boolean existe) {
            this.existe = existe;
        }

        public Arista() { this.existe = false; }

        @Override
        public String toString() {
            return existe?"1":"0";
        }
    }

    public Grafo(int maximo){
        largo = maximo;
        vertices = new Sucursal[maximo];
        matrizADY = new Arista[maximo][maximo];

        for(int i = 0; i < maximo; i++){
            for(int j = 0; j <= i; j++){
                matrizADY[i][j] = new Arista();
                matrizADY[j][i]= matrizADY[i][j];
            }
        }
    }

    public void agregarVertice(Sucursal sucursal){
        vertices[cantSuc] = sucursal;
        cantSuc++;
    }

    public void registrarArista(Sucursal ini, Sucursal fin, int latencia){
        int inicio = buscarIndice(ini);
        int end = buscarIndice(fin);
        Conexion con = new Conexion(ini,fin,latencia);

        matrizADY[inicio][end].existe = true;
        matrizADY[inicio][end].conexion = con;
    }

    public int buscarIndice(Sucursal s){
        if(s == null){
            return  -1;
        }
        for(int i = 0; i < vertices.length; i++){

            if(s.equals(vertices[i])){
                return i;
            }
        }

        return -1;
    }

    public Sucursal buscarSucursalPorCodigo(String codigoSucursal){

        for (int i = 0; i <= vertices.length -1; i++) {

            if(vertices[i] == null){
                return null;
            }

            if(codigoSucursal.equals(vertices[i].getCodigo())){
                return vertices[i];
            }
        }

        return null;
    }

    public boolean existeConexion(String codUno, String codDos){

        int iUno = buscarIndice(buscarSucursalPorCodigo(codUno));
        int iDos = buscarIndice(buscarSucursalPorCodigo(codDos));

       if(matrizADY[iUno][iDos].existe){
           return true;
       }

        return false;
    }

    public void actulizarConexion(String codUno, String codDos, int latencia){

        int iUno = buscarIndice(buscarSucursalPorCodigo(codUno));
        int iDos = buscarIndice(buscarSucursalPorCodigo(codDos));

        if(matrizADY[iUno][iDos].existe){
            matrizADY[iUno][iDos].getConexion().setLatencia(latencia);
        }
    }

    public String analizarSuc (String codigo){
        boolean[] visitados = new boolean[largo];
        boolean[] visitadosSinOrigen = new boolean[largo];
        int indice = buscarIndice(buscarSucursalPorCodigo(codigo));
        int numComp = 0;
        int numCompSinOrigen = 0;

        //suma la cantiadad de componenetes cuando no esta visitado
        for (int i = 0; i < cantSuc; i++) {
            if (!visitados[i]){
                dfsRecursivoRec(i, visitados);
                numComp++; // suma uno por la cantidad de componentes que hayan
            }
        }

        //suma la cantidad de componentes obviando la sucursal que se quiere analizar
        for (int i = 0; i < cantSuc; i++) {
            if (i != indice){
                if(!visitadosSinOrigen[i]){
                    dfsRecursivoRecDos(i, visitadosSinOrigen, indice);
                    numCompSinOrigen++;//cantidad de componentes sacando la sucursal a analizar
                }
            }
        }

        if(numComp < numCompSinOrigen){
            return "SI";
        }else {
            return "NO";
        }
    }

    private void dfsRecursivoRec(int vExplorar, boolean[] visitados) {
        if (!visitados[vExplorar]) {
            visitados[vExplorar] = true;
            for (int vDestino = 0; vDestino < cantSuc; vDestino++) {
                if (matrizADY[vExplorar][vDestino].existe) {
                    dfsRecursivoRec(vDestino, visitados);
                }
            }
        }
    }

    private void dfsRecursivoRecDos(int vExplorar, boolean[] visitados, int ignorar) {
        //mientras no esten todos visitados
        if (!visitados[vExplorar]) {
            visitados[vExplorar] = true;
            for (int vDestino = 0; vDestino < cantSuc; vDestino++) {

                //y la sucursal en la que estemos parados sea distinta a la que se quiere analizar
                if(vExplorar != ignorar){
                    if (matrizADY[vExplorar][vDestino].existe){
                        dfsRecursivoRecDos(vDestino, visitados,ignorar);
                    }
                }
            }
        }
    }

    public int costoMasAlto(String codigoSucursal, int latenciaLimite){
        int inicio = buscarIndice(buscarSucursalPorCodigo(codigoSucursal));
        int retorno = 0;

        ResultadoDijkstra dijkstra = dijkstra(inicio, latenciaLimite);

        for (int i = 0; i < vertices.length; i++) {

            if(dijkstra.costos[i] != Integer.MAX_VALUE){
                if (dijkstra.costos[i] > retorno){
                    retorno = dijkstra.costos[i];
                }
            }
        }

        return retorno;
    }

    public String resultadoDijkstra(String codigoSucursal, int latenciaLimite){
        int inicio = buscarIndice(buscarSucursalPorCodigo(codigoSucursal));
        ListaSucursal l = new ListaSucursal();

        for (int i = 0; i < vertices.length; i++) {

            ResultadoDijkstra dijkstra = dijkstra(inicio, latenciaLimite);

            if(dijkstra.costos[i] <= latenciaLimite){
                l.agregarOrdenado(vertices[i]);
            }
        }

        return l.listarDatos();
    }

    public ResultadoDijkstra dijkstra(int inicio, int latenciaLimite){
        int [] costos = new int[largo];
        int [] padres = new int[largo];

        boolean [] visitados = new boolean[largo];

        for (int i = 0; i < largo; i++) {
            costos[i] = Integer.MAX_VALUE;
            padres[i] = -1;
        }

        costos[inicio] = 0;
        padres[inicio] = inicio;

        while (!todoVisitado(visitados, padres)){
            int explorar = verticeNoVisitiado(costos, visitados, padres);

            for (int ady = 0; ady < largo; ady++) {

                if(matrizADY[explorar][ady].existe){//adyasentes del explorar

                    int costoArista = matrizADY[explorar][ady].getConexion().getLatencia();

                    int costoExplorar = costos[explorar];
                    int total = costoExplorar + costoArista;

                    int costoMinimoVerticeAdyasente = costos[ady];

                    if(costoMinimoVerticeAdyasente > total && total <= latenciaLimite){
                        costos[ady] = total;
                        padres[ady] = explorar;
                    }
                }
            }

            visitados[explorar] = true;
        }

        return new ResultadoDijkstra(costos);
    }

    private boolean todoVisitado(boolean[] visitados, int [] padres){
        for (int i = 0; i < largo; i++) {
            if(padres[i] != -1){
                if(!visitados[i]){
                    return false;
                }
            }
        }

        return true;
    }

    private int verticeNoVisitiado(int[] costos, boolean[] v, int [] padres){
        int minIdx = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < largo; i++) {
            if(costos[i] <= min && !v[i] && padres[i] != -1) {
                min = costos[i];
                minIdx = i;
            }
        }

        return minIdx;
    }

    public static class ResultadoDijkstra {
        private int[] costos;//aca estan los costos desde A a cada uno de los vertices

        public ResultadoDijkstra(int[] costos) {
            this.costos = costos;
        }

        public int[] getCostos() {
            return costos;
        }
    }
}



