package teoriadosgrafos.extensoes

/**
 * Função de extensão da classe IntArray utilizada
 * para converter um Array de Inteiros para um Array de Doubles
 * @receiver IntArray: Array receptor
 * @return DoubleArray: Array de Doubles
 */
fun IntArray.toDoubleArray() = map { it.toDouble() }.toDoubleArray()