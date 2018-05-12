package teoriadosgrafos.extensoes

/**
 * Função de extensão da classe Array de IntArray(Matriz de inteiros)
 * utilizada para converter uma Matriz de Inteiros para uma Matriz de Doubles
 * @receiver Array<IntArray>: Matriz receptora
 * @return Array<DoubleArray>: Matriz de Doubles
 */
fun Array<IntArray>.toDouble2DArray() = map { it.toDoubleArray() }.toTypedArray()
