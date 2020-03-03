package raul.carros.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import raul.carros.domain.dto.UserCarrosDTO;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String login);
	
//	 @Query(value = "SELECT new raul.carros.domain.dto.UserCarrosDTO(u.nomeUser,u.email,c.nomeCarro,c.tipo) FROM User u inner join u.carro c")
//	 List<UserCarrosDTO> findUserAndCarros();
}
