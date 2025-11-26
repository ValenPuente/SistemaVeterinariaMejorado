package modelo;

public class EmailService implements INotificador {

    @Override
    public String enviarFactura(Factura factura) {
        // Lógica para enviar la factura por email al duenio -->
        return "Enviando factura por email al dueño: " + factura.getDuenio().getEmail();}
}
