package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.Aresta
import com.github.icarohs7.unoxgraph.GrafoPonderado
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxkcommons.extensoes.expandido
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ArvoreCustoMinimoTest : StringSpec() {
	private lateinit var original: GrafoPonderado
	private lateinit var minimo: DoubleArray
	
	init {
		"Deve calcular a árvore geradora mínima utilizando o algoritmo de Kruskal" {
			val kruskal = original.arvoreGeradora.kruskal()
			kruskal.tree.expandido() shouldBe minimo
			
			val prim = original.arvoreGeradora.prim()
			prim.tree.expandido() shouldBe minimo
			
			kruskal shouldBe prim
		}
	}
	
	override fun beforeTest(description: Description) {
		original = GrafoPonderado.ofASize(7).also { grafo ->
			//a,b,c,d,e,f,g
			//0,1,2,3,4,5,6
			grafo += Aresta(0, 1, 7.0)  /* A */
			grafo += Aresta(0, 3, 5.0)
			
			grafo += Aresta(1, 2, 8.0)  /* B */
			grafo += Aresta(1, 3, 9.0)
			grafo += Aresta(1, 4, 7.0)
			
			grafo += Aresta(2, 4, 5.0)  /* C */
			
			grafo += Aresta(3, 4, 15.0) /* D */
			grafo += Aresta(3, 5, 6.0)
			
			grafo += Aresta(4, 5, 8.0)  /* E */
			grafo += Aresta(4, 6, 9.0)
			
			grafo += Aresta(5, 6, 11.0) /* F */
		}
		
		minimo = GrafoPonderado.ofASize(7).also { grafo ->
			grafo += Aresta(0, 1, 7.0)
			grafo += Aresta(0, 3, 5.0)
			
			grafo += Aresta(1, 4, 7.0)
			
			grafo += Aresta(3, 5, 6.0)
			
			grafo += Aresta(4, 2, 5.0)
			grafo += Aresta(4, 6, 9.0)
		}.matrizAdjacencia.expandido()
	}
}