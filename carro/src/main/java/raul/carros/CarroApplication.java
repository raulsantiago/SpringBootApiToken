package raul.carros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import raul.carros.domain.Carro;
import raul.carros.domain.dto.CarroDTO;

@SpringBootApplication
public class CarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarroApplication.class, args);
		
//		Carro c = new Carro();
//		
//		System.out.println("Carro "+c.getId());
//		
//		CarroDTO e = new CarroDTO();
//		
//		System.out.println("CarroDTO "+e.getId());
		
	}

}
