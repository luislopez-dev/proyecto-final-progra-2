package app.services;

import app.Models.Factura;
import app.Models.Producto;
import app.Models.Venta;
import app.interfaces.IFacturaRepository;
import app.interfaces.IProductoRepository;
import app.interfaces.IVentaRepository;
import app.interfaces.IVentaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    private IVentaRepository ventaRepository;

    private IProductoRepository productoRepository;

    private IFacturaRepository facturaRepository;

    public VentaService(IVentaRepository ventaRepository, IProductoRepository productoRepository, IFacturaRepository facturaRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.facturaRepository = facturaRepository;
    }

    @Override
    @Transactional // Ejectutar Transacción
    public void saveVenta(Venta venta, Long codigoProducto, Factura factura) {

        var producto = productoRepository.findById(codigoProducto).get();

        float impuestos = producto.getPrecio() * 0.12f;

        float subTotal = producto.getPrecio();

        float total = producto.getPrecio() + impuestos;

        factura.setImpuestos(impuestos);

        factura.setProducto(producto);

        factura.setSubTotal(subTotal);

        factura.setTotal(total);

        venta.setTotal(total);

        venta.setProducto(producto);

        venta.setFactura(factura);

        producto.setExistencias(producto.getExistencias() - 1);

        productoRepository.save(producto);

        facturaRepository.save(factura);

        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> findAllVentas() {

        return ventaRepository.findAll();
    }
}
