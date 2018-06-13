package teoriadosgrafos.operacoes.arvoregeradora

import teoriadosgrafos.GrafoPonderado

/**
 * [Kruskal] - [Kruskal.gerar]
 * @receiver [GrafoPonderado.ArvoreGeradora]
 * @return arvore de custo mínimo - [MST]
 */
fun GrafoPonderado.ArvoreGeradora.kruskal() = Kruskal.gerar(grafo)

/**
 * [Prim] - [Prim.gerar]
 * @receiver [GrafoPonderado.ArvoreGeradora]
 * @return arvore de custo mínimo - [MST]
 */
fun GrafoPonderado.ArvoreGeradora.prim() = Prim.gerar(grafo)