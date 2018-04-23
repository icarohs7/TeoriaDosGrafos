package teoriadosgrafos.aplicacao.trabalho1etapa2;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Trabalho Extra, Etapa 2, Teoria dos Grafos
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class TrabalhoExtraEtapa2 {
	
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main( String[] args ) {
		/* Definir Look&Feel da aplicação */
		try {
			UIManager.setLookAndFeel( new AcrylLookAndFeel() );
		} catch ( UnsupportedLookAndFeelException e ) {
			e.printStackTrace();
		}
		/* Criar a view */
		new View( "Teoria dos Grafos - Trabalho Extra Etapa 2" ).setVisible( true );
	}
}
