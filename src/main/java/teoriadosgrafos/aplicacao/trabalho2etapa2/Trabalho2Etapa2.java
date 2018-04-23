package teoriadosgrafos.aplicacao.trabalho2etapa2;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import teoriadosgrafos.excecoes.ForaDoGrafoException;

/**
 * The type T 2 e 2 questao 2.
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class Trabalho2Etapa2 {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 *
	 * @throws ForaDoGrafoException the fora do grafo exception
	 */
	public static void main( String[] args ) {
		/* Definir o Look And Feel da aplicação */
		try {
			UIManager.setLookAndFeel( new AcrylLookAndFeel() );
		} catch ( UnsupportedLookAndFeelException e ) {
			e.printStackTrace();
		}
		/* Criar frame */
		JFrame frame = new JFrame( "Etapa 2 Trabalho 2 TeoriaDosGrafos" );
		/* Criar painel raiz */
		JPanel panel = new JPanel( new MigLayout( "wrap 1" ) );
		/* Scroll pane que embrulhará o painel raiz, permitindo o scroll */
		JScrollPane scroll = new JScrollPane( panel );
		/* Desativar o scroll horizontal */
		scroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		/* Definir velocidade do scroll vertical */
		scroll.getVerticalScrollBar().setUnitIncrement( 16 );
		/* Adicionar componentes ao painel */
		panel.add( new Questao1().rootPanel, "align center" );
		panel.add( new Questao2().rootPanel, "align center" );
		panel.add( new Questao3().rootPanel, "align center" );
		/* Definir painel raiz da aplicação no frame */
		frame.setContentPane( scroll );
		/* Operação de fechamento */
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		/* Dimensiona os componentes */
		frame.pack();
		/* Define o tamanho da janela */
		frame.setSize( ( (int) ( new Questao2().rootPanel.getPreferredSize().width * 1.05 ) ), 600 );
		/* Centraliza a janela da aplicação */
		frame.setLocationRelativeTo( null );
		/* Torna a aplicação visivel */
		frame.setVisible( true );
	}
}
