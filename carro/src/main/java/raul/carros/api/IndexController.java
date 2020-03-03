package raul.carros.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@GetMapping
	public String get() {
		return	"API dos Carros";
	}
	
//	@PostMapping("/login")
//	public String login(@PathParam("login") String login, @PathParam("senha") String senha) {
//		return	"Login "+login+ ", Senha "+senha;
//	}
//	
//	@PostMapping("/carros/{id}")
//	public String getCarroById(@PathVariable("id") Long id) {
//		return	"Carro by id"+ id;
//	}
//	
//	@PostMapping("/carros/tipo/{tipo}")
//	public String getCarroBytipo(@PathVariable("tipo") String tipo) {
//		return	"Lista de carro"+ tipo;
//	}	

}
