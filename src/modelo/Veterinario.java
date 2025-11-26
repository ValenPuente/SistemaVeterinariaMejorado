package modelo;

public class Veterinario extends Persona {
    // clase que modela la entidad de los veterinarios!!
    // atributos
    private int idVeterinario; // id unico para cada veterinario!!
    private int clave; // clave que usará el veterinario para iniciar sesión!!


    // constructor ->
    public Veterinario(int idEmpleado, String nombre, String apellido, int clave){
        super(nombre, apellido); // llama al constructor de la clase padre!!
        this.idVeterinario = idEmpleado;
        this.clave = clave;
    }

    public Veterinario(String nombre, String apellido) {
        super(nombre, apellido);
    }

    // getters ->
    public int getClave() {
        return clave;
    }
    public int getIdVeterinario() { return idVeterinario; }
}
