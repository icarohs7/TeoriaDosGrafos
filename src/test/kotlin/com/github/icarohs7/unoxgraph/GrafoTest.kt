package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.extensoes.expandido
import com.github.icarohs7.unoxcommons.extensoes.para
import com.github.icarohs7.unoxcommons.extensoes.por
import com.github.icarohs7.unoxcommons.extensoes.preenchendoMatrizDoubleDeTamanho
import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GrafoTest : StringSpec() {
	private lateinit var g: Grafo   //Grafo1
	private lateinit var g2: Grafo  //Grafo2
	private lateinit var gnd: Grafo //Grafo não direcionado
	private lateinit var m: MatrizDouble    //Matriz1
	private lateinit var mnd: MatrizDouble  //Matriz não direcionada
	
	init {
		"Deve conter uma lista de arestas correspondentes à matriz de adjacência" {
			g.matrizDeAdjacencia.expandido() shouldBe m.expandido()
			gnd.matrizDeAdjacencia.expandido() shouldBe mnd.expandido()
		}
		
		"Deve verificar se outro grafo é igual" {
			g shouldBe g2
		}
		
		"Deve excluir uma aresta" {
			val g3 = GrafoNaoPonderado(3)
			g3.addAresta(0, 1)
			g3.addAresta(1, 2)
			g2.excluirAresta(2, 0)
			
			g2 shouldBe g3
		}
		
		"Deve calcular corretamente sua lista de vértices" {
			g.vertices shouldBe listOf(0, 1, 2)
		}
		
		"Deve ser construído de forma idiomática" {
			val gi = GrafoNaoPonderado(0, 1, 2, 0, direcionado = false)
			gi shouldBe g
			
			val gndi = GrafoNaoPonderado(0, 1, 2, 0)
			gndi shouldBe gnd
		}
	}
	
	override fun beforeTest(description: Description) {
		g = GrafoNaoPonderado(3)
		g += 0 para 1
		g += 1 para 2
		g += 2 para 0
		
		g2 = GrafoNaoPonderado(3)
		g2 += 0 para 1
		g2 += 1 para 2
		g2 += 2 para 0
		
		m = INFINITO preenchendoMatrizDoubleDeTamanho (3 por 3)
		m[0][1] = 0.0
		m[1][0] = 0.0
		m[1][2] = 0.0
		m[2][1] = 0.0
		m[2][0] = 0.0
		m[0][2] = 0.0
		
		gnd = GrafoNaoPonderado(3, true)
		gnd += 0 para 1
		gnd += 1 para 2
		gnd += 2 para 0
		
		mnd = INFINITO preenchendoMatrizDoubleDeTamanho (3 por 3)
		mnd[0][1] = 0.0
		mnd[1][2] = 0.0
		mnd[2][0] = 0.0
	}
}

