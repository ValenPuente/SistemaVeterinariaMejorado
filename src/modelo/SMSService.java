package modelo;

public class SMSService implements INotificador {

    @Override // indica que este metodo sobrescribe un metodo de la interfaz!!!!
    public String enviarFactura(Factura factura) {
        // Lógica para enviar la factura por SMS al duenio -->
        return "Enviando factura por SMS al dueño: " + factura.getDuenio().getNumTelefono();
    }

}
