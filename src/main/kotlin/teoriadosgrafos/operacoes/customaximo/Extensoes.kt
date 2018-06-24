package teoriadosgrafos.operacoes.customaximo

import teoriadosgrafos.Grafo

fun Grafo.CustoMaximo.custoMaximo() = CustoMaximo.calcular(grafo)
fun Grafo.CustoMaximo.caminhoMaximo() = CaminhoMaximo.calcular(grafo)