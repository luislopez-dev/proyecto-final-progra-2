package app.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Data
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_codigo_producto", nullable = false) 
    private Producto producto;

    private String clienteNombre;

    private int nitCliente;

    private float impuestos;

    private float subTotal;

    private float total;

    private LocalDate fecha = LocalDate.now();
}
