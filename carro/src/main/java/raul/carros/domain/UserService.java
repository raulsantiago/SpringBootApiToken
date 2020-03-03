package raul.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import raul.carros.domain.dto.UserCarrosDTO;
import raul.carros.domain.dto.UserDTO;
import raul.carros.domain.dto.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	  @Autowired
	    private UserRepository repositoryUser;

	    public List<UserDTO> getUsers() {
	        return repositoryUser.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
	    }
	    
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	// List All
//	public List<UserDTO> getUsers(){
//		return userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
//	}	
//	
//	// Find by id por User 
//	public UserDTO getUserById(Long id){
//		Optional<User> users = userRepository.findById(id);
//		return users.map(UserDTO::create).orElseThrow(() -> new ObjectNotFoundException("User não encontrado"));
//	}
	
//	// Find by id por CarroID
//	public List<UserCarrosDTO> findUserCarros(){
//		return userRepository.findUserAndCarros().stream().map(UserCarrosDTO::createDTO).collect(Collectors.toList());
//	}
	
//	// Insert/Create
//	public UserDTO insert(User user) {
//		Assert.isNull(user.getIduser(), "Não foi possível inserir o registro");
//		return UserDTO.create(userRepository.save(user));
//	}
//		
//	// Update
//	public UserDTO update(User User, Long id) {
//		Assert.notNull(id, "Não foi possível atualizar o registro");
//		Optional<User> optional = userRepository.findById(id);
//			if(optional.isPresent()) {
//				User db = optional.get();			
//				db.setNomeuser(User.getUsername());
//				db.setLogin(User.getLogin());
//				db.setEmail(User.getEmail());			
//				userRepository.save(db);
//				return UserDTO.create(db);
//			} else {
//				throw new RuntimeException("Não foi possível aualizar o registro");
//			}
//	}
//		
//	// Delete
//	public void delete(Long id) {		
//		userRepository.deleteById(id);			
//		}
	
}
