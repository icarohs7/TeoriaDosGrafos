package teoriadosgrafos.operacoes.customaximo

import teoriadosgrafos.Grafo

/**
 * [CustoMaximo] - [CustoMaximo.calcular]
 * @receiver Grafo.CustoMaximo
 */
fun Grafo.CustoMaximo.custoMaximo() = CustoMaximo.calcular(grafo)

/**
 * [CaminhoMaximo] - [CaminhoMaximo.calcular]
 * @receiver Grafo.CustoMaximo
 * @return List<Int>
 */
fun Grafo.CustoMaximo.caminhoMaximo() = CaminhoMaximo.calcular(grafo)