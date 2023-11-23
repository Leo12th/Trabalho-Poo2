import com.Trabalho_Poo2.model.TipoMidia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoMidiaTest {

    @Test
    public void testDescricao() {
        // Verifique se as descrições das enumerações estão corretas
        assertEquals("Imagem", TipoMidia.IMAGEM.getDescricao());
        assertEquals("Vídeo", TipoMidia.VIDEO.getDescricao());
        assertEquals("Documento", TipoMidia.DOCUMENTO.getDescricao());
        assertEquals("Áudio", TipoMidia.AUDIO.getDescricao());
    }
}
//Esses testes verificam se as descrições das enumerações em TipoMidia estão definidas corretamente.