package raul.carros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// CrudRepository sao tem Iterable
// JpaRepository trabalha com List e Iterable 
public interface CarroRepository extends JpaRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);
	
	 List<Carro> findByNomeContaining(String query);

}
