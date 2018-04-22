package teoriadosgrafos;

import java.io.PrintStream;
import java.util.LinkedList;

/**
 * Classe representando um grafo e suas operações
 *
 * @author Icaro D. Temponi
 */
public abstract class Grafo {
	/**
	 * Valor representanto infinito, sua função pode variar de acordo com o grafo e algoritmo usado
	 */
	public static final int INFINITO = Integer.MAX_VALUE / 2;
	/**
	 * Tabela de vértices que foram visitados, ou vértices abertos
	 */
	public boolean[] visitados;
	
	/**
	 * Matriz de adjacência utilizada para construir o grafo
	 */
	int[][] matrizDeAdjacencia;
	
	/**
	 * Se o grafo é ou não direcionado
	 */
	boolean direcionado;
	
	
	/**
	 * Modelo padrão para construção de um grafo
	 *
	 * @param matrizDeAdjacencia Matriz que compõe o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	protected Grafo( int[][] matrizDeAdjacencia, boolean direcionado ) {
		this.matrizDeAdjacencia = matrizDeAdjacencia;
		this.direcionado = direcionado;
	}
	
	
	/**
	 * Retorna a matriz de adjacência do grafo instanciado
	 *
	 * @return um array bidimensional de valores inteiros referentes à 		adjacencia vértice x vértice
	 */
	public int[][] getMatrizDeAdjacencia() {
		return matrizDeAdjacencia;
	}
	
	/**
	 * Get matriz de adjacencia as string string [ ] [ ].
	 *
	 * @return the string [ ] [ ]
	 */
	public String[][] getMatrizDeAdjacenciaAsString() {
		/* Matriz que conterá a conversão da matriz de adjacência para uma matriz de strings */
		String[][] matrizDeAdjacenciaString = new String[matrizDeAdjacencia.length][matrizDeAdjacencia[0].length];
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			for ( int j = 0; j < matrizDeAdjacencia[0].length; j++ ) {
				/* Converter cada elemento da matriz de adjacência para String */
				matrizDeAdjacenciaString[i][j] = Integer.toString( matrizDeAdjacencia[i][j] );
			}
		}
		/* Retornar o resultado */
		return matrizDeAdjacenciaString;
	}
	
	/**
	 * Inicializar o grafo
	 */
	public void inicializar() {
		//Marcar todos os vértices como abertos ou seja, não visitados
		resetVisitados();
	}
	
	/**
	 * Desmarcar todos os vértices visitados
	 */
	public void resetVisitados() {
		visitados = new boolean[matrizDeAdjacencia.length];
	}
	
	/**
	 * Retorna a lista de arestas contidas no grafo utilizando o valor
	 * infinito para definir a não adjacência de vértices
	 *
	 * @return A lista contendo todas as arestas adjacentes no grafo
	 */
	public LinkedList<Aresta> getArestas() {
		/* Criar lista de arestas */
		LinkedList<Aresta> arestas = new LinkedList<>();
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			for ( int j = 0; j < matrizDeAdjacencia[0].length; j++ ) {
				if ( matrizDeAdjacencia[i][j] != INFINITO ) {
					Aresta novaAresta = new Aresta( i, j, matrizDeAdjacencia[i][j] );
					/* Para cada elemento da matriz, se o mesmo for uma aresta válida e o grafo for direcionado,
					 * adicioná-lo à lista de arestas */
					if ( direcionado ) {
						arestas.add( novaAresta );
					} else {
						/* Já se o grafo for não direcionado, somente se a aresta já não estiver presente no conjunto
						 * a mesma será adicionada */
						if ( !arestas.contains( novaAresta ) ) {
							arestas.add( novaAresta );
						}
					}
				}
			}
		}
		return arestas;
	}
	
	/**
	 * Retorna verdadeiro se ainda existe algum elemento aberto no grafo
	 *
	 * @return True se existir algum elemento não visitado ou false do contrário
	 */
	public boolean existeAberto() {
		for ( boolean vertice : visitados ) {
			if ( !vertice ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna uma lista contendo os vizinhos abertos do vértice informado
	 *
	 * @param vertice       O vértice do qual se deseja obter os vizinhos
	 * @param naoAdjacencia O valor utilizado para marcar um vértice como não adjacente a outro
	 *
	 * @return Uma lista contendo os vizinhos abertos do vértice informado
	 */
	public LinkedList<Integer> getVizinhos( int vertice, int naoAdjacencia ) {
		LinkedList<Integer> vizinhos = new LinkedList<>();
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( matrizDeAdjacencia[vertice][i] != naoAdjacencia ) {
				vizinhos.add( i );
			}
		}
		return vizinhos;
	}
	
	/**
	 * Print matriz.
	 *
	 * @param out the out
	 */
	public void printMatriz( PrintStream out ) {
		for ( int u = 0; u < matrizDeAdjacencia.length; u++ ) {
			for ( int v = 0; v < matrizDeAdjacencia[0].length; v++ ) {
				/* Para cada aresta, imprimi-la, mesmo as não existentes */
				out.print( matrizDeAdjacencia[u][v] + " " );
			}
			/* Ao fim de cada linha da matriz, quebrar a linha na impressão */
			out.println();
		}
	}
	
	/**
	 * Print arestas.
	 *
	 * @param out the out
	 */
	public void printArestas( PrintStream out ) {
		for ( int u = 0; u < matrizDeAdjacencia.length; u++ ) {
			for ( int v = 0; v < matrizDeAdjacencia[0].length; v++ ) {
				if ( matrizDeAdjacencia[u][v] != INFINITO ) {
					/* Para cada aresta, imprimi-la e quebrar a linha */
					out.println( new Aresta( u, v, matrizDeAdjacencia[u][v] ) );
				}
			}
		}
	}
}
