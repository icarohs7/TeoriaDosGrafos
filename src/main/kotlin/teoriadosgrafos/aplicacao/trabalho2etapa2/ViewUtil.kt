package teoriadosgrafos.aplicacao.trabalho2etapa2

import teoriadosgrafos.Grafo
import java.awt.Font

/* Compilação das classes contendo as interfaces dos 3 enunciados */

/**
 * Classe agrupando elementos comuns
 */
internal object ViewUtil {
	/**
	 * Fonte utilizada nos títulus
	 */
	val h1: Font = Font("Consolas", Font.BOLD, 28)
	/**
	 * Fonte utilizada nos subtítulos
	 */
	val h2: Font = Font("Consolas", Font.PLAIN, 20)
	
	/**
	 * Substitui o valor infinito em uma matriz pelo símbolo infinito
	 * @param matriz the matriz
	 * @return string [ ] [ ]
	 */
	fun replaceInfinity(matriz: Array<Array<String>>): Array<Array<String>> {
		/* String que armazenará a matriz com o símbolo já substituído */
		val novaString = Array(matriz.size) { Array(matriz[0].size) { "" } }
		
		for (i in matriz.indices) {
			for (j in 0 until matriz[0].size) {
				if (matriz[i][j] == Integer.toString(Grafo.INFINITO)) {
					/* Substituir os valores infinitos pelo símbolo */
					novaString[i][j] = Character.toString('\u221e')
				} else {
					/* Nos outros elementos, manter os mesmos */
					novaString[i][j] = matriz[i][j]
				}
			}
		}
		/* Retornar a nova string */
		return novaString
	}
}





