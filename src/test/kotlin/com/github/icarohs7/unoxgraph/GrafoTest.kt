package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.extensoes.minusAssign
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.estatico.DoubleMatriz
import com.github.icarohs7.unoxkcommons.extensoes.cells
import com.github.icarohs7.unoxkcommons.extensoes.para
import com.github.icarohs7.unoxkcommons.extensoes.por
import com.github.icarohs7.unoxkcommons.extensoes.preenchendoMatrizDoubleDeTamanho
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GrafoTest : StringSpec() {
	private lateinit var g: Grafo   //Grafo1
	private lateinit var g2: Grafo  //Grafo2
	private lateinit var gd: Grafo //Grafo não direcionado
	private lateinit var m: DoubleMatriz    //Matriz1
	private lateinit var md: DoubleMatriz  //Matriz não direcionada
	
	init {
		"Deve conter uma lista de arestas correspondentes à matriz de adjacência" {
			g.matrizAdjacencia.cells shouldBe m.cells
			gd.matrizAdjacencia.cells shouldBe md.cells
		}
		
		"Deve verificar se outro grafo é igual" {
			g shouldBe g2
		}
		
		"Deve excluir uma aresta" {
			val g3 = Grafo.NaoPonderado.ofASize(3, direcionado = false)
			g3 += 0 para 1
			g3 += 1 para 2
			g2 -= 2 para 0
			
			g2 shouldBe g3
		}
		
		"Deve calcular corretamente sua lista de vértices" {
			g.vertices shouldBe listOf(0, 1, 2)
		}
		
		"Deve ser construído de forma idiomática, direcionado" {
			val gi = Grafo.NaoPonderado.withThePath(0, 1, 2, 0, direcionado = false)
			gi shouldBe g
		}
		
		"Deve ser construído de forma idiomática, não direcionado" {
			val gdi = Grafo.NaoPonderado.withThePath(0, 1, 2, 0, direcionado = true)
			gdi shouldBe gd
		}
	}
	
	override fun beforeTest(description: Description) {
		g = Grafo.NaoPonderado.ofASize(3, direcionado = false)
		g += 0 para 1
		g += 1 para 2
		g += 2 para 0
		
		g2 = Grafo.NaoPonderado.ofASize(3, direcionado = false)
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
		
		gd = Grafo.NaoPonderado.ofASize(3, direcionado = true)
		gd += 0 para 1
		gd += 1 para 2
		gd += 2 para 0
		
		md = INFINITO preenchendoMatrizDoubleDeTamanho (3 por 3)
		md[0][1] = 0.0
		md[1][2] = 0.0
		md[2][0] = 0.0
	}
}

