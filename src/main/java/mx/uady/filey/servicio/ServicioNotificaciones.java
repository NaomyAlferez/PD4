package mx.uady.filey.servicio;

import mx.uady.filey.config.ConfiguracionSistema;
import mx.uady.filey.modelo.Editorial;
import mx.uady.filey.modelo.Reservacion;
import mx.uady.filey.modelo.Stand;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ServicioNotificaciones {
    private ConfiguracionSistema config;

    public ServicioNotificaciones(ConfiguracionSistema config) {
        this.config = config;
    }

    public List<String> generarAlertas(List<Reservacion> reservaciones, List<Stand> stands, List<Editorial> editoriales) {
        List<String> alertas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (Reservacion res : reservaciones) {
            Editorial editorial = buscarEditorial(editoriales, res.getIdEditorial());
            Stand stand = buscarStand(stands, res.getIdStand());

            if (editorial == null || stand == null) continue;

            // 1. Notificación de reserva a punto de vencer (3 días o menos)
            long diasParaVencerReserva = ChronoUnit.DAYS.between(hoy, res.getFechaVencimiento());
            if (diasParaVencerReserva >= 0 && diasParaVencerReserva <= 3) {
                alertas.add(String.format("[AVISO VENCIMIENTO RESERVA] Para: %s (%s) -> Su reserva del Stand #%d vence en %d día(s) (%s).",
                        editorial.getNombre(), editorial.getCorreo(), stand.getId(), diasParaVencerReserva, res.getFechaVencimiento()));
            }

            // 2. Notificación de descuento por pronto pago a punto de vencer
            long diasProntoPago = ChronoUnit.DAYS.between(hoy, config.getFechaLimiteProntoPago());
            if (res.isAplicaProntoPago() && diasProntoPago >= 0 && diasProntoPago <= 3) {
                alertas.add(String.format("[AVISO PRONTO PAGO] Para: %s -> El descuento del %.0f%% por pronto pago para el Stand #%d vence el %s.",
                        editorial.getNombre(), config.getPorcentajeDescuentoProntoPago() * 100, stand.getId(), config.getFechaLimiteProntoPago()));
            }

            // 3. Notificación de fecha límite para cubrir costo total / porcentaje de ocupado
            double porcentajeCubierto = res.getMontoPagado() / stand.getPrecio();
            if (porcentajeCubierto < config.getPorcentajeMinimoOcupado()) {
                alertas.add(String.format("[AVISO PAGO PENDIENTE] Para: %s -> Stand #%d tiene %.0f%% pagado ($%.2f de $%.2f). Requiere cubrir al menos %.0f%% antes del %s para pasar a OCUPADO.",
                        editorial.getNombre(), stand.getId(), porcentajeCubierto * 100, res.getMontoPagado(), stand.getPrecio(),
                        config.getPorcentajeMinimoOcupado() * 100, config.getFechaMaximaLiquidacion()));
            }
        }
        return alertas;
    }

    private Editorial buscarEditorial(List<Editorial> editoriales, int id) {
        for (Editorial e : editoriales) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    private Stand buscarStand(List<Stand> stands, int id) {
        for (Stand s : stands) {
            if (s.getId() == id) return s;
        }
        return null;
    }
}