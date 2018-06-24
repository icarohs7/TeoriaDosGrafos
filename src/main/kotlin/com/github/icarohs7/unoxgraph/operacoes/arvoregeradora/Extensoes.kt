package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.GrafoPonderado

fun GrafoPonderado.ArvoreGeradora.kruskal() = Kruskal.gerar(grafo)
fun GrafoPonderado.ArvoreGeradora.prim() = Prim.gerar(grafo)