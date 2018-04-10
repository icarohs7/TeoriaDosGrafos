package edu.grafos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Classe representando um grafo e suas operações
 *
 * @author Icaro D. Temponi
 * @version 2.0
 */
public abstract class Grafo {
	protected int[][] matrizDeAdjacencia;
	protected int[] visitado;
	protected boolean direcionado;
	
	
	/**
	 * Modelo padrão para construção de um grafo
	 *
	 * @param matrizDeAdjacencia Matriz que compõe o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 *
	 * @since Versão 1.0
	 */
	protected Grafo( int[][] matrizDeAdjacencia, boolean direcionado ) {
		this.matrizDeAdjacencia = matrizDeAdjacencia;
		this.direcionado = direcionado;
	}
	
	
	/**
	 * Retorna a matriz de adjacência do grafo instanciado
	 *
	 * @return um array bidimensional de valores inteiros referentes à
	 * adjacencia vértice x vértice
	 *
	 * @since Versão 1.0
	 */
	public int[][] getMatrizDeAdjacencia() {
		return matrizDeAdjacencia;
	}
	
	/**
	 * Desmarcar todos os vértices visitados
	 *
	 * @since Versão 1.0
	 */
	protected void resetVisitados() {
		visitado = new int[matrizDeAdjacencia.length];
	}
	
	/**
	 * Classe representando um grafo ponderado e suas operações
	 *
	 * @author Icaro D. Temponi
	 * @version 1.0
	 * @since Versão 2.0
	 */
	public static class GrafoPonderado extends Grafo {
		public GrafoPonderado( int[][] matrizDeAdjacencia, boolean direcionado ) {
			super( matrizDeAdjacencia, direcionado );
		}
		
		protected Queue buscar( int origem, int destino, int metodoDeBusca ) {
			return null;
		}
		
		//Enumeração dos tipos de busca
		public enum MetodoDeBusca {
			DIJKSTRA
		}
		
	}
	
	/**
	 * Classe representando um grafo ponderado e suas operações
	 *
	 * @author Icaro D. Temponi
	 * @version 1.0
	 * @since Versão 2.0
	 */
	public static class GrafoNaoPonderado extends Grafo {
		/**
		 * Construir o grafo não ponderado, se assegurando que nenhuma célula da matriz de adjacência contem valor
		 * diferente de 0 ou 1
		 *
		 * @param matrizDeAdjacencia Matriz utilizada para construir o grafo
		 * @param direcionado        Se o grafo é ou não direcionado
		 *
		 * @throws CaminhoNaoEncontradoException Lança uma exceção caso o destino seja inalcançável apartir da origem
		 * @since Versão 1.0
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
		
		public GrafoNaoPonderado( int[][] matrizDeAdjacencia ) throws CaminhoNaoEncontradoException {
			this( matrizDeAdjacencia, false );
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
		 * @since Versão 1.0
		 */
		protected Queue buscar( int origem, int destino, MetodoDeBusca metodo ) throws CaminhoNaoEncontradoException {
			switch ( metodo ) {
				case LARGURA:
					return buscaEmLargura( origem, destino );
				case PROFUNDIDADE:
					return buscaEmProfundidade( origem, destino );
				default:
					throw new CaminhoNaoEncontradoException( "Método de busca inválido" );
			}
		}
		
		/**
		 * Executa uma busca em profundidade no grafo
		 *
		 * @param origem  O nó de origem da busca
		 * @param destino O nó de destino da busca
		 *
		 * @return A fila contendo o caminho percorrido
		 *
		 * @throws CaminhoNaoEncontradoException caso o destino não seja encontrado
		 * @since Versão 1.0
		 */
		public LinkedList buscaEmProfundidade( int origem, int destino ) throws CaminhoNaoEncontradoException {
			//Utilizar a numeração utilizada pela máquina na numeração de vetores 0..X
			origem--;
			destino--;
			
			//Desmarcar todos os nós visitados
			resetVisitados();
			
			//Instanciar a fila e pilha utilizadas no processo de busca
			LinkedList<Integer> caminho = new LinkedList<>();
			Stack<Integer> caminhoInvertido = new Stack<>();
			
			//Lançar uma exceção caso o destino não seja encontrado
			if ( !DFS( origem, destino, caminhoInvertido ) ) {
				throw new CaminhoNaoEncontradoException( "Destino não encontrado." );
			}
			
			//Converter a pilha contendo o caminho invertido para fila
			caminho.addAll( caminhoInvertido );
			
			//Retornar o caminho da origem para o destino
			return caminho;
		}
		
		/**
		 * Executa uma busca em largura no grafo
		 *
		 * @param origem  O nó de origem da busca
		 * @param destino O nó de destino da busca
		 *
		 * @return A fila contendo o caminho percorrido
		 *
		 * @throws CaminhoNaoEncontradoException caso o destino não seja encontrado
		 * @since Versão 1.0
		 */
		public LinkedList buscaEmLargura( int origem, int destino ) throws CaminhoNaoEncontradoException {
			//Utilizar a numeração utilizada pela máquina na numeração de vetores 0..X
			origem--;
			destino--;
			
			//Resetar o vetor de vértices visitados
			resetVisitados();
			//Marcar o primeiro vértice visitado
			visitado[origem] = 1;
			
			//Instanciar fila e pilha usadas no processo de busca
			LinkedList<Integer> caminho = new LinkedList<>();
			Stack<Integer> caminhoInvertido = new Stack<>();
			
			//Lançar exceção caso o destino não seja encontrado
			if ( !BFS( origem, destino, caminho, caminhoInvertido ) ) {
				throw new CaminhoNaoEncontradoException( "Destino não encontrado." );
			}
			
			//Limpar a fila utilizada no processo de busca para reutilizá-la como retorno
			caminho.clear();
			
			//Converter a pilha contendo o caminho invertido para fila
			caminho.addAll( caminhoInvertido );
			
			//Retornar o caminho da origem para o destino
			return caminho;
		}
		
		/**
		 * Procedimento recursivo responsável pela busca em profundidade
		 *
		 * @param origem           O nó a partir do qual a busca será iniciada
		 * @param destino          O nó que se deseja encontrar partindo da origem
		 * @param caminhoInvertido Pilha utilizada para armazenar o caminho
		 *
		 * @return Verdadeiro se o caminho for encontrado e Falso caso contrário
		 *
		 * @since Versão 1.0
		 */
		private boolean DFS( int origem, int destino, Stack<Integer> caminhoInvertido ) {
			//Marcar o vértice atual
			visitado[origem] = 1;
			
			//adicioná-lo à pilha
			caminhoInvertido.push( origem + 1 );
			
			//e retornar caso o destino tenha sido alcançado
			if ( origem == destino ) {
				return true;
			}
			
			for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
				//Para cada vértice adjacente
				if ( matrizDeAdjacencia[origem][i] == 1 ) {
					//e não visitado
					if ( visitado[i] == 0 ) {
						//descer um nível na busca
						if ( DFS( i, destino, caminhoInvertido ) ) {
							//e voltar caso o destino seja encontrado em uma das chamadas recursivas
							return true;
						}
					}
				}
			}
			
			//Desempilhar os vértices que levam ao caminho incorreto
			caminhoInvertido.pop();
			
			//e retornar avisando que o destino não foi encontrado no galho recursivo atual
			return false;
		}
		
		/**
		 * Procedimento recursivo responsável pela busca em largura
		 *
		 * @param origem           O nó a partir do qual a busca será iniciada
		 * @param destino          O nó que se deseja encontrar partindo da origem
		 * @param proximosVertices Fila utilizada para armazenar os próximos
		 *                         vértices a serem visitados
		 * @param caminhoInvertido Pilha utilizada para armazenar o caminho
		 *
		 * @return Verdadeiro se o caminho for encontrado e Falso caso contrário
		 *
		 * @since Versão 1.0
		 */
		private boolean BFS( int origem, int destino, LinkedList<Integer> proximosVertices, Stack<Integer> caminhoInvertido ) {
			//Empilhar vértice visitado
			caminhoInvertido.push( origem + 1 );
			
			//Retornar caso o destino seja alcançado
			if ( origem == destino ) {
				return true;
			}
			
			//Laço utilizado para enfileirar os visinhos do vértice atual
			for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
				if ( matrizDeAdjacencia[origem][i] == 1 ) {
					if ( visitado[i] == 0 ) {
						visitado[i] = 1;
						proximosVertices.add( i );
					}
				}
			}
			
			//Caso a chamada recursiva no nivel inferior tenha encontrado o destino retornar repassando a informação
			if ( !proximosVertices.isEmpty() ) {
				return BFS( proximosVertices.remove(), destino, proximosVertices, caminhoInvertido );
			}
			
			//Caso a fila esteja vazia retorne falso
			return false;
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
		 *
		 * @since Versão 1.0
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
	
	/**
	 * Exceção representando um caminho inalcançável partindo da origem ao destino
	 */
	public static class CaminhoNaoEncontradoException extends Exception {
		public CaminhoNaoEncontradoException( String s ) {
			super( s );
		}
	}
}
