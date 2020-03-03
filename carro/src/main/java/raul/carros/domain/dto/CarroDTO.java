package raul.carros.domain.dto;

import org.modelmapper.ModelMapper;
import lombok.Data;
import raul.carros.domain.Carro;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // =>>  apenas os atributos não-nulos apareçam no JSON
public class CarroDTO {	
	
	private Long id;	
	private String nome;	
	private String tipo;
	
	// ModelMapper sao as copias de todos os atributos GET
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
	
	
	//Substituido pelo ModelMapper
//	public CarroDTO(Carro c) {		
//		this.id = c.getId();
//		this.nome = c.getNome();
//		this.tipo = c.getTipo();
//	}
	
	
	
}
