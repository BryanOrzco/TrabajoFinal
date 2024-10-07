package gp.Sisteme.Inventario.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;  // Podrías agregar un username si se requiere para el proceso de autenticación
    private String password;

}
