package raul.carros.domain;

import raul.carros.domain.JoinRepository;
import raul.carros.domain.dto.UserDTO;
import raul.carros.domain.dto.UsuarioRoleDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private JoinRepository repJoinRepository;

    public List<UsuarioRoleDTO> findUsersAndRoles() {
        return repJoinRepository.findUsersAndRoles();
    }

    public List<UserDTO> findUsersByRole(String role) {
        return repJoinRepository.findUsersByRole(role).stream().map(UserDTO::create).collect(Collectors.toList());
    }

}
