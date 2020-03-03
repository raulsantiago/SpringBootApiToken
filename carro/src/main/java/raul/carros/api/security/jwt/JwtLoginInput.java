package raul.carros.api.security.jwt;

import lombok.Data;

@Data
class JwtLoginInput {
    private String username;
    private String password;
}