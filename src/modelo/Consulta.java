package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Consulta {
    // clase que surge de la relación muchos a muchos entre Veterinario y Mascota! Un veterinario puede tenexr
    // muchas mascotas a su cargo y una mascota puede ser atendida por varios veterinarios! Para guardar datos
    // extra de esa relación de asociación muchos a muchos creamos esta clase intermedia!! Esto aplica el
    // principio GRASP de fabricación pura!!

    // atributos de la clase consulta ->
    private int idConsulta; // identificador único de la consulta, es auto Incremental en la base de datos !!
    private Mascota mascota;
    private Veterinario veterinario;
    private String condicion; // problema que presenta la mascota en la consulta
    private Tratamiento tratamiento; // enum con el tratamiento dado en la consulta, que contiene
    // el coste!
    private LocalDate fecha; // fecha de la consulta


    // constructor de la clase consulta ->
    public Consulta(Mascota mascota, Veterinario veterinario, String condicion, Tratamiento tratamiento, LocalDate fecha) {
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.fecha = fecha;
        this.condicion = condicion;
        this.tratamiento = tratamiento;
    }

    // constructor con idConsulta (para cuando se recuperan datos de la base de datos) ->
    public Consulta(int idConsulta, Mascota mascota, Veterinario veterinario, String condicion, Tratamiento tratamiento, LocalDate fecha) {
        this.idConsulta = idConsulta;
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.fecha = fecha;
        this.condicion = condicion;
        this.tratamiento = tratamiento;
    }

    // métodos getters de los atributos -->
    public int getIdConsulta() {
        return idConsulta;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }
    public Mascota getMascota() {
        return mascota;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public Tratamiento getTratamiento() {
        return tratamiento;
    }
    public String getCondicion() { return condicion; }
}
