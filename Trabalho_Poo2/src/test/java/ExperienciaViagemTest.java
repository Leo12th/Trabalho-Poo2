import com.Trabalho_Poo2.model.ExperienciaViagem;
import com.Trabalho_Poo2.model.Midia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExperienciaViagemTest {

    private ExperienciaViagem experienciaViagem;

    @BeforeEach
    public void setUp() {
        // Crie uma instância de ExperienciaViagem para cada teste
        experienciaViagem = new ExperienciaViagem();
    }

    @Test
    public void testTitulo() {
        // Defina o título da experiência
        experienciaViagem.setTitulo("Minha Viagem");

        // Verifique se o título foi definido corretamente
        assertEquals("Minha Viagem", experienciaViagem.getTitulo());
    }

    @Test
    public void testTexto() {
        // Defina o texto da experiência
        experienciaViagem.setTexto("Esta é a minha experiência de viagem.");

        // Verifique se o texto foi definido corretamente
        assertEquals("Esta é a minha experiência de viagem.", experienciaViagem.getTexto());
    }

    @Test
    public void testMidias() {
        // Crie uma lista de mídias
        List<Midia> midias = new ArrayList<>();
        midias.add(new Midia());
        midias.add(new Midia());

        // Defina as mídias na experiência
        experienciaViagem.setMidias(midias);

        // Verifique se as mídias foram definidas corretamente
        assertNotNull(experienciaViagem.getMidias());
        assertEquals(2, experienciaViagem.getMidias().size());
    }

    @Test
    public void testUsuario() {
        // Crie um usuário
        Usuario usuario = new Usuario();

        // Associe o usuário à experiência
        experienciaViagem.setUsuario(usuario);

        // Verifique se o usuário foi associado corretamente
        assertNotNull(experienciaViagem.getUsuario());
        assertEquals(usuario, experienciaViagem.getUsuario());
    }
}
//Este exemplo de teste unitário cria uma instância de ExperienciaViagem, define valores para seus campos e verifica se os valores foram definidos corretamente.