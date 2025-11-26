package modelo;

public class Duenio extends Persona {
    // tiene como atributos propios -->
    private final int Dni; // identificador único!
    private final int numTelefono;
    private final String email;

    // los hacemos final para que no puedan ser modificados su valor después de la creación del objeto!

    // constructor con todos los atributos ->
    public Duenio(int Dni, String nombre, String apellido, int numTelefono, String email) {
        super(nombre, apellido);
        this.Dni = Dni;
        this.numTelefono = numTelefono;
        this.email = email;
    }

    //getters ->
    public int getDni() {
        return Dni;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public String getEmail() { return email; }

}