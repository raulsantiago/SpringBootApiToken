package raul.carros.domain;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.*;

@Entity
@Data
public class Role implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY  )	 
    private Long id;	
	
    private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
    
    

}
