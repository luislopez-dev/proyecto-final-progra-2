package app.controllers;

import app.interfaces.IFacturaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("facturas")
@Controller
public class FacturasController {

    private IFacturaRepository facturaRepository;

    public FacturasController(IFacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @GetMapping()
    public String index(Model model) {

        var facturas = facturaRepository.findAll();

        model.addAttribute("facturas", facturas);

        return "Factura/index";
    }
}
