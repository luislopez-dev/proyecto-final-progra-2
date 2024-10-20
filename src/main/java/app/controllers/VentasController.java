package app.controllers;

import app.Enums.MetodoPago;
import app.Models.Factura;
import app.Models.Producto;
import app.Models.Venta;
import app.interfaces.IProductoService;
import app.interfaces.IVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("ventas")
@Controller
public class VentasController {

    private IVentaService ventasService;
    private IProductoService productoService;

    public VentasController(IVentaService ventasService, IProductoService productoService) {
        this.ventasService = ventasService;
        this.productoService = productoService;
    }

    @PostMapping("store")
    public String registrarVenta(
            @RequestParam("metodoPago") MetodoPago metodoPago,
            @RequestParam("codigoProducto") Long codigoProducto,
            @RequestParam("clienteNombre") String clienteNombre,
            @RequestParam("nitCliente") int nitCliente) {

        Venta venta = new Venta();
        venta.setMetodoPago(metodoPago);

        Factura factura = new Factura();
        factura.setClienteNombre(clienteNombre);
        factura.setNitCliente(nitCliente);

        ventasService.saveVenta(venta, codigoProducto, factura);

        return "redirect:/ventas";
    }


    @GetMapping()
    public String index(Model model) {

        var ventas = ventasService.findAllVentas();

        model.addAttribute("ventas", ventas);

        return "Venta/index";
    }

    @GetMapping("create")
    public String create(Model model) {

        var productos = productoService.findAllProductos();

        model.addAttribute("productos", productos);

        return "Venta/create";
    }


}
