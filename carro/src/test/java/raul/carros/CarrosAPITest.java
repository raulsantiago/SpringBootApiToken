package raul.carros;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import raul.carros.domain.Carro;
import raul.carros.domain.dto.CarroDTO;

@SpringBootTest(classes = CarroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest extends BaseAPITest {

	// Alteração para atender o Token
    private ResponseEntity<CarroDTO> getCarro(String url) {
        return get(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getCarros(String url) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<CarroDTO>>() {
                });
    }

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        // Insert
        ResponseEntity response = post("/api/v1/carros", carro, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        CarroDTO c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        delete(location, null);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(6, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(2, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(2, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(2, getCarros("/api/v1/carros/tipo/luxo").getBody().size());
        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/1");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        CarroDTO c = response.getBody();
        assertNotNull(c);
        assertEquals("Tucker 1948", c.getNome());
    }

    @Test
    public void testGetNotFound() {
        ResponseEntity response = getCarro("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    
    
    // Alteração abaixo para atender o BasicAuth    
//	@Autowired
//    protected TestRestTemplate rest;
//
//    private ResponseEntity<CarroDTO> getCarro(String url) {
//        return rest.withBasicAuth("user","123").getForEntity(url, CarroDTO.class);
//    }
//
//    private ResponseEntity<List<CarroDTO>> getCarros(String url) {
//        return rest.withBasicAuth("user","123").exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<CarroDTO>>() {
//                });
//    }
//
//
//    @Test
//    public void testSave() {
//
//        Carro carro = new Carro();
//        carro.setNome("Porshe");
//        carro.setTipo("esportivos");
//
//        // Insert
//        ResponseEntity response = rest.withBasicAuth("admin","123").postForEntity("/api/v1/carros", carro, null);
//        System.out.println(response);
//
//        // Verifica se criou
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        // Buscar o objeto
//        String location = response.getHeaders().get("location").get(0);
//        CarroDTO c = getCarro(location).getBody();
//
//        assertNotNull(c);
//        assertEquals("Porshe", c.getNome());
//        assertEquals("esportivos", c.getTipo());
//
//        // Deletar o objeto
//        rest.withBasicAuth("user","123").delete(location);
//
//        // Verificar se deletou
//        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
//    }
//
//    @Test
//    public void testLista() {
//        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
//        assertNotNull(carros);
//        assertEquals(6, carros.size());
//    }
//
//    @Test
//    public void testListaPorTipo() {
//        assertEquals(2, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
//        assertEquals(2, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
//        assertEquals(2, getCarros("/api/v1/carros/tipo/luxo").getBody().size());
//        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
//    }
//
//    @Test
//    public void testGetOk() {
//        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/5");
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//        CarroDTO c = response.getBody();
//        assertEquals("Bugatti Veyron", c.getNome());
//    }
//
//    @Test
//    public void testGetNotFound() {
//        ResponseEntity response = getCarro("/api/v1/carros/1100");
//        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
//    }	

}