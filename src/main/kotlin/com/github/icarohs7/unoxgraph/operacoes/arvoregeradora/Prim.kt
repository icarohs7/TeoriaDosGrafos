package com.github.icarohs7.unoxgraph.operacoes.arvoregeradora

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.estatico.MstArestasInsuficientesException
import com.github.icarohs7.unoxgraph.estatico.TipoDeGrafoIncorretoException
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta

/**
 * Injetar a função de calculo de prim na classe grafo
 */
fun Grafo.mstPrim(): MST {
	return Prim.gerar(this as? Grafo.Ponderado ?: throw TipoDeGrafoIncorretoException())
}

private object Prim {
	
	fun gerar(grafo: Grafo.Ponderado): MST {
		/* Inicializar o grafo */
		grafo.desmarcarTodosOsVertices()
		
		/* Quantidade de vértices */
		val tamanho = grafo.matrizAdjacencia.size
		
		/* Criar MST que conterá o resultado, com suas arestas representando T */
		val mst = MST(tamanho)
		
		/* Array contendo os vértices visitados, representando V */
		val visitados = grafo.visitados
		
		/* Lista que irá controlar a iteração, desmarcarTodosOsVertices com o primeiro vértice do grafo,
		 * representando B */
		val b = mutableListOf(0)
		
		/* Marcar o primeiro vértice*/
		visitados[0] = true
		
		/* Laço que gerará a árvore:
		 * Que durará enquanto B não conter todos os vértices */
		while (b.size < tamanho) {
			/* (u,v) = aresta de menor peso tal que u pertence a V-B e v pertence a B,
			 * V-B Representa pertencer a V sem pertencer a B */
			val menorAresta = menorPeso(grafo, b)
			
			/* Tratamento de erros */
			if (menorAresta.peso == INFINITO) throw MstArestasInsuficientesException()
			
			/* T = T união {(u,v)} */
			mst.addAresta(menorAresta, grafo.direcionado)
			
			/* B = B união {u} */
			b.add(menorAresta.origem)
			
			/* Retirar u de V */
			visitados[menorAresta.origem] = true
		}
		/* Retornar o resultado */
		return mst
	}
	
	/**
	 * Retorna a aresta de menor peso tal que sua origem pertence ao conjunto de vértices abertos
	 * sem pertencer ao conjunto de vértices adicionados pelo algoritmo de Prim e seu destino pertence
	 * aos vértices adicionados
	 */
	private fun menorPeso(grafo: Grafo.Ponderado, conjuntoVertices: MutableList<Int>): Aresta {
		var menor = Aresta(0, 0, INFINITO)
		/* O array de vértices abertos */
		val visitados = grafo.visitados
		/* A matriz de pesos */
		val w = grafo.matrizAdjacencia
		
		/* Para cada aresta concreta ou não dentro do grafo,
		 * Com V representando os Vértices abertos
		 * E B representando a lista de vértices já conectados à árvore*/
		visitados.indices.forEach { u ->
			visitados.indices.forEach { v ->
				/* Para cada aresta, pegar a que tem menor peso que a menor registrada */
				if (w[u][v] < menor.peso) {
					/* Verificar se a origem desta aresta está contida em V e não contida em B */
					if (!visitados[u] && !conjuntoVertices.contains(u)) {
						/* E Verificar se o destino desta aresta está contivo em B */
						if (conjuntoVertices.contains(v)) {
							/* Caso as 3 asserções anteriores sejam verdadeiras,
							 * fazer desta aresta a menor até o momento */
							menor = Aresta(u, v, w[u][v])
						}
					}
				}
			}
		}
		
		/* Retornar a menor aresta */
		return menor
	}
}