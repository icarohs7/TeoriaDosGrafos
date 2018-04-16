package edu.grafos.metodosdebusca.ponderado

import edu.grafos.GrafoPonderado

/**
 * Classe representando o método de busca de Dijkstra
 */
abstract class Dijkstra {
	//Métodos estáticos
	companion object {
		/**
		 * Realiza a busca de menor caminho em um grafo utilizando o algoritmo de Dijkstra
		 * @param origem Int -- O vértice origem da busca
		 * @param grafo GrafoPonderado -- O grafo sobre o qual a busca será executada
		 * @return ResultadoDijkstra -- Um objeto contendo o array de menores distâncias e o array de predecessores
		 */
		fun buscar(origem: Int, grafo: GrafoPonderado): ResultadoDijkstra {
			//Inicializar o grafo
			inicializar(origem, grafo)
			
			//Array de distâncias
			val d = grafo.distancia
			
			//Array de predecessores
			val p = grafo.precedentes
			
			//Array de visitados (ou abertos)
			val visitados = grafo.visitados
			
			//Iterar por todos os vértices abertos
			while (existeAberto(grafo)) {
				//Armazenar o vértice aberto contendo a menor distância no grafo
				val u = menorDistancia(grafo)
				
				//Fechar o vértice u
				visitados[u] = true
				
				//Relaxar todos os vértices abertos adjacentes a u
				for (v in 0 until d.size) {
					relaxar(grafo, u, v)
				}
			}
			
			//Retornar o conjunto contendo o array de distâncias mínimas e o array de predecessores
			return ResultadoDijkstra(d, p)
		}
		
		/**
		 * Fazer o relaxamento da aresta E(u,v)
		 * @param grafo GrafoPonderado -- O grafo em que a busca está sendo executada
		 * @param u Int -- O vértice de onde a aresta partirá
		 * @param v Int -- O vértice destino da aresta
		 */
		private fun relaxar(grafo: GrafoPonderado, u: Int, v: Int) {
			//Se o vértice destino estiver aberto,
			if (!grafo.visitados[v]) {
				//For adjacente ao vértice u,
				if (grafo.matrizDeAdjacencia[u][v] > -1) {
					//O vértice u possuir uma distância definida,
					if (grafo.distancia[u] != grafo.INFINITO) {
						//E V possuir uma distância maior à origem que U mais a aresta que liga U a V
						if (grafo.distancia[v] > (grafo.matrizDeAdjacencia[u][v] + grafo.distancia[u])) {
							//Fazer a distância de V ser a distância de V à origem passando por U
							grafo.distancia[v] = grafo.distancia[u] + grafo.matrizDeAdjacencia[u][v]
							//E o precedente de V se tornar U
							grafo.precedentes[v] = u
						}
					}
				}
			}
		}
		
		/**
		 * Inicializar o grafo
		 * @param origem Int -- O vértice de origem
		 * @param grafo GrafoPonderado -- O grafo em que a busca será realizada
		 * @return Unit
		 */
		private fun inicializar(origem: Int, grafo: GrafoPonderado) {
			//Inicializar todas as distâncias como infinitas, com exceção da distância s até s, que será 0
			grafo.resetDistancias(grafo.INFINITO)
			grafo.distancia[origem] = 0
			
			//Inicializar todos os precedentes como -1 para representar sem precedente
			grafo.resetPrecedentes(-1)
			grafo.precedentes[origem] = 0
			
			//Marcar todos os vértices como abertos ou seja, não visitados
			grafo.resetVisitados()
		}
		
		/**
		 * Informa há vértice aberto no grafo
		 * @param grafo GrafoPonderado -- O grafo onde a busca será executada
		 * @return Boolean -- Verdadeiro se ainda houver vértice aberto, falso do contrário
		 */
		private fun existeAberto(grafo: GrafoPonderado): Boolean {
			/*Passa por todos os elementos no array de visitados
			 *e retorna verdadeiro no primeiro elemento aberto (não visitado),
			 *caso não encontre nenhum, retorna falso */
			grafo.visitados.forEach { visitado ->
				if (!visitado) {
					return true
				}
			}
			return false
		}
		
		/**
		 * Retorna a menor distância relacionada a um vértice aberto dentro do grafo
		 * @param grafo GrafoPonderado -- O grafo em que a busca será realizada
		 * @return Int -- O vértice aberto incidente à menor distância dentro do grafo
		 */
		private fun menorDistancia(grafo: GrafoPonderado): Int {
			//Array de distâncias do grafo
			val d = grafo.distancia
			//Array de vértices abertos (não visitados)
			val aberto = grafo.visitados.map { visitado -> !visitado }
			/*Definir um valor inicial para menor, que será substituído posteriormente pelo menor elemento
			aberto dentro do grafo*/
			var menor = Int.MAX_VALUE
			
			//De 0 ao tamanho do array de distâncias, indo até o tamanho-1
			for (i in 0 until d.size) {
				if (aberto[i] && d[i] <= menor) {
					menor = i
				}
			}
			
			return menor
		}
	}
}
