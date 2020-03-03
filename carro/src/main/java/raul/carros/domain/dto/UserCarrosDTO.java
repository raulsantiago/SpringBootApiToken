package raul.carros.domain.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import lombok.Data;
import raul.carros.domain.Carro;
import raul.carros.domain.User;

@Data
public class UserCarrosDTO {
	private String nomeUser;
    private String email;
    private String nomeCarro;	
	private String tipo;
	
//	// ModelMapper sao as copias de todos os atributos GET
//	public static UserCarrosDTO create1(User u) {
//		ModelMapper modelMapper = new ModelMapper();
//		return modelMapper.map(u, UserCarrosDTO.class);
//	}	
	
	// Para fins de Join com Foreing Key muitos para muitos de Role
    private List<String> carros;    
    public static UserCarrosDTO createDTO(User user) { 	
        ModelMapper modelMapper = new ModelMapper();
        UserCarrosDTO dto = modelMapper.map(user, UserCarrosDTO.class);
        dto.carros = user.getCarros().stream().map(Carro::getNome).collect(Collectors.toList());
        dto.carros = user.getCarros().stream().map(Carro::getTipo).collect(Collectors.toList());
        return dto;  
    }    
}