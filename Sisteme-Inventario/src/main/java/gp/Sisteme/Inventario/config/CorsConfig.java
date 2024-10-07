package gp.Sisteme.Inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Añade esta anotación
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Permite el acceso al endpoint de inicio de sesión
                registry.addMapping("/login")
                        .allowedOrigins("http://localhost:4200") // Ajusta esto si es necesario
                        .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS") // Asegúrate de permitir los métodos adecuados
                        .allowedHeaders("*") // Permitir cualquier encabezado
                        .exposedHeaders("Authorization"); // Exponer encabezados específicos, si es necesario

                registry.addMapping("/inventario-app")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}