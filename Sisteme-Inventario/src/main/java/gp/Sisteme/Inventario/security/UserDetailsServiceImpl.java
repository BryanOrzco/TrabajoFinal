package gp.Sisteme.Inventario.security;

import gp.Sisteme.Inventario.modelo.Usuario;
import gp.Sisteme.Inventario.repositorio.UsusarioRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsusarioRespositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio
                .findOneByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con el nombre de usuario: " + username));
        return new UserDetailImpl(usuario);

    }
}
