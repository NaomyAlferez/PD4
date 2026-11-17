package mx.uady.filey.config;

import java.time.LocalDate;

public class ConfiguracionSistema {
    private double costoPorMetroCuadrado;
    private double porcentajeMinimoOcupado; // Ej. 0.50 (50% cubierto para pasar a OCUPADO)
    private double porcentajeDescuentoProntoPago; // Ej. 0.10 (10% de descuento)
    private LocalDate fechaLimiteProntoPago;
    private LocalDate fechaMaximaLiquidacion;

    public ConfiguracionSistema() {
        this.costoPorMetroCuadrado = 1500.0;
        this.porcentajeMinimoOcupado = 0.50;
        this.porcentajeDescuentoProntoPago = 0.10;
        this.fechaLimiteProntoPago = LocalDate.now().plusDays(2);
        this.fechaMaximaLiquidacion = LocalDate.now().plusDays(5);
    }

    public double getCostoPorMetroCuadrado() { return costoPorMetroCuadrado; }
    public double getPorcentajeMinimoOcupado() { return porcentajeMinimoOcupado; }
    public double getPorcentajeDescuentoProntoPago() { return porcentajeDescuentoProntoPago; }
    public LocalDate getFechaLimiteProntoPago() { return fechaLimiteProntoPago; }
    public LocalDate getFechaMaximaLiquidacion() { return fechaMaximaLiquidacion; }
}