package modelo;

public class MetodoEnvioFactory {

    public static INotificador crearMetodoEnvio(MetodosEnvio metodo) {
        return switch (metodo) {
            case EMAIL -> new EmailService();
            case SMS -> new SMSService();
        };
    }
}
