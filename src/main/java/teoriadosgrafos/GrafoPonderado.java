package teoriadosgrafos;

import teoriadosgrafos.excecoes.ForaDoGrafoException;

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class GrafoPonderado extends Grafo {
	/**
	 * Tabela de distâncias que guarda a menor distância da origem ao vértice v
	 */
	public int[] distancia;
	
	/**
	 * Tabela de predecessores do vértice v, onde o valor de cada elemento p[v]
	 * é o vértice que antecede v no menor caminho de s a v
	 */
	public int[] precedentes;
	
	/**
	 * Construir o grafo ponderado utilizando o valor não direcionado como padrão
	 *
	 * @param matrizDeAdjacencia A matriz representando o grafo
	 */
	@SuppressWarnings( "unused" )
	public GrafoPonderado( int[][] matrizDeAdjacencia ) {
		this( matrizDeAdjacencia, false );
	}
	
	/**
	 * Construir o grafo ponderado
	 *
	 * @param matrizDeAdjacencia Matriz que gerará o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	public GrafoPonderado( int[][] matrizDeAdjacencia, boolean direcionado ) {
		super( matrizDeAdjacencia, direcionado );
		distancia = new int[matrizDeAdjacencia.length];
		precedentes = new int[matrizDeAdjacencia.length];
	}
	
	/**
	 * Construir um grafo ponderado vazio
	 *
	 * @param tamanho     A quantidade de vértices contidas no grafo
	 * @param direcionado Se o grafo é ou não direcionado
	 */
	public GrafoPonderado( int tamanho, boolean direcionado ) {
		this( new int[tamanho][tamanho], direcionado );
		resetMatrizDeAdjacencia();
	}
	
	/**
	 * Reset matriz de adjacencia.
	 */
	private void resetMatrizDeAdjacencia() {
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			for ( int j = 0; j < matrizDeAdjacencia[0].length; j++ ) {
				/* Definir todos os pesos da matriz de adjacência para infinitos */
				matrizDeAdjacencia[i][j] = INFINITO;
			}
		}
	}
	
	
	/**
	 * Inicializar o grafo
	 */
	@Override
	public void inicializar() {
		//Inicializar todas as distâncias como infinitas, com exceção da distância s até s, que será 0
		resetDistancias();
		
		//Inicializar todos os precedentes como -1 para representar sem precedente
		resetPrecedentes();
		
		//Marcar todos os vértices como abertos ou seja, não visitados
		resetVisitados();
		
		//Fazer todas as distâncias do vértice até ele mesmo sendo 0
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			matrizDeAdjacencia[i][i] = 0;
		}
	}
	
	/**
	 * Inicializa ou Reinicializa o array de distâncias
	 */
	private void resetDistancias() {
		for ( int i = 0; i < distancia.length; i++ ) {
			distancia[i] = Grafo.INFINITO;
		}
	}
	
	/**
	 * Inicializa ou Reinicializa o array de predecessores com o valor desejado
	 */
	private void resetPrecedentes() {
		for ( int i = 0; i < precedentes.length; i++ ) {
			precedentes[i] = -1;
		}
	}
	
	/**
	 * Retorna o vértice contendo a menor distância até uma dada origem
	 *
	 * @return O índice do vértice aberto mais próximo à origem ou infinito caso não haja vértice aberto
	 */
	public int menorDistancia() {
		int menor = INFINITO;
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( !visitados[i] ) {
				if ( distancia[i] < menor ) {
					/* Passando por todos os vértices, se este
					 * estiver aberto e sua distância for menor que a distância atual,
					 * defini-la para este valor*/
					menor = i;
				}
			}
		}
		
		return menor;
	}
	
	/**
	 * Adicionar uma aresta ao grafo
	 *
	 * @param origem  Vértice de origem da aresta
	 * @param destino Vértice de destino da aresta
	 * @param peso    Peso da aresta
	 *
	 * @throws ForaDoGrafoException the fora do grafo exception
	 */
	public void addAresta( int origem, int destino, int peso ) throws ForaDoGrafoException {
		origem--;
		destino--;
		try {
			matrizDeAdjacencia[origem][destino] = peso;
			if ( !direcionado ) {
				matrizDeAdjacencia[destino][origem] = peso;
			}
		} catch ( IndexOutOfBoundsException e ) {
			throw new ForaDoGrafoException( "Tentou acessar uma aresta fora do grafo: " + e.getMessage() );
		}
	}
}
