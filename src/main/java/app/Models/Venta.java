package app.Models;

import app.Enums.MetodoPago;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn()
    private Factura factura;

    @ManyToOne
    @JoinColumn()
    private Producto producto;

    private float total;

    private LocalDate fecha = LocalDate.now();
}
