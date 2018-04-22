package teoriadosgrafos;

import java.util.LinkedList;
import java.util.Queue;

import teoriadosgrafos.excecoes.CaminhoNaoEncontradoException;
import teoriadosgrafos.metodosdebusca.naoponderado.BuscaEmLargura;
import teoriadosgrafos.metodosdebusca.naoponderado.BuscaEmProfundidade;

/**
 * Classe representando um grafo não ponderado e suas operações
 *
 * @author Icaro D. Temponi
 */
public class GrafoNaoPonderado extends Grafo {
	public GrafoNaoPonderado( int[][] matrizDeAdjacencia ) throws CaminhoNaoEncontradoException {
		this( matrizDeAdjacencia, false );
	}
	
	/**
	 * Construir o grafo não ponderado, se assegurando que nenhuma célula da matriz de adjacência contem valor
	 * diferente de 0 ou 1
	 *
	 * @param matrizDeAdjacencia Matriz utilizada para construir o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 *
	 * @throws CaminhoNaoEncontradoException Lança uma exceção caso o destino seja inalcançável apartir da origem
	 */
	public GrafoNaoPonderado( int[][] matrizDeAdjacencia, boolean direcionado ) throws CaminhoNaoEncontradoException {
		super( matrizDeAdjacencia, direcionado );
		
		//Permitir somente 0 ou 1 nos valores das células da matriz de adjacência
		for ( int[] linha : matrizDeAdjacencia ) {
			for ( int celula : linha ) {
				if ( !( celula == 0 || celula == 1 ) ) {
					throw new CaminhoNaoEncontradoException( "A matriz de adjacência para grafos ponderados só suporta os valores 0 ou 1" );
				}
			}
		}
	}
	
	/**
	 * Executar a busca em um grafo não ponderado partindo de um vértice origem em direção a um vértice destino
	 *
	 * @param origem  O vértice de origem
	 * @param destino O vértice que se deseja alcançar apartir da origem
	 * @param metodo  O método de busca a ser utilizado
	 *
	 * @return Uma fila contendo o caminho da origem ao destino
	 *
	 * @throws CaminhoNaoEncontradoException Lança uma exceção caso o destino seja inalcançável apartir da origem
	 */
	public Queue buscar( int origem, int destino, MetodoDeBusca metodo ) throws CaminhoNaoEncontradoException {
		switch ( metodo ) {
			case LARGURA:
				return BuscaEmLargura.buscar( origem, destino, this );
			case PROFUNDIDADE:
				return BuscaEmProfundidade.buscar( origem, destino, this );
			default:
				throw new CaminhoNaoEncontradoException( "Método de busca inválido" );
		}
	}
	
	
	/**
	 * Exclui uma aresta conectando 2 vértices
	 *
	 * @param origem  O vértice a partir de qual a aresta sairá
	 * @param destino O vértice que será conectado ao vértice origem
	 */
	public void excluirAresta( int origem, int destino ) {
		origem--;
		destino--;
		
		matrizDeAdjacencia[origem][destino] = 0;
		if ( !direcionado ) {
			matrizDeAdjacencia[destino][origem] = 0;
		}
	}
	
	/**
	 * Retorna uma fila contendo os vértices adjacentes ao vértice indicado
	 *
	 * @param vertice Vértice do qual se deseja obter os vizinhos
	 *
	 * @return Fila contendo os vértices vizinhos ao vértice selecionado
	 */
	public Queue<Integer> logConexoes( int vertice ) {
		Queue<Integer> conexoes = new LinkedList<>();
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( matrizDeAdjacencia[vertice][i] == 1 ) {
				conexoes.offer( i + 1 );
			}
		}
		
		return conexoes;
	}
	
	//Enumeração dos tipos de busca
	public enum MetodoDeBusca {
		LARGURA, PROFUNDIDADE
	}
}
