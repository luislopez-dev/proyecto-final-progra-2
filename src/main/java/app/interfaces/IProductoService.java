package app.interfaces;

import app.Models.Producto;

import java.util.List;

public interface IProductoService {

    void saveProducto(Producto producto);

    void updateProducto(Producto producto);

    void deleteProducto(Producto producto);

    Producto findProductoByCodigoProducto(long codigoProducto);

    List<Producto> findProductosByNombre(String nombreProducto);

    List<Producto> findAllProductos();
}
