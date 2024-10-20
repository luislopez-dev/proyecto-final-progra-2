package app.controllers;

import app.interfaces.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.plaf.PanelUI;

@Controller
public class ProductoController {

    private IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public String index(Model model) {

        var productos = productoService.findAllProductos();

        model.addAttribute("productos", productos);

        return "Producto/index";
    }
}
