package teoriadosgrafos.operacoes.arvoregeradora

import teoriadosgrafos.GrafoPonderado

fun GrafoPonderado.ArvoreGeradora.kruskal() = Kruskal.gerar(grafo)
fun GrafoPonderado.ArvoreGeradora.prim() = Prim.gerar(grafo)