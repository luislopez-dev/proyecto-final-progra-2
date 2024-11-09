package app.interfaces;

import app.Models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByProductoCodigoProducto(Long codigoProducto);

    @Query("SELECT v FROM Venta v WHERE v.producto.codigoProducto = :codigoProducto")
    List<Venta> findVentasByProducto(@Param("codigoProducto") Long codigoProducto);
}
