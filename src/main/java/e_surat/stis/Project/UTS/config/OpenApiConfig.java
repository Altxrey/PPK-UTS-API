package e_surat.stis.Project.UTS.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eSuratOpenAPI() {
        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()
                .info(new Info()
                        .title("E-Surat API Documentation")
                        .description("""
                                API ini merupakan bagian dari sistem e-Surat STIS.<br>
                                Gunakan endpoint **/api/auth/login** untuk mendapatkan JWT token.<br><br>
                                Setelah login, klik tombol **Authorize 🔐** di kanan atas Swagger UI,<br>
                                lalu masukkan token dengan format:<br>
                                <code>Bearer &lt;token_kamu&gt;</code>
                                """)
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(In.HEADER)));
    }
}
