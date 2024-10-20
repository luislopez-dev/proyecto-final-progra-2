package app.services;

import app.Models.Producto;
import app.interfaces.IProductoRepository;
import app.interfaces.IProductoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
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
    public void deleteProducto(Long codigoProducto)
    {
        Producto producto = findProductoByCodigoProducto(codigoProducto);

        productoRepository.delete(producto);
    }

    @Override
    public Producto findProductoByCodigoProducto(long codigoProducto) {

        return productoRepository.findById(codigoProducto).get();
    }

    @Override
    public List<Producto> findProductosByNombre(String nombreProducto) {

        return productoRepository.findProductosByNombre(nombreProducto).orElse(List.of());
    }

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void generarReportePDF(Long codigoProducto, HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=producto.pdf");

        try {
            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Establecer el estilo del documento
            document.setMargins(20, 20, 20, 20); // Margen: arriba, derecha, abajo, izquierda

            // Agregar el nombre de la empresa
            Paragraph empresa = new Paragraph("Distribuidora Centroamericana S.A.")
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(empresa);

            // Espaciado entre el nombre de la empresa y el título
            document.add(new Paragraph("\n"));

            // Agregar un título con estilo
            Paragraph title = new Paragraph("Reporte de Producto")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Espaciado entre el título y la información
            document.add(new Paragraph("\n"));

            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = fechaActual.format(formatter);

            // Agregar la fecha al documento
            Paragraph fecha = new Paragraph("Fecha: " + fechaFormateada)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(fecha);

            // Espaciado entre la fecha y el contenido
            document.add(new Paragraph("\n"));

            // Obtener el producto
            Producto producto = findProductoByCodigoProducto(codigoProducto);

            // Verificar si el producto existe
            if (producto != null) {
                // Crear una tabla con 2 columnas
                Table table = new Table(2);
                table.setWidth(UnitValue.createPercentValue(100)); // Ancho de la tabla al 100%

                // Agregar encabezados de tabla
                table.addHeaderCell(new Cell().add(new Paragraph("Campo").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Valor").setBold()));

                // Agregar filas con datos del producto
                table.addCell(new Cell().add(new Paragraph("Nombre")));
                table.addCell(new Cell().add(new Paragraph(producto.getNombre())));
                table.addCell(new Cell().add(new Paragraph("Precio")));
                // Formatear el precio con el símbolo Q
                table.addCell(new Cell().add(new Paragraph("Q" + String.format("%.2f", producto.getPrecio()))));
                table.addCell(new Cell().add(new Paragraph("Existencias")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(producto.getExistencias()))));

                // Agregar la tabla al documento
                document.add(table);
            } else {
                document.add(new Paragraph("Producto no encontrado.").setTextAlignment(TextAlignment.CENTER));
            }

            // Espaciado entre el contenido y la fecha de generación
            document.add(new Paragraph("\n\n"));

            // Obtener la fecha y hora actual para el pie de página
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaHoraFormateada = fechaHoraActual.format(formatterHora);

            // Agregar la fecha y hora al final del documento
            Paragraph footer = new Paragraph("Reporte generado el: " + fechaHoraFormateada)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(footer);

            // Cerrar el documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
