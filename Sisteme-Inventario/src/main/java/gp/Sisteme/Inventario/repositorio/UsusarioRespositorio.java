package gp.Sisteme.Inventario.repositorio;

import gp.Sisteme.Inventario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsusarioRespositorio extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findOneByusername(String username);
}
