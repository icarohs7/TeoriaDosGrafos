package teoriadosgrafos.operacoes.metodosdebusca

import teoriadosgrafos.GrafoNaoPonderado

/**
 * [BuscaEmLargura] - [BuscaEmLargura.buscar]
 * @receiver [GrafoNaoPonderado.MetodosDeBusca]
 * @param origem [Int]
 * @param destino [Int]
 * @return caminho - [List]
 */
fun GrafoNaoPonderado.MetodosDeBusca.buscaEmLargura(origem: Int, destino: Int) = BuscaEmLargura.buscar(origem, destino, grafo)

/**
 * [BuscaEmProfundidade] - [BuscaEmProfundidade.buscar]
 * @receiver [GrafoNaoPonderado.MetodosDeBusca]
 * @param origem [Int]
 * @param destino [Int]
 * @return caminho - [List]
 */
fun GrafoNaoPonderado.MetodosDeBusca.buscaEmProfundidade(origem: Int, destino: Int) = BuscaEmProfundidade.buscar(origem, destino, grafo)