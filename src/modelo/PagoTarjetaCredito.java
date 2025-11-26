package modelo;

public class PagoTarjetaCredito implements IPagable {

    @Override
    public String procesarPago(Factura factura) {
        // Lógica para procesar el pago con tarjeta de crédito
        return ("Procesando pago con tarjeta de crédito por un monto de: " + factura.getTratamiento().getValor());
    }
}
