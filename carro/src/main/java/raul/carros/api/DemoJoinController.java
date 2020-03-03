package raul.carros.api;


import raul.carros.domain.JoinService;
import raul.carros.domain.dto.UserDTO;
import raul.carros.domain.dto.UsuarioRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/join")
public class DemoJoinController {
	
	@Autowired
    private JoinService serviceJoinService;
	
    @GetMapping()
    public ResponseEntity get() {
        List<UsuarioRoleDTO> list = serviceJoinService.findUsersAndRoles();
        return list.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(list);
    }

    @GetMapping("/{role}")
    //http://localhost:8080/api/v1/join/user
    public ResponseEntity findUsersByRole(@PathVariable("role") String role) {
        List<UserDTO> lista = serviceJoinService.findUsersByRole(role);
        return lista.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(lista);
    }
}