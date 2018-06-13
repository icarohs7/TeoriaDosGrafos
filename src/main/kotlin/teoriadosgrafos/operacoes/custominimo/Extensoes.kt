package teoriadosgrafos.operacoes.custominimo

import teoriadosgrafos.GrafoPonderado

/**
 * [BellmanFord] - [BellmanFord.buscar]
 * @receiver [GrafoPonderado.CustoMinimo]
 * @param origem [Int]
 * @return [ResultadoPonderado]
 */
fun GrafoPonderado.CustoMinimo.bellmanFord(origem: Int) = BellmanFord.buscar(origem, grafo)

/**
 * [Dijkstra] - [Dijkstra.buscar]
 * @receiver [GrafoPonderado.CustoMinimo]
 * @param origem [Int]
 * @return [ResultadoPonderado]
 */
fun GrafoPonderado.CustoMinimo.dijkstra(origem: Int) = Dijkstra.buscar(origem, grafo)

/**
 * [FloydWarshall] - [FloydWarshall.buscar]
 * @receiver [GrafoPonderado.CustoMinimo]
 * @return [ResultadoWarshall]
 */
fun GrafoPonderado.CustoMinimo.floydWarshall() = FloydWarshall.buscar(grafo)