package raul.carros.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.*;

@Entity
@Data
@Table(name="USERS")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long iduser;

    private String nomeuser;
    private String email;
    private String login;
    private String senha;
    
    @OneToMany
    private Set<Carro> carros;
    
//    // Um para muitos em JPA - parte 2
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Carro> carros;  
    
    
    // Muitos para muitos em JPA criando uma nova tebela de nome = users_roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_iduser", referencedColumnName = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))    
    private List<Role> roles;
    
    // Para gerar uma senha encode de hash do número 123
//    public static void main(String[] args) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pwd = encoder.encode("123");
//        System.out.print(pwd);
//    }

    
    // Metodos do UserDetails
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return roles;
	}

	@Override
	public String getPassword() {		
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
