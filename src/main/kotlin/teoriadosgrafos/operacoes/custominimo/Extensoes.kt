package teoriadosgrafos.operacoes.custominimo

import teoriadosgrafos.GrafoPonderado

fun GrafoPonderado.CustoMinimo.bellmanFord(origem: Int) = BellmanFord.buscar(origem, grafo)
fun GrafoPonderado.CustoMinimo.dijkstra(origem: Int) = Dijkstra.buscar(origem, grafo)
fun GrafoPonderado.CustoMinimo.floydWarshall() = FloydWarshall.buscar(grafo)