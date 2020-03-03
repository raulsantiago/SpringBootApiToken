package raul.carros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raul.carros.domain.User;
import raul.carros.domain.UserService;
import raul.carros.domain.dto.UserDTO;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
    private UserService serviceUser;

    @GetMapping()
    public ResponseEntity get() {
        List<UserDTO> list = serviceUser.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {

        //UserDetails userDetails = JwtUtil.getUserDetails();

        return UserDTO.create(user);
    }
    
	
//	@Autowired
//	private UserService userService;
//	
//	// List All
//	@GetMapping()
//	public ResponseEntity get() {
//		return ResponseEntity.ok(userService.getUsers());
//	}
//	
//	// Get by Id User
//	@GetMapping("/{id}")
//	public ResponseEntity get(@PathVariable("id") Long id) {
//		UserDTO users = userService.getUserById(id);
//		return ResponseEntity.ok(users);
//	}
		
	//	Get by Id de Carros
//    @GetMapping()
//    public ResponseEntity getUc() {
//        List<UserCarrosDTO> list = userService.findUserCarros();
//        return list.isEmpty() ?
//                ResponseEntity.noContent().build() :
//                ResponseEntity.ok(list);
//    }
	
	
//	@GetMapping("/carros/{carroId}/users")
//	public ResponseEntity findUsersByCarroId(@PathVariable("carroId") Long carroId) {
//		//CarroDTO carro = carroService.getCarroById(carroId);		
//		List<UserCarroDTO> users = joinUserCarroRepository.findByCarro(carroId);
//		return ResponseEntity.ok(users);		
//	}
	

	

}
