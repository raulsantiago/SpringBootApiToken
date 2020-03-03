package raul.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import raul.carros.domain.User;
import raul.carros.domain.UserRepository;

@Service(value = "userDetailsService") // O value Ã© um identificador para fazer injeÃ§Ã£o de dependencia em outra classe
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
 
		User user = userRepository.findByLogin(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return user;
		
		// não precisa mais pois agora tem relacionamento no banco
//		return User.withUsername(username).password(user.getSenha()).roles("USER").build();
		
		
//		// Este codigo foi substituido pois a senha já esta salva com encoder
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		if(username.equals("user")) {
//			return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
//		} else if(username.equals("admin")) {
//			return User.withUsername(username).password(encoder.encode("admin")).roles("USER", "ADMIN").build();
//		}
//		 
//		throw new UsernameNotFoundException("user not found");
		
	}

}
