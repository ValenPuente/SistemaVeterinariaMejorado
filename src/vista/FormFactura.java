package vista;

import controlador.ControladorFactura;
import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormFactura extends JFrame {
    // recibimos como atributos el dueño y el tratamiento que aparecerán el la factura!!
    private Duenio duenio; // duenio de la factura
    private Tratamiento tratamiento; // tratamiento de la factura que contiene el costo
    private ControladorFactura controladorFactura = new ControladorFactura();


    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCentral;
    private JButton payPalButton;
    private JButton tarjetaDeCreditoButton;
    private JButton SMSButton;
    private JButton emailButton;
    private JLabel lblRetornoPago;
    private JLabel lblRetornoEnvio;

    public FormFactura(Duenio duenio, Tratamiento tratamiento) {
        this.duenio = duenio;
        this.tratamiento = tratamiento;
        inicializar();
        // deshabilitamos los botones de envío hasta que se seleccione un metodo de pago
        emailButton.setEnabled(false);
        SMSButton.setEnabled(false);
        // creamos instancia de Factura ->
        Factura factura = new Factura(duenio, tratamiento);

        payPalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al seleccionar PayPal como metodo de pago
                MetodosPago metodoPagoElegido = MetodosPago.PAYPAL;
                String retornoPago = controladorFactura.procesarPago(metodoPagoElegido, factura);
                lblRetornoPago.setText(retornoPago);
                // habilitamos los botones de envío
                emailButton.setEnabled(true);
                SMSButton.setEnabled(true);

            }
        });

        tarjetaDeCreditoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al seleccionar Tarjeta de Crédito como metodo de pago
                MetodosPago metodoPagoElegido = MetodosPago.CREDITO;
                String retornoPago = controladorFactura.procesarPago(metodoPagoElegido, factura);
                lblRetornoPago.setText(retornoPago);
                // habilitamos los botones de envío
                emailButton.setEnabled(true);
                SMSButton.setEnabled(true);
            }
        });

        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al seleccionar Email como metodo de envío
                MetodosEnvio metodoEnvioElegido = MetodosEnvio.EMAIL;
                String retornoEnvio = controladorFactura.procesarEnvio(metodoEnvioElegido, factura);
                lblRetornoEnvio.setText(retornoEnvio);
                // evitar múltiples clics: deshabilitar botones de envío y de pago
                emailButton.setEnabled(false);
                SMSButton.setEnabled(false);
                payPalButton.setEnabled(false);
                tarjetaDeCreditoButton.setEnabled(false);

                // cerramos la ventana después de enviar la factura y volvemos abrir la de buscarDuenio
                // para iniciar un nuevo proceso
                javax.swing.Timer t = new javax.swing.Timer(2000, ev -> {
                    dispose();
                    new FormBuscarDuenio().setVisible(true);
                });
                t.setRepeats(false);
                t.start();

            }
        });

        SMSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al seleccionar SMS como metodo de envío
                MetodosEnvio metodoEnvioElegido = MetodosEnvio.SMS;
                String retornoEnvio = controladorFactura.procesarEnvio(metodoEnvioElegido, factura);
                lblRetornoEnvio.setText(retornoEnvio);
                // evitar múltiples clics: deshabilitar botones de envío y de pago
                emailButton.setEnabled(false);
                SMSButton.setEnabled(false);
                payPalButton.setEnabled(false);
                tarjetaDeCreditoButton.setEnabled(false);
                // cerramos la ventana después de enviar la factura y volvemos abrir la de buscarDuenio
                // para iniciar un nuevo proceso
                javax.swing.Timer t = new javax.swing.Timer(3000, ev -> {
                    dispose();
                    new FormBuscarDuenio().setVisible(true);
                });
                t.setRepeats(false);
                t.start();

            }
        });
    }

    public void inicializar() {
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}
