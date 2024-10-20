package app.interfaces;


import app.Models.Factura;
import app.Models.Venta;

import java.util.List;

public interface IVentaService {

    public void saveVenta(Venta venta, Long codigoProducto, Factura factura);
    public List<Venta> findAllVentas();
}