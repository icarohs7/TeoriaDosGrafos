package teoriadosgrafos.atividades.etapa2trabalho2

import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.extensoes.toDoubleArray
import teoriadosgrafos.extensoes.toFixed
import teoriadosgrafos.operacoes.custominimo.ResultadoPonderado
import teoriadosgrafos.operacoes.custominimo.dijkstra

/**
 * A faculdade Única de Ipatinga está necessitando de um software que auxilie o setor de
 * marketing a planejar a campanha para o vestibular do 2º semestre de 2018 e 1º
 * semestre 2019. O problema consiste em planejar o roteiro de viagem para cidades de
 * forma que minimize o custo de viagem e consiga fazer a campanha (visitar todas as
 * cidades). Ela dispõe de um efetivo de 10 pessoas e um veículo para transporte.
 */
object Questao1 {
	/**
	 * Tabela de distâncias pedida na questão
	 */
	val buscaDeDistancias: ResultadoPonderado
		get() {
			/* Distância em kilômetros entre as cidades */
			val tabelaDeDistancias = mapOf(
				Cidades.IPATINGA to intArrayOf(0, 30, 20, 50, 80, 200, 60).toDoubleArray(),
				Cidades.TIMOTEO to intArrayOf(30, 0, 15, 60, 100, 280, 90).toDoubleArray(),
				Cidades.CORONEL_FABRICIANO to intArrayOf(20, 15, 0, 55, 85, 250, 75).toDoubleArray(),
				Cidades.NAQUE to intArrayOf(50, 60, 55, 0, 30, 150, 30).toDoubleArray(),
				Cidades.PERIQUITO to intArrayOf(80, 100, 85, 30, 0, 100, 50).toDoubleArray(),
				Cidades.GOVERNADOR_VALADARES to intArrayOf(200, 280, 250, 150, 100, 0, 120).toDoubleArray(),
				Cidades.SANTANA_DO_PARAISO to intArrayOf(60, 90, 75, 30, 50, 120, 0).toDoubleArray()
			)
			
			/* Pessoal necessário por cidade */
			val tabelaDePessoal = mapOf(
				Cidades.IPATINGA to 10,
				Cidades.TIMOTEO to 5,
				Cidades.CORONEL_FABRICIANO to 10,
				Cidades.NAQUE to 3,
				Cidades.PERIQUITO to 3,
				Cidades.GOVERNADOR_VALADARES to 10,
				Cidades.SANTANA_DO_PARAISO to 5
			)
			
			/* Tabela com os pesos das arestas calculados */
			val tabelaDeCustos = tabelaDeDistancias.map { (cidade, distancias) ->
				distancias.mapIndexed { index, aresta ->
					if (cidade == findCidade(index)) {
						/* Se o destino for a própria cidade, zerar o custo */
						0.0
					} else {
						/* Do contrário, Peso da aresta =
						 * distancia + pessoal * 5 + (distancia / 12) * 3,5 */
						(aresta + tabelaDePessoal[findCidade(index)]!! * 5 + (aresta / 12) * 3.5)
							.toFixed(2)
					}
				}.toDoubleArray()
			}.toTypedArray()
			
			return GrafoPonderado(tabelaDeCustos).custoMinimo.dijkstra(1)
		}
	
	/**
	 * Enum contendo as cidades
	 * @constructor
	 */
	enum class Cidades {
		IPATINGA,
		TIMOTEO,
		CORONEL_FABRICIANO,
		NAQUE,
		PERIQUITO,
		GOVERNADOR_VALADARES,
		SANTANA_DO_PARAISO;
	}
	
	/**
	 * Encontrar uma cidade pelo seu índice
	 * @param index Int
	 * @return Cidades
	 */
	fun findCidade(index: Int): Cidades {
		if (index > 7) {
			throw IndexOutOfBoundsException()
		}
		
		return Cidades.values()[index]
	}
}