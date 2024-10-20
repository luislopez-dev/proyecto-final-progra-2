package app.interfaces;

import app.Models.Producto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface IProductoService {

    void saveProducto(Producto producto);

    void updateProducto(Producto producto);

    void deleteProducto(Long codigoProducto);

    Producto findProductoByCodigoProducto(long codigoProducto);

    List<Producto> findProductosByNombre(String nombreProducto);

    List<Producto> findAllProductos();

    void generarReportePDF(Long codigoProducto, HttpServletResponse response);
}
