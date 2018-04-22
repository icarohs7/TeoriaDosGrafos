package teoriadosgrafos.metodosdebusca.naoponderado;

import java.util.LinkedList;
import java.util.Stack;

import teoriadosgrafos.Grafo;
import teoriadosgrafos.excecoes.CaminhoNaoEncontradoException;

public abstract class BuscaEmProfundidade {
	
	/**
	 * Executa uma busca em profundidade no grafo
	 *
	 * @param origem  O nó de origem da busca
	 * @param destino O nó de destino da busca
	 * @param grafo   O grafo onde será executada a busca
	 *
	 * @return A fila contendo o caminho percorrido
	 *
	 * @throws CaminhoNaoEncontradoException caso o destino não seja encontrado
	 */
	public static LinkedList buscar( int origem, int destino, Grafo grafo ) throws CaminhoNaoEncontradoException {
		//Utilizar a numeração utilizada pela máquina na numeração de vetores 0..X
		origem--;
		destino--;
		
		//Desmarcar todos os nós visitados
		grafo.resetVisitados();
		
		//Instanciar a fila e pilha utilizadas no processo de busca
		LinkedList<Integer> caminho = new LinkedList<>();
		Stack<Integer> caminhoInvertido = new Stack<>();
		
		//Lançar uma exceção caso o destino não seja encontrado
		if ( !DFS( origem, destino, grafo, caminhoInvertido ) ) {
			throw new CaminhoNaoEncontradoException( "Destino não encontrado." );
		}
		
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
	 */
	private static boolean DFS( int origem, int destino, Grafo grafo, Stack<Integer> caminhoInvertido ) {
		//Marcar o vértice atual
		grafo.visitados[origem] = true;
		
		//adicioná-lo à pilha
		caminhoInvertido.push( origem + 1 );
		
		//e retornar caso o destino tenha sido alcançado
		if ( origem == destino ) {
			return true;
		}
		
		for ( int i = 0; i < grafo.getMatrizDeAdjacencia().length; i++ ) {
			//Para cada vértice adjacente
			if ( grafo.getMatrizDeAdjacencia()[origem][i] == 1 ) {
				//e não visitado
				if ( !grafo.visitados[i] ) {
					//descer um nível na busca
					if ( DFS( i, destino, grafo, caminhoInvertido ) ) {
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
}
