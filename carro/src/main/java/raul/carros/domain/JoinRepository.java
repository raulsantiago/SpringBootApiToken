package raul.carros.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raul.carros.domain.dto.UsuarioRoleDTO;

@Repository
public interface JoinRepository  extends JpaRepository<User, Long> {

	 @Query(value = "SELECT new raul.carros.domain.dto.UsuarioRoleDTO(u.login,u.email,r.nome) FROM User u inner join u.roles r")
	 List<UsuarioRoleDTO> findUsersAndRoles();

	 @Query(value = "SELECT u FROM User u inner join u.roles r WHERE r.nome like %:role%")
	 List<User> findUsersByRole(@Param("role") String role);

}
