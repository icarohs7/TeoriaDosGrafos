package teoriadosgrafos.aplicacao.trabalho2etapa2;

import net.miginfocom.swing.MigLayout;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import teoriadosgrafos.Grafo;
import teoriadosgrafos.GrafoPonderado;
import teoriadosgrafos.arvoregeradora.Kruskal;
import teoriadosgrafos.arvoregeradora.MST;
import teoriadosgrafos.arvoregeradora.Prim;
import teoriadosgrafos.excecoes.CicloNegativoException;
import teoriadosgrafos.metodosdebusca.ponderado.FloydWarshall;
import teoriadosgrafos.metodosdebusca.ponderado.ResultadoWarshall;

/* Compilação das classes contendo as interfaces dos 3 enunciados */

/**
 * Classe agrupando elementos comuns
 */
abstract class InterfacesGraficas {
	/**
	 * Fonte utilizada nos títulus
	 */
	static Font h1;
	/**
	 * Fonte utilizada nos subtítulos
	 */
	static Font h2;
	
	static {
		/* Definição da fonte dos títulos */
		h1 = new Font( "Consolas", Font.BOLD, 28 );
		/* Definição da fonte dos subtítulos */
		h2 = new Font( "Consolas", Font.PLAIN, 20 );
	}
	
	/**
	 * Substitui o valor infinito em uma matriz pelo símbolo infinito
	 *
	 * @param matriz the matriz
	 *
	 * @return string [ ] [ ]
	 */
	static String[][] replaceInfinity( String[][] matriz ) {
		/* String que armazenará a matriz com o símbolo já substituído */
		String[][] novaString = new String[matriz.length][matriz[0].length];
		
		for ( int i = 0; i < matriz.length; i++ ) {
			for ( int j = 0; j < matriz[0].length; j++ ) {
				if ( matriz[i][j].equals( Integer.toString( Grafo.INFINITO ) ) ) {
					/* Substituir os valores infinitos pelo símbolo */
					novaString[i][j] = Character.toString( '\u221e' );
				} else {
					/* Nos outros elementos, manter os mesmos */
					novaString[i][j] = matriz[i][j];
				}
			}
		}
		/* Retornar a nova string */
		return novaString;
	}
}

/**
 * The type Questao 1.
 */
class Questao1 {
	/**
	 * Root panel.
	 */
	JPanel rootPanel;
	
	/**
	 * Instantiates a new Questao 1.
	 */
	Questao1() {
		/* Definição do painel raiz */
		rootPanel = new JPanel( new MigLayout( "wrap 1" ) );
		/* Criar e Definir componentes gráficos */
		criarComponentes();
	}
	
	/**
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Colunas da tabela */
		String[] colunas = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		/* Dados da tabela */
		String[][] dados;
		
		/* Título */
		JLabel titulo = new JLabel( "Questão 1. Menor caminho Floyd-Warshall" );
		titulo.setFont( InterfacesGraficas.h1 );
		
		/* Adição dos componentes ao painel */
		/* Título */
		rootPanel.add( titulo, "align center" );
		/* Tabela */
		try {
			/* Tentar gerar a tabela */
			dados = InterfacesGraficas.replaceInfinity( primeiroGrafo().getDistanciasAsStringArray() );
			rootPanel.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
		} catch ( CicloNegativoException e ) {
			/* Caso haja ciclo negativo, tratar */
			rootPanel.add(
					new JLabel( "Erro: Não foi possível gerar a" +
					            " tabela de distâncias, há um ciclo" +
					            " negativo no grafo " ),
					"align center"
			);
		}
	}
	
	/**
	 * Primeiro grafo resultado warshall.
	 *
	 * @return the resultado warshall
	 */
	private ResultadoWarshall primeiroGrafo() {
		/* Definir o valor para a não adjacência(NA) */
		int NA = Grafo.INFINITO;
		/* Definição da matriz de adjacência */
		int matriz[][] = new int[][] {
				{ NA, 5, NA, NA, NA, 8, NA, NA, NA, 2, },
				{ 5, NA, -1, NA, NA, 3, NA, NA, NA, 3, },
				{ NA, -1, NA, -1, NA, 3, 3, 2, 3, 3, },
				{ NA, NA, -1, NA, 8, 1, 7, 4, 1, 6, },
				{ NA, NA, NA, 8, NA, NA, 9, NA, 4, NA, },
				{ 8, 3, 3, 1, NA, NA, 4, -1, NA, NA, },
				{ NA, NA, 3, 7, 9, 4, NA, -4, 6, NA, },
				{ NA, NA, 2, 4, NA, -1, -4, NA, NA, NA, },
				{ NA, NA, 3, 1, 4, NA, 6, NA, NA, 7, },
				{ 2, 3, 3, 6, NA, NA, NA, NA, 7, NA }
		};
		/* Criação do grafo */
		GrafoPonderado grafo = new GrafoPonderado( matriz, false );
		/* Gerar a matriz de menores distâncias utilizando o algoritmo de Floyd-Warshall */
		return FloydWarshall.INSTANCE.buscar( grafo );
	}
}

/**
 * The type Questao 2.
 */
class Questao2 {
	/**
	 * Painel raiz da aplicação
	 */
	JPanel rootPanel;
	
	/**
	 * Instantiates a new Questao 2.
	 */
	Questao2() {
		/* Definição do painel raiz da interface gráfica da aplicação */
		rootPanel = new JPanel( new MigLayout( "wrap 1" ) );
		/* Criar os componentes visuais */
		criarComponentes();
	}
	
	/**
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Definição da tabela contendo o resultado do primeiro grafo */
		/* Nomes das colunas */
		String[] colunas = new String[] {
				"1", "2", "3", "4", "5", "6"
		};
		/* Matriz de strings que conterá os dados das células nas tabelas */
		String[][] dados;
		/* Título */
		JLabel titulo = new JLabel( "Questão 2. MST utilizando Prim" );
		titulo.setFont( InterfacesGraficas.h1 );
		/* Primeira Label */
		JLabel label1 = new JLabel( "Grafo 1" );
		label1.setFont( InterfacesGraficas.h2 );
		/* Segunda Label */
		JLabel label2 = new JLabel( "Grafo 2" );
		label2.setFont( InterfacesGraficas.h2 );
		/* Terceira Label */
		JLabel label3 = new JLabel( "Grafo 3" );
		label3.setFont( InterfacesGraficas.h2 );
		
		/* Adição dos componentes ao painel */
		/* Título */
		rootPanel.add( titulo, "align center" );
		/* Primeira Label */
		rootPanel.add( label1, "align center" );
		/* Tabela1 */
		dados = InterfacesGraficas.replaceInfinity( primeiroGrafo().getTreeAsString() );
		rootPanel.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
		/* Segunda Label */
		rootPanel.add( label2, "align center" );
		/* Tabela 2 */
		dados = InterfacesGraficas.replaceInfinity( segundoGrafo().getTreeAsString() );
		colunas = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		rootPanel.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
		/* Terceira Label */
		rootPanel.add( label3, "align center" );
		/* Tabela 3 */
		dados = InterfacesGraficas.replaceInfinity( terceiroGrafo().getTreeAsString() );
		colunas = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m" };
		rootPanel.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
		
	}
	
	/**
	 * Primeiro grafo mst.
	 *
	 * @return the mst
	 */
	private MST primeiroGrafo() {
		/* Criação do grafo */
		GrafoPonderado grafo = new GrafoPonderado( 6, false );
		/* Adição das arestas */
		/* Vértice 1 */
		grafo.addAresta( 1, 2, 1 );
		grafo.addAresta( 1, 3, 3 );
		/* Vértice 2 */
		grafo.addAresta( 2, 3, 1 );
		grafo.addAresta( 2, 4, 2 );
		grafo.addAresta( 2, 5, 3 );
		/* Vértice 3 */
		grafo.addAresta( 3, 5, 2 );
		/* Vértice 4 */
		grafo.addAresta( 4, 5, -3 );
		grafo.addAresta( 4, 6, 3 );
		/* Vértice 5 */
		grafo.addAresta( 5, 6, 2 );
		/* Gerar MST */
		return Prim.INSTANCE.gerar( grafo );
	}
	
	/**
	 * Segundo grafo mst.
	 *
	 * @return the mst
	 */
	private MST segundoGrafo() {
		/* Criação do grafo */
		GrafoPonderado grafo = new GrafoPonderado( 10, false );
		/* Adição das arestas */
		/* Vértice 1 */
		grafo.addAresta( 1, 2, 11 );
		grafo.addAresta( 1, 3, 1 );
		grafo.addAresta( 1, 4, 14 );
		/* Vértice 2 */
		grafo.addAresta( 2, 3, 10 );
		grafo.addAresta( 2, 5, 6 );
		grafo.addAresta( 2, 6, 8 );
		/* Vértice 3 */
		grafo.addAresta( 3, 4, 7 );
		grafo.addAresta( 3, 6, 7 );
		/* Vértice 4 */
		grafo.addAresta( 4, 6, 9 );
		grafo.addAresta( 4, 7, 6 );
		/* Vértice 5 */
		grafo.addAresta( 5, 6, 5 );
		grafo.addAresta( 5, 8, 3 );
		/* Vértice 6 */
		grafo.addAresta( 6, 7, 2 );
		grafo.addAresta( 6, 8, 6 );
		grafo.addAresta( 6, 9, 5 );
		/* Vértice 7 */
		grafo.addAresta( 7, 10, 3 );
		/* Vértice 8 */
		grafo.addAresta( 8, 9, 1 );
		/* Vértice 9 */
		grafo.addAresta( 9, 10, 4 );
		/* Gerar MST */
		return Prim.INSTANCE.gerar( grafo );
	}
	
	/**
	 * Terceiro grafo mst.
	 *
	 * @return the mst
	 */
	private MST terceiroGrafo() {
		/* Criação do grafo */
		GrafoPonderado grafo = new GrafoPonderado( 13, false );
		/* Vértice a */
		grafo.addAresta( 1, 2, 5 );
		grafo.addAresta( 1, 7, 1 );
		grafo.addAresta( 1, 8, 3 );
		/* Vértice b */
		grafo.addAresta( 2, 3, 1 );
		grafo.addAresta( 2, 6, 4 );
		grafo.addAresta( 2, 7, 1 );
		/* Vértice c */
		grafo.addAresta( 3, 4, 7 );
		grafo.addAresta( 3, 6, 2 );
		/* Vértice d */
		grafo.addAresta( 4, 5, 2 );
		grafo.addAresta( 4, 6, 6 );
		/* Vértice e */
		grafo.addAresta( 5, 6, 7 );
		grafo.addAresta( 5, 10, 3 );
		/* Vértice f */
		grafo.addAresta( 6, 7, 1 );
		grafo.addAresta( 6, 9, 2 );
		grafo.addAresta( 6, 10, 3 );
		/* Vértice g */
		grafo.addAresta( 7, 8, 6 );
		grafo.addAresta( 7, 9, 5 );
		/* Vértice h */
		grafo.addAresta( 8, 9, 4 );
		grafo.addAresta( 8, 11, 8 );
		/* Vértice i */
		grafo.addAresta( 9, 10, 8 );
		grafo.addAresta( 9, 11, 7 );
		grafo.addAresta( 9, 12, 4 );
		/* Vértice j */
		grafo.addAresta( 10, 12, 3 );
		grafo.addAresta( 10, 13, 4 );
		/* Vértice k */
		grafo.addAresta( 11, 12, 2 );
		/* Vértice l */
		grafo.addAresta( 12, 13, 3 );
		/* Gerar MST */
		return Prim.INSTANCE.gerar( grafo );
	}
}

/**
 * The type Questao 3.
 */
class Questao3 {
	/**
	 * Root panel.
	 */
	JPanel rootPanel;
	
	/**
	 * Instantiates a new Questao 3.
	 */
	Questao3() {
		/* Definição do painel raiz */
		rootPanel = new JPanel( new MigLayout( "wrap 1" ) );
		/* Criar e Definir componentes gráficos */
		criarComponentes();
	}
	
	/**
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Nomes das colunas utilizadas nas tabelas */
		String[] colunas;
		/* Dados contidos nas tabelas */
		String[][] dados;
		/* Título */
		JLabel titulo = new JLabel( "Questão 3. MST utilizando Kruskal" );
		titulo.setFont( InterfacesGraficas.h1 );
		/* Label 1 */
		JLabel label1 = new JLabel( "Grafo 1" );
		label1.setFont( InterfacesGraficas.h2 );
		/* Label 2 */
		JLabel label2 = new JLabel( "Grafo 2" );
		label2.setFont( InterfacesGraficas.h2 );
		/* Adicionar Componentes ao Painel */
		/* Título */
		rootPanel.add( titulo, "align center" );
		/* Label 1 */
		rootPanel.add( label1, "align center" );
		/* Tabela 1 */
		dados = InterfacesGraficas.replaceInfinity( primeiroGrafo().getTreeAsString() );
		colunas = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		rootPanel.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
		/* Label 2 */
		rootPanel.add( label2, "align center" );
		/* Tabela 2 */
		dados = InterfacesGraficas.replaceInfinity( segundoGrafo().getTreeAsString() );
		colunas = new String[] { "1", "2", "3", "4", "6", "7", "8" };
		rootPanel.add( new TabelaComScroll( dados, colunas ).getScrollableTable(), "align center" );
	}
	
	/**
	 * Primeiro grafo mst.
	 *
	 * @return the mst
	 */
	private MST primeiroGrafo() {
		/* Definição do grafo */
		GrafoPonderado grafo = new GrafoPonderado( 10, false );
		/* Adição das Arestas */
		/* Vértice a */
		grafo.addAresta( 1, 2, 60 );
		grafo.addAresta( 1, 3, 54 );
		grafo.addAresta( 1, 4, 42 );
		/* Vértice b */
		grafo.addAresta( 2, 4, 71 );
		grafo.addAresta( 2, 6, 29 );
		/* Vértice c */
		grafo.addAresta( 3, 4, 56 );
		grafo.addAresta( 3, 5, 67 );
		/* Vértice d */
		grafo.addAresta( 4, 5, 26 );
		grafo.addAresta( 4, 6, 52 );
		grafo.addAresta( 4, 7, 87 );
		/* Vértice e */
		grafo.addAresta( 5, 7, 70 );
		grafo.addAresta( 5, 9, 73 );
		/* Vértice f */
		grafo.addAresta( 6, 7, 20 );
		grafo.addAresta( 6, 8, 25 );
		/* Vértice g */
		grafo.addAresta( 7, 8, 36 );
		grafo.addAresta( 7, 9, 59 );
		grafo.addAresta( 7, 10, 32 );
		/* Vértice h */
		grafo.addAresta( 8, 10, 25 );
		/* Vértice i */
		grafo.addAresta( 9, 10, 26 );
		/* Gerar MST utilizando o algoritmo de Kruskal */
		return Kruskal.INSTANCE.gerar( grafo );
	}
	
	/**
	 * Segundo grafo mst.
	 *
	 * @return the mst
	 */
	private MST segundoGrafo() {
		/* Criação do grafo */
		GrafoPonderado grafo = new GrafoPonderado( 7, false );
		/* Adição das arestas */
		/* Vértice 1 */
		grafo.addAresta( 1, 2, 2 );
		grafo.addAresta( 1, 3, 4 );
		grafo.addAresta( 1, 4, 5 );
		/* Vértice 2 */
		grafo.addAresta( 2, 4, 2 );
		grafo.addAresta( 2, 5, 7 );
		/* Vértice 3 */
		grafo.addAresta( 3, 4, 1 );
		grafo.addAresta( 3, 6, 4 );
		/* Vértice 4 */
		grafo.addAresta( 4, 5, 2 );
		grafo.addAresta( 4, 6, 3 );
		/* Vértice 6 */
		grafo.addAresta( 5, 6, 1 );
		grafo.addAresta( 5, 7, 4 );
		/* Vértice 7 */
		grafo.addAresta( 6, 7, 7 );
		/* Gerar MST utilizando o algoritmo de Kruskal */
		return Kruskal.INSTANCE.gerar( grafo );
	}
}