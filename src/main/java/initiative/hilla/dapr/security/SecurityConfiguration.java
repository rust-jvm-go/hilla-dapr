package initiative.hilla.dapr.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    // The secret is stored in /config/secrets/application.properties by default.
    // Never commit the secret into version control; each environment should have
    // its own secret.
    @Value("${initiative.hilla.dapr.auth.secret}")
    private String authSecret;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            // H2 console; TODO: remove later
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));

        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
            .requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll()
            // Icons from the line-awesome addon
            .requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg")).permitAll()
            // H2 console; TODO: remove later
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/connect/**")).authenticated()
        );

        super.configure(http);

        http.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        setLoginView(http, "/login");

        setStatelessAuthentication(
            http,
            new SecretKeySpec(Base64.getDecoder().decode(authSecret), JwsAlgorithms.HS256),
            "initiative.hilla.dapr");
    }
}
