import com.Trabalho_Poo2.model.ExperienciaViagem;
import com.Trabalho_Poo2.model.Midia;
import com.Trabalho_Poo2.model.TipoMidia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MidiaTest {

    @Test
    public void testConstrutorComCamposObrigatorios() {
        // Crie uma instância de ExperienciaViagem (pode ser um mock ou um objeto real)
        ExperienciaViagem experienciaViagem = new ExperienciaViagem();

        // Crie uma instância de Midia
        Midia midia = new Midia("Descrição", "caminho/arquivo.png", TipoMidia.IMAGEM, experienciaViagem);

        // Verifique se os campos foram atribuídos corretamente
        assertEquals("Descrição", midia.getDescricao());
        assertEquals("caminho/arquivo.png", midia.getCaminhoArquivo());
        assertEquals(TipoMidia.IMAGEM, midia.getTipoMidia());
        assertEquals(experienciaViagem, midia.getExperienciaViagem());
    }

    @Test
    public void testConstrutorPadrao() {
        // Crie uma instância de Midia usando o construtor padrão
        Midia midia = new Midia();

        // Verifique se a instância foi criada corretamente
        assertNotNull(midia);
    }
}
//Estes testes verificam se os campos da classe Midia estão sendo atribuídos corretamente quando você cria uma instância da classe.