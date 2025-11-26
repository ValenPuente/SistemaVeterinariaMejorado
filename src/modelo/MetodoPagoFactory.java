package modelo;

public class MetodoPagoFactory {

    // este metodo simplemente se designa que tipo de implementación tendrá el objeto IPagable -->

    public static IPagable crearMetodoPago(MetodosPago metodo){ // como parámetro recibimos una instancia
        // del enum, ya sea PAYPAL o CREDITO!! Lo hacemos estático así no tenemos que crear
        // por si se pasa
        return switch (metodo) { // con el switch mostramos las opciones de pago
            case PAYPAL -> new PagoPayPal(); // si es PAYPAL devolvemos una nueva instancia de PagoPayPal
            case CREDITO -> new PagoTarjetaCredito(); // si es CREDITO devolvemos una nueva instancia de
            // PagoTarjetaCredito
        };
    }
}
