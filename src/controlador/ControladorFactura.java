package controlador;

import modelo.*;

public class ControladorFactura {
    // creamos instancia de GestorFactura ->

    private final GestorFactura gestorFactura = new GestorFactura();

    public String procesarPago(MetodosPago metodoPagoElegido, Factura factura) {
        // creamos objeto del tipo interfaz con la implementación lógica del método de pago elegido
        IPagable metodoPagoImpl = MetodoPagoFactory.crearMetodoPago(metodoPagoElegido);

        // llamamos al metodo pagar del gestorFactura, pasando el objeto del tipo IPagable
        return gestorFactura.pagar(metodoPagoImpl, factura);
    }

    public String procesarEnvio(MetodosEnvio metodoEnvioElegido, Factura factura) {
        // creamos objeto del tipo interfaz con la implementación lógica del metodo de envío elegido
        INotificador notificadorImpl = MetodoEnvioFactory.crearMetodoEnvio(metodoEnvioElegido);

        // llamamos al metodo enviar del gestorFactura, pasando el objeto del tipo INotificador
        return gestorFactura.enviar(notificadorImpl, factura);
    }

}
