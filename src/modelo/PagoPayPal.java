package modelo;

public class PagoPayPal implements IPagable {

    @Override
    public String procesarPago(Factura factura) {
        // Lógica para procesar el pago a través de PayPal
        return ("Procesando pago por PayPal para el tratamiento con costo: " + factura.getTratamiento().getValor());
    }
}
