package app.interfaces;

import app.Models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByProductoCodigoProducto(Long codigoProducto);
}
