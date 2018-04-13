package edu.grafos.metodosdebusca.ponderado

import edu.grafos.GrafoPonderado

abstract class Dijkstra {
	//Métodos estáticos
	companion object {
		private const val INFINITO = Int.MAX_VALUE / 2
		/**
		 * Realiza a busca de menor caminho em um grafo utilizando o algoritmo de Dijkstra
		 * @param origem Int -- O vértice origem da busca
		 * @param grafo GrafoPonderado -- O grafo sobre o qual a busca será executada
		 * @return ResultadoDijkstra -- Um objeto contendo o array de menores distâncias e o array de predecessores
		 */
		fun buscar(origem: Int, grafo: GrafoPonderado): ResultadoDijkstra {
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
					if (!visitados[v]
					    && grafo.matrizDeAdjacencia[u][v] != -1
					    && d[u] != INFINITO
					    && (d[u] + grafo.matrizDeAdjacencia[u][v] < d[v])) {
						
						p[v] = u
						d[v] = d[u] + grafo.matrizDeAdjacencia[u][v]
					}
				}
			}
			
			//Retornar o conjunto contendo o array de distâncias mínimas e o array de predecessores
			return ResultadoDijkstra(d, p)
		}
		
		/**
		 * Inicializar o grafo
		 * @param origem Int -- O vértice de origem
		 * @param grafo GrafoPonderado -- O grafo em que a busca será realizada
		 * @return Unit
		 */
		private fun inicializar(origem: Int, grafo: GrafoPonderado) {
			//Inicializar todas as distâncias como infinitas, com exceção da distância s até s, que será 0
			grafo.resetDistancias(INFINITO)
			grafo.distancia[origem] = 0
			
			//Inicializar todos os precedentes como -1 para representar sem precedente
			grafo.resetPrecedentes(-1)
			
			//Marcar todos os vértices como abertos ou seja, não visitados
			grafo.resetVisitados()
		}
		
		
		/**
		 * Retorna um array contendo os vértices adjacentes ao vértice atual
		 * @param grafo GrafoPonderado -- O grafo no qual a busca está sendo executada
		 * @param vertice Int -- O vértice origem
		 * @return IntArray -- Um array contendo todos os vértices adjacentes ao vértice origem
		 */
		private fun adjacencia(grafo: GrafoPonderado, vertice: Int): IntArray {
			return grafo.matrizDeAdjacencia[vertice]
					.filterIndexed { _, peso -> peso > -1 }
					.mapIndexed { indice, _ -> indice }
					.toIntArray()
		}
		
		/**
		 * Informa há vértice aberto no grafo
		 * @param grafo GrafoPonderado -- O grafo onde a busca será executada
		 * @return Boolean -- Verdadeiro se ainda houver vértice aberto, falso do contrário
		 */
		private fun existeAberto(grafo: GrafoPonderado): Boolean {
			grafo.visitados.forEach { if (!it) return true }
			return false
		}
		
		/**
		 * Retorna a menor distância relacionada a um vértice aberto dentro do grafo
		 * @param grafo GrafoPonderado -- O grafo em que a busca será realizada
		 * @return Int -- O vértice aberto incidente à menor distância dentro do grafo
		 */
		private fun menorDistancia(grafo: GrafoPonderado): Int {
			val d = grafo.distancia
			val aberto = grafo.visitados.map { !it }
			var menor = Int.MAX_VALUE
			
			for (i in 0 until d.size) {
				if (aberto[i] && d[i] <= menor) {
					menor = i
				}
			}
			
			return menor
		}
	}
}
