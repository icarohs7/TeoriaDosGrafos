package teoriadosgrafos.extensoes

import teoriadosgrafos.Grafo

/**
 * Substitui o valor infinito em uma matriz pelo símbolo infinito
 * @receiver Array<Array<String>>: Matriz receptora
 * @return Array<Array<String>>>: Matriz formatada
 */
fun Array<Array<String>>.replaceInfinity(): Array<Array<String>> {
	/* String que armazenará a matriz com o símbolo já substituído */
	val novaString = Array(size) { Array(this[0].size) { "" } }
	
	indices.forEach { i ->
		this[0].indices.forEach { j ->
			if (this[i][j] == Grafo.INFINITO.toString()) {
				/* Substituir os valores infinitos pelo símbolo */
				novaString[i][j] = Character.toString('\u221e')
			} else {
				/* Nos outros elementos, manter os mesmos */
				novaString[i][j] = this[i][j]
			}
		}
	}
	/* Retornar a nova string */
	return novaString
}