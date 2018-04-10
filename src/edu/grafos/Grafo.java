package edu.grafos;

import java.util.LinkedList;                                                    //Importação da implementação de pilha contida na JDK
import java.util.List;
import java.util.Stack;                                                            //Importação da implementação de fila contida na JDK

/**
 * Classe contendo a implementação de um grafo direcionado ou não direcionado
 *
 * @author Icaro D. Temponi
 * @version 1.1
 */
public class Grafo {
	
	/**
	 * Vetor de vértices visitados
	 */
	private int[] visitado;
	
	/**
	 * Matriz de adjacência do grafo
	 */
	private int[][] matrizDeAdjacencia;
	
	/**
	 * Se o grafo é ou não direcionado
	 */
	private boolean direcionado;
	
	/**
	 * Construir o grafo, definindo sua matriz de adjacência e se ele é
	 * direcionado
	 *
	 * @param matrizDeAdjacencia A matriz de adjacência vértice x vértice
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	public Grafo( int[][] matrizDeAdjacencia, boolean direcionado ) {
		setMatrizDeAdjacencia( matrizDeAdjacencia, direcionado );
	}
	
	/**
	 * Construir o grafo assumindo-o como não direcionado
	 *
	 * @param matrizDeAdjacencia A matriz de adjacência vértice x vértice
	 */
	public Grafo( int[][] matrizDeAdjacencia ) {
		setMatrizDeAdjacencia( matrizDeAdjacencia, false );
	}
	
	/**
	 * Retorna a matriz de adjacência do grafo instanciado
	 *
	 * @return uma array bidimensional de valores inteiros referentes à
	 * adjacencia vértice x vértice
	 */
	public int[][] getMatrizDeAdjacencia() {
		return matrizDeAdjacencia;
	}
	
	/**
	 * Definir a matriz de adjacência que construirá o grafo
	 *
	 * @param matrizDeAdjacencia A matriz de adjacencia correspondente ao grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	public final void setMatrizDeAdjacencia( int[][] matrizDeAdjacencia, boolean direcionado ) {
		this.matrizDeAdjacencia = matrizDeAdjacencia;
		this.direcionado = direcionado;
		resetVisitados();
	}
	
	/**
	 * Desmarcar todos os vértices visitados
	 */
	private void resetVisitados() {
		visitado = new int[matrizDeAdjacencia.length];
	}
	
	/**
	 * Executa uma busca em profundidade no grafo
	 *
	 * @param origem  O nó de origem da busca
	 * @param destino O nó de destino da busca
	 * @return A fila contendo o caminho percorrido
	 * @throws java.lang.Exception caso o destino não seja encontrado
	 */
	public LinkedList buscaEmProfundidade( int origem, int destino ) throws Exception {
		origem--;                                                                //Utilizar a numeração utilizada pela máquina na numeração de vetores 0..X
		destino--;
		
		resetVisitados();                                                        //Desmarcar todos os nós visitados
		
		LinkedList<Integer> caminho = new LinkedList();                            //Instanciar a fila e pilha utilizadas no processo de busca
		Stack<Integer> caminhoInvertido = new Stack();
		
		if ( !DFS( origem, destino, caminhoInvertido ) ) {                            //Lançar uma exceção caso o destino não seja encontrado
			throw new Exception( "Destino não encontrado." );
		}
		caminhoInvertido.forEach( vertice -> {                                    //Converter a pilha para fila
			caminho.add( vertice );
		} );
		
		return caminho;                                                            //Retornar o caminho da origem para o destino
	}
	
	/**
	 * Procedimento recursivo responsável pela busca em profundidade
	 *
	 * @param origem           O nó a partir do qual a busca será iniciada
	 * @param destino          O nó que se deseja encontrar partindo da origem
	 * @param caminhoInvertido Pilha utilizada para armazenar o caminho
	 * @return Verdadeiro se o caminho for encontrado e Falso caso contrário
	 */
	private boolean DFS( int origem, int destino, Stack<Integer> caminhoInvertido ) {
		visitado[origem] = 1;                                                    //Marcar o vértice atual
		caminhoInvertido.push( origem + 1 );                                        //adicioná-lo à pilha
		
		if ( origem == destino ) {                                                //e retornar caso o destino tenha sido alcançado
			return true;
		}
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( matrizDeAdjacencia[origem][i] == 1 ) {                            //Para cada vértice adjacente
				if ( visitado[i] == 0 ) {                                            //e não visitado
					if ( DFS( i, destino, caminhoInvertido ) ) {                    //descer um nível na busca
						return true;                                            //e voltar caso o destino seja encontrado em uma das chamadas recursivas
					}
				}
			}
		}
		
		caminhoInvertido.pop();                                                    //Desempilhar os vértices que levam ao caminho incorreto
		return false;                                                            //e retornar avisando que o destino não foi encontrado no galho recursivo atual
	}
	
	/**
	 * Executa uma busca em largura no grafo
	 *
	 * @param origem  O nó de origem da busca
	 * @param destino O nó de destino da busca
	 * @return A fila contendo o caminho percorrido
	 * @throws java.lang.Exception caso o destino não seja encontrado
	 */
	public LinkedList buscaEmLargura( int origem, int destino ) throws Exception {
		origem--;                                                                //Utilizar a numeração utilizada pela máquina na numeração de vetores 0..X
		destino--;
		
		resetVisitados();                                                        //Resetar o vetor de vértices visitados
		visitado[origem] = 1;                                                    //Marcar o primeiro vértice visitado
		
		LinkedList<Integer> caminho = new LinkedList();                            //Instanciar fila e pilha usadas no processo de busca
		Stack<Integer> caminhoInvertido = new Stack();
		
		if ( !BFS( origem, destino, caminho, caminhoInvertido ) ) {                    //Lançar exceção caso o destino não seja encontrado
			throw new Exception( "Destino não encontrado." );
		}
		
		caminho.clear();                                                        //Limpar a fila utilizada no processo de busca para reutilizá-la como retorno
		
		caminhoInvertido.forEach( vertice -> {                                    //Converter a pilha contendo o caminho invertido para fila utilizando expressão lambda
			caminho.add( vertice );
		} );
		
		return caminho;
	}
	
	/**
	 * Procedimento recursivo responsável pela busca em largura
	 *
	 * @param origem           O nó a partir do qual a busca será iniciada
	 * @param destino          O nó que se deseja encontrar partindo da origem
	 * @param proximosVertices Fila utilizada para armazenar os próximos
	 *                         vértices a serem visitados
	 * @param caminhoInvertido Pilha utilizada para armazenar o caminho
	 * @return Verdadeiro se o caminho for encontrado e Falso caso contrário
	 */
	private boolean BFS( int origem, int destino, LinkedList<Integer> proximosVertices, Stack<Integer> caminhoInvertido ) {
		caminhoInvertido.push( origem + 1 );                                        //Empilhar vértice visitado
		
		if ( origem == destino ) {                                                //Retornar caso o destino seja alcançado
			return true;
		}
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {                    //Laço utilizado para enfileirar os visinhos do vértice atual
			if ( matrizDeAdjacencia[origem][i] == 1 ) {
				if ( visitado[i] == 0 ) {
					visitado[i] = 1;
					proximosVertices.add( i );
				}
			}
		}
		
		if ( !proximosVertices.isEmpty() ) {                                        //Caso a chamada recursiva no nivel inferior tenha encontrado o destino retornar repassando a informação
			if ( BFS( proximosVertices.remove(), destino, proximosVertices, caminhoInvertido ) ) {
				return true;
			}
		}
		
		return false;                                                            //Caso a fila esteja vazia retorne falso
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
	 * Procedimento auxiliar utilizado para mostrar no console todas as conexões
	 * entre vértices. Use somente em ambientes de desenvolvimento.
	 */
	public void logConexoes() {
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			
			System.out.println( "Vértice " + ( i + 1 ) + " se conecta com:" );
			
			for ( int j = 0; j < matrizDeAdjacencia.length; j++ ) {
				if ( matrizDeAdjacencia[i][j] == 1 ) {
					System.out.print( ( j + 1 ) + " " );
				}
			}
			
			System.out.println( "\n" );
		}
	}
	
	/**
	 * Verificar os vértices adjacentes a um nó selecionado
	 *
	 * @param vertice O vértice a ser selecionado
	 * @return Uma string contendo os vértices adjacentes ao vértice selecionado
	 */
	public String logConexoes( int vertice ) {
		String conexoes = "";
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( matrizDeAdjacencia[vertice][i] == 1 ) {
				conexoes += ( i + 1 ) + ",";
			}
		}
		
		conexoes = conexoes.substring( 0, conexoes.length() > 1 ? conexoes.length() - 1 : conexoes.length() );
		return conexoes;
	}
	
	/**
	 * Retorna uma lista contendo os vértices adjacentes ao vértice indicado
	 *
	 * @param vertice Vértice do qual se deseja obter os vizinhos
	 * @return Lista contendo os vértices vizinhos ao vértice selecionado
	 * @since 1.1
	 */
	public List<Integer> conexoes( int vertice ) {
		List<Integer> verticesConectados = new LinkedList<>();
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( matrizDeAdjacencia[vertice][i] == 1 ) {
				verticesConectados.add( i );
			}
		}
		
		return verticesConectados;
	}
	
	private class GrafoPonderado{
	
	}
}
