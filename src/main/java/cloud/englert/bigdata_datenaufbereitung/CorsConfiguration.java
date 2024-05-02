package cloud.englert.bigdata_datenaufbereitung;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Enabling CORS requests from localhost.
 * <a href="https://docs.spring.io/spring-security/reference/reactive/integrations/cors.html">Docs</a>
 *
 * @author Linus Englert
 */
public class CorsConfiguration {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost", "https://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}