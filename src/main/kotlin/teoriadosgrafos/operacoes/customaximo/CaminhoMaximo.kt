package teoriadosgrafos.operacoes.customaximo

import teoriadosgrafos.Grafo
import teoriadosgrafos.extensoes.getEntradas
import teoriadosgrafos.operacoes.ordemtopologica.khan

/**
 * Calcular o caminho mais longo em um grafo
 */
object CaminhoMaximo {
	@Suppress("LocalVariableName")
	fun calcular(grafo: Grafo): List<Int> {
		// Ordenar topológicamente
		val V = grafo.ordemTopologica.khan()
		
		// Função recursiva para cálculo da maior distância de um vértice*/
		fun dist(v: Int): List<Int> {
			return if (v.getEntradas(grafo).isEmpty()) {
				mutableListOf(v)
			} else {
				return mutableListOf(v) +
						v.getEntradas(grafo)
							.fold<Int, List<Int>>(emptyList()) { acc, i ->
								val a = dist(i)
								if (a.size > acc.size) a else acc
							}
			}
		}
		
		// Gerar lista de caminho máximo
		val res: List<Int> = V.fold(emptyList()) { acc, i ->
			val a = dist(i)
			if (a.size > acc.size) a else acc
		}
		
		// Retornar a lista contento o caminho máximo corrigida
		return res.reversed().dropLast(1)
	}
}