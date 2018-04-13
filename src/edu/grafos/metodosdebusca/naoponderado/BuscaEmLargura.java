package edu.grafos.metodosdebusca.naoponderado;

import java.util.LinkedList;
import java.util.Stack;

import edu.grafos.GrafoNaoPonderado;
import edu.grafos.excecoes.CaminhoNaoEncontradoException;

public abstract class BuscaEmLargura {
	/**
	 * Executa uma busca em largura no grafo
	 *
	 * @param origem  O nó de origem da busca
	 * @param destino O nó de destino da busca
	 * @param grafo   O grafo no qual a busca será executada
	 *
	 * @return A fila contendo o caminho percorrido
	 *
	 * @throws CaminhoNaoEncontradoException caso o destino não seja encontrado
	 */
	public static LinkedList buscar( int origem, int destino, GrafoNaoPonderado grafo ) throws CaminhoNaoEncontradoException {
		//Utilizar a numeração utilizada pela máquina na numeração de vetores 0..X
		origem--;
		destino--;
		
		//Resetar o vetor de vértices visitados
		grafo.resetVisitados();
		//Marcar o primeiro vértice visitado
		grafo.visitados[origem] = true;
		
		//Instanciar fila e pilha usadas no processo de busca
		LinkedList<Integer> caminho = new LinkedList<>();
		Stack<Integer> caminhoInvertido = new Stack<>();
		
		//Lançar exceção caso o destino não seja encontrado
		if ( !BFS( origem, destino, grafo, caminho, caminhoInvertido ) ) {
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
	 * Procedimento recursivo responsável pela busca em largura
	 *
	 * @param origem           O nó a partir do qual a busca será iniciada
	 * @param destino          O nó que se deseja encontrar partindo da origem
	 * @param grafo            O grafo no qual a busca será executada
	 * @param proximosVertices Fila utilizada para armazenar os próximos
	 *                         vértices a serem visitados
	 * @param caminhoInvertido Pilha utilizada para armazenar o caminho
	 *
	 * @return Verdadeiro se o caminho for encontrado e Falso caso contrário
	 */
	private static boolean BFS( int origem, int destino, GrafoNaoPonderado grafo, LinkedList<Integer> proximosVertices, Stack<Integer> caminhoInvertido ) {
		//Empilhar vértice visitado
		caminhoInvertido.push( origem + 1 );
		
		//Retornar caso o destino seja alcançado
		if ( origem == destino ) {
			return true;
		}
		
		//Laço utilizado para enfileirar os visinhos do vértice atual
		for ( int i = 0; i < grafo.getMatrizDeAdjacencia().length; i++ ) {
			if ( grafo.getMatrizDeAdjacencia()[origem][i] == 1 ) {
				if ( !grafo.visitados[i] ) {
					grafo.visitados[i] = true;
					proximosVertices.add( i );
				}
			}
		}
		
		//Caso a chamada recursiva no nivel inferior tenha encontrado o destino retornar repassando a informação
		if ( !proximosVertices.isEmpty() ) {
			return BFS( proximosVertices.remove(), destino, grafo, proximosVertices, caminhoInvertido );
		}
		
		//Caso a fila esteja vazia retorne falso
		return false;
	}
}
