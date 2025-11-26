package modelo;

public class Mascota {
    // clase padre de las mascotas que heredarán los atributos -->
    private int idMascota; // identificador único de la mascota, es auto Incremental en la base
    // de datos !!
    private String tipo; // tipo de mascota (perro, gato, etc.)
    private String nombre;
    private Duenio duenio; // relación de asociación unidireccional 1 a n con la clase Dueño!
    // solo mascota conoce a duenio, pero la clase duenio no conoce mascota!!!


    // constructor de la clase mascota ->
    public Mascota(int idMascota, String tipo, String nombre, Duenio duenio){
        this.idMascota = idMascota;
        this.tipo = tipo;
        this.nombre = nombre;
        this.duenio = duenio;
    }

    // constructor de la clase mascota sin el id (para cuando se crean nuevas mascotas antes de
    // guardarlas en la base de datos donde no necesitamos el valor del id porque es auto incremental!
    // FUNDAMENTAL ESO!! En algunos casos como este, creamos un objeto de mascota sin el id
    // ya que es auto incremental en la base y no tenemos su valor cuando ingresamos los datos
    // de la mascota. Cuando recuperamos la mascota de la base de datos si tendremos el id asignado!!
    // y usamos el otro constructor con id!!

    public Mascota(String tipo, String nombre, Duenio duenio){
        this.tipo = tipo;
        this.nombre = nombre;
        this.duenio = duenio;
    }

    // métodos getters ->
    public int getIdMascota() { return idMascota; }

    public String getTipo() { return tipo;}

    public String getNombre() {
        return nombre;
    }

    public Duenio getDuenio() { return duenio; }
}
