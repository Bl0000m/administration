package kz.bloooom.administration.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Value("${swagger.url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        Server s2 = new Server();
        s2.setUrl(url);
        return new OpenAPI()
                .info(new Info().title("Bloom Administration API")
                        .description("Bloom Project")
                        .version("v1.0.0").license(new License().name("Apache 2.0")
                                .url("http://springdoc.org")).contact(new Contact().name("Miras Madiyev")
                                .email("m.madiyev@vlife.kz"))).servers(List.of(s2));
    }
}
