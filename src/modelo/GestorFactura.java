package modelo;

public class GestorFactura {

    public String pagar(IPagable metodoPago, Factura factura){
        // recibimos como parámetro un objeto del tipo interfaz IPagable

        // de esta forma es independiente de las clases PagoTarjetaCredito y PagoPayPal

        // es decir, usamos la implementación que tenga incluída el objeto interfaz!!!

        return metodoPago.procesarPago(factura);
    }

    public String enviar(INotificador metodoNotificador, Factura factura){
        // recibimos como parámetro un objeto del tipo interfaz INotificador

        // de esta forma es independiente de las clases EmailService y SMSService

        // es decir, usamos la implementación que tenga incluída el objeto interfaz!!!

        return metodoNotificador.enviarFactura(factura);
    }
}
