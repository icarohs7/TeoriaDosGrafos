package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxgraph.estatico.CicloNegativoException
import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.estatico.TipoDeGrafoIncorretoException
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.funcoes.matrizOf

/**
 * Função de extensão injetando o cálculo de custo mínimo utilizando
 * o algoritmo de Floyd Warshall na classe grafo
 */
fun Grafo.custoMinimoFloydWarshall(): ResultadoWarshall {
	return floydWarshall(this as? Grafo.Ponderado ?: throw TipoDeGrafoIncorretoException())
}

private fun floydWarshall(grafo: Grafo.Ponderado): ResultadoWarshall {
	/* Inicializar o grafo */
	grafo.desmarcarTodosOsVertices()
	/* Pesos */
	val w = grafo.matrizAdjacencia
	/* Criar e Inicializar as matrizes de distâncias e próximos */
	val d = matrizOf(grafo.tamanho) { INFINITO }
	val prox = matrizOf(grafo.tamanho) { -1 }
	/* Processo de inicialização */
	for (u in 0 until grafo.tamanho) {
		for (v in 0 until grafo.tamanho) {
			/* Para cada aresta (u,v) definir
			 * dist[u][v] para w[u][v] */
			if (w[u][v] != INFINITO) {
				d[u][v] = w[u][v]
				prox[u][v] = v
			}
		}
	}
	
	/* Executar a busca */
	/* K representa a definição da distância mínima partindo de cada vértice para todos os outros */
	for (k in 0 until grafo.tamanho) {
		for (i in 0 until grafo.tamanho) {
			for (j in 0 until grafo.tamanho) {
				/* Para cada distância de I para J, se a mesma for
				 * maior que a distância de I para K somada à distância
				 * de K para J, tornar a distância de I para J para a distância
				 * de I para K somada à distância de K para J, sendo K o vértice
				 * entre eles e fazendo o próximo de I para J ser o próximo de
				 * I para K*/
				if (d[i][j] > d[i][k] + d[k][j]) {
					d[i][j] = d[i][k] + d[k][j]
					prox[i][j] = prox[i][k]
				}
			}
		}
	}
	
	/* Verificação para ciclos negativos no grafo */
	for (i in 0 until grafo.tamanho) {
		if (d[i][i] < 0) {
			throw CicloNegativoException()
		}
	}
	
	/* Zerar distância dos elementos até si mesmos */
	for (i in 0 until grafo.tamanho) {
		d[i][i] = 0.0
	}
	
	/* Retornar o resultado contendo as distâncias e os próximos */
	return ResultadoWarshall(d, prox)
}