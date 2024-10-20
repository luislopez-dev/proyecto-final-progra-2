package app.controllers;

import app.Models.Producto;
import app.interfaces.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public String index(Model model) {

        var productos = productoService.findAllProductos();

        model.addAttribute("productos", productos);

        return "Producto/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("nombre") String nombre, Model model) {

        var productos = productoService.findProductosByNombre(nombre);

        model.addAttribute("productos", productos);

        return "Producto/index";
    }

    @GetMapping("create")
    public String create() {

        return "Producto/create";
    }

    @GetMapping("{codigoProducto}")
    public String show(@PathVariable Long codigoProducto, Model model) {

        var producto = productoService.findProductoByCodigoProducto(codigoProducto);

        model.addAttribute("producto", producto);

        return "Producto/show";
    }

    @GetMapping("{codigoProducto}/edit")
    public String edit(@PathVariable Long codigoProducto, Model model) {

        var producto = productoService.findProductoByCodigoProducto(codigoProducto);

        model.addAttribute("producto", producto);

        return "Producto/edit";
    }

    @PostMapping("{codigoProducto}/delete")
    public String deleteConfirmed(@PathVariable Long codigoProducto, RedirectAttributes redirectAttributes) {

        productoService.deleteProducto(codigoProducto);

        redirectAttributes.addFlashAttribute("message", "Producto eliminado con éxito");

        return "redirect:/productos";
    }

    @PostMapping("store")
    public String store(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {

        productoService.saveProducto(producto);

        redirectAttributes.addFlashAttribute("message", "Producto creado con éxito");

        return "redirect:/productos";
    }

    @PostMapping("edit")
    public String EditConfirmed(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {

        productoService.saveProducto(producto);

        redirectAttributes.addFlashAttribute("message", "Producto actualizado con éxito");

        return "redirect:/productos";
    }


}
