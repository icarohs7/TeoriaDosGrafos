package com.github.icarohs7.unoxgraph.operacoes.customaximo

import com.github.icarohs7.unoxgraph.Grafo

fun Grafo.CustoMaximo.custoMaximo() = CustoMaximo.calcular(grafo)
fun Grafo.CustoMaximo.caminhoMaximo() = CaminhoMaximo.calcular(grafo)