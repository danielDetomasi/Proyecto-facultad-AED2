package dominio.Clases;

public class Conexion {
    private Sucursal sucursalUno;
    private Sucursal sucursalDos;
    private int latencia;

    public Conexion( Sucursal codigoSucursalUno,Sucursal codigoSucursalDos, int latencia) {
        this.sucursalUno = codigoSucursalUno;
        this.sucursalDos = codigoSucursalDos;
        this.latencia = latencia;
    }

    public int getLatencia() {
        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
    }

    public Sucursal getSucursalDos() {
        return sucursalDos;
    }

    public void setSucursalDos(Sucursal sucursalDos) {
        this.sucursalDos = sucursalDos;
    }

    public Sucursal getSucursalUno() {
        return sucursalUno;
    }

    public void setSucursalUno(Sucursal sucursalUno) {
        this.sucursalUno = sucursalUno;
    }

    @Override
    public String toString() {
        return sucursalUno.toString() + sucursalDos.toString() + latencia;
    }
}
