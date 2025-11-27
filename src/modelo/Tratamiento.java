package modelo;

public class Tratamiento {

    // atributos
    private int idTratamiento; // identificador único del tratamiento, es auto Incremental
    // en la base de datos !!
    private String tratamiento; // descripción del tratamiento!!
    private int valor; // valor del tratamiento!

    // constructor de la clase tratamiento ->
    public Tratamiento(int idTratamiento, String tratamiento, int valor) {
        this.idTratamiento = idTratamiento;
        this.tratamiento = tratamiento;
        this.valor = valor;
    }

    // métodos getters ->
    public int getIdTratamiento() {
        return idTratamiento;
    }
    public String getTratamiento() {
        return tratamiento;
    }
    public int getValor() {
        return valor;
    }

    // toString() ->

    @Override
    public String toString() { // queremos que al imprimir el objeto con un System.out.println
        // nos muestre el nombre del tratamiento en lugar de la referencia al objeto y bien
        // de los demás atributos!!
        return tratamiento;
    }
}
