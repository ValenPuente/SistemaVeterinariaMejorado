package modelo;

public class Factura {
    private Duenio duenio;
    private Tratamiento tratamiento;

    public Factura(Duenio duenio, Tratamiento tratamiento){
        this.duenio = duenio;
        this.tratamiento = tratamiento;
    }

    // getters y setters

    public Duenio getDuenio() {
        return duenio;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

}
