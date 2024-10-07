package gp.Sisteme.Inventario.repositorio;

import gp.Sisteme.Inventario.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}
