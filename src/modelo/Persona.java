package modelo;

public abstract class Persona {
    // clase padre abstracta de la cual heredan Veterinario y Dueño!
    protected String nombre;
    protected String apellido;

    // constructor de la clase padre que llamarán las clases hijas! ->
    public Persona(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // getters de los atributos que heredarán las clases hijas como métodos abstractos -->

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
