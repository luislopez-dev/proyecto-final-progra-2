package app.interfaces;

import app.Models.Producto;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    Optional<List<Producto>> findProductosByNombre(String nombre);
}
