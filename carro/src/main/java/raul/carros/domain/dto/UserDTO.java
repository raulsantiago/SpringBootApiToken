package raul.carros.domain.dto;


import raul.carros.domain.Role;
import raul.carros.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;
    
    private List<String> roles;
    
    // Para fins de Join com Foreing Key muitos para muitos de Role        
    public static UserDTO create(User user) {    	
        ModelMapper modelMapper = new ModelMapper();
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.roles = user.getRoles().stream()
                .map(Role::getNome)
                .collect(Collectors.toList());
        return dto;  
    }  
    
    
    public static UserDTO create(User user, String token) {
        UserDTO dto = create(user);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }   

}