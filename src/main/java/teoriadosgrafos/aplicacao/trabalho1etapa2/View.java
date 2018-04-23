package teoriadosgrafos.aplicacao.trabalho1etapa2;

import net.miginfocom.swing.MigLayout;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import teoriadosgrafos.Grafo;
import teoriadosgrafos.GrafoPonderado;
import teoriadosgrafos.aplicacao.trabalho2etapa2.TabelaComScroll;
import teoriadosgrafos.metodosdebusca.ponderado.Dijkstra;
import teoriadosgrafos.metodosdebusca.ponderado.ResultadoPonderado;

public class View extends JFrame {
	/**
	 * Root.
	 */
	private JPanel root;
	
	View( String s ) throws HeadlessException {
		super( s );
		/* Instanciar painel raiz da aplicação */
		root = new JPanel( new MigLayout( "wrap 1" ) );
		/* Definir o container raiz da view */
		setContentPane( root );
		/* Definir operação de fechamento */
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		/* Inicializar componentes */
		criarComponentes();
		/* Dimensionar a view de acordo com seus componentes */
		pack();
		/* Centralizar Janela */
		setLocationRelativeTo( null );
	}
	
	public void criarComponentes() {
		int NA = Grafo.INFINITO;
		/* Matriz geradora do grafo */
		int matriz[][] = new int[][] {
				//       a   b   c   d   e   f   g   h   i   j   l   m
				/*a*/ { NA, 61, 50, 42, NA, NA, NA, NA, NA, NA, NA, NA },
				/*b*/ { 61, NA, NA, 32, NA, 29, NA, NA, NA, 17, NA, NA },
				/*c*/ { 50, NA, NA, 56, 67, NA, NA, NA, NA, NA, NA, NA },
				/*d*/ { 42, 32, 56, NA, 45, 62, 85, NA, NA, NA, NA, NA },
				/*e*/ { NA, NA, 67, 45, NA, NA, 72, NA, 73, NA, NA, NA },
				/*f*/ { NA, 29, NA, 62, NA, NA, 20, 35, NA, 30, 45, NA },
				/*g*/ { NA, NA, NA, 85, 72, 20, NA, 40, 60, NA, NA, 32 },
				/*h*/ { NA, NA, NA, NA, NA, 35, 40, NA, NA, NA, 50, 21 },
				/*i*/ { NA, NA, NA, NA, 73, NA, 60, NA, NA, NA, NA, 50 },
				/*j*/ { NA, 17, NA, NA, NA, 30, NA, NA, NA, NA, 30, NA },
				/*l*/ { NA, NA, NA, NA, NA, 45, NA, 50, NA, 30, NA, NA },
				/*m*/ { NA, NA, NA, NA, NA, 32, NA, 21, 50, NA, NA, NA }
		};
		/* Instanciar o grafo */
		GrafoPonderado grafo = new GrafoPonderado( matriz, true );
		//Destino ignorado utilizando o algoritmo de Dijkstra
		ResultadoPonderado resultado = Dijkstra.INSTANCE.buscar( 1, grafo );
		/* Nomes das colunas das tabelas */
		String[] colunas = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l", "m" };
		/* Variável que armazenará os dados passados para as tabelas */
		String[][] dados;
		/* Criar label1 */
		JLabel label1 = new JLabel( "Tabela de distâncias" );
		/* Criar label2 */
		JLabel label2 = new JLabel( "Tabela de predecessores" );
		/* Adicionar componentes ao painel */
		/* Label1 */
		root.add( label1, "align center" );
		/* Tabela 1 */
		dados = new String[][] { resultado.distanciasAsStringArray() };
		root.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
		/* Label2 */
		root.add( label2, "align center" );
		/* Tabela 2 */
		dados = new String[][] { resultado.precedentesAsStringArray() };
		root.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
	}
}
