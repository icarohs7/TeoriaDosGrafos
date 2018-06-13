package teoriadosgrafos.atividades.etapa2trabalho2

import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.extensoes.toDouble2DArray
import teoriadosgrafos.operacoes.arvoregeradora.MST
import teoriadosgrafos.operacoes.arvoregeradora.kruskal

/**
 * Existem 8 pequenas ilhas em um arquipélago
 * e o governo deseja construir 7 pontes
 * conectando-as de forma que cada ilha possa
 * ser alcançada de qualquer outra ilha através
 * de uma ou mais pontes.
 * O custo de construção de uma ponte é
 * proporcional ao seu comprimento.
 * As distâncias entre os pares de ilhas são
 * dados na tabela abaixo.
 * Ache quais pontes devem ser construídas
 * para que o custo da construção seja mínimo
 */
object Questao2 {
	val arvoreMinima: MST
		get() {
			@Suppress("LocalVariableName")
			val NA = Grafo.INFINITO.toInt()
			
			/* Matriz do grafo */
			val matriz = arrayOf(
				/* 1 */intArrayOf(NA, 240, 210, 340, 280, 200, 345, 120),
				/* 2 */intArrayOf(NA, NA, 265, 175, 215, 180, 185, 155),
				/* 3 */intArrayOf(NA, NA, NA, 260, 115, 350, 435, 195),
				/* 4 */intArrayOf(NA, NA, NA, NA, 160, 330, 295, 230),
				/* 5 */intArrayOf(NA, NA, NA, NA, NA, 360, 400, 170),
				/* 6 */intArrayOf(NA, NA, NA, NA, NA, NA, 175, 205),
				/* 7 */intArrayOf(NA, NA, NA, NA, NA, NA, NA, 305),
				/* 8 */intArrayOf(NA, NA, NA, NA, NA, NA, NA, NA)
			).toDouble2DArray()
			
			return GrafoPonderado(matriz, true).arvoreGeradora.kruskal()
		}
}