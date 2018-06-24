package teoriadosgrafos.operacoes.metodosdebusca

import teoriadosgrafos.GrafoNaoPonderado

fun GrafoNaoPonderado.MetodosDeBusca.buscaEmLargura(origem: Int, destino: Int) = BuscaEmLargura.buscar(origem, destino, grafo)
fun GrafoNaoPonderado.MetodosDeBusca.buscaEmProfundidade(origem: Int, destino: Int) = BuscaEmProfundidade.buscar(origem, destino, grafo)