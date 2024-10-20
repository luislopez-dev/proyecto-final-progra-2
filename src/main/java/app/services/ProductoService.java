package app.services;

import app.Models.Producto;
import app.interfaces.IProductoRepository;
import app.interfaces.IProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService
{
    private IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void saveProducto(Producto producto) {

        productoRepository.save(producto);
    }

    @Override
    public void updateProducto(Producto producto) {

        productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Producto producto) {
        productoRepository.delete(producto);
    }

    @Override
    public Producto findProductoByCodigoProducto(long codigoProducto) {

        return productoRepository.findById(codigoProducto).get();
    }

    @Override
    public List<Producto> findProductosByNombre(String nombreProducto) {

        return productoRepository.findProductosByNombre(nombreProducto).get();
    }

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }
}
