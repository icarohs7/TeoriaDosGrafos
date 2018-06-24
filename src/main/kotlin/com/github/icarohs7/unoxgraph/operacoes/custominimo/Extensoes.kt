package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxgraph.GrafoPonderado

fun GrafoPonderado.CustoMinimo.bellmanFord(origem: Int) = BellmanFord.buscar(origem, grafo)
fun GrafoPonderado.CustoMinimo.dijkstra(origem: Int) = Dijkstra.buscar(origem, grafo)
fun GrafoPonderado.CustoMinimo.floydWarshall() = FloydWarshall.buscar(grafo)