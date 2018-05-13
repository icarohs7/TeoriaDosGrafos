package teoriadosgrafos.aplicacao.trabalho2etapa2

import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.metodosdebusca.ponderado.Dijkstra
import teoriadosgrafos.metodosdebusca.ponderado.ResultadoPonderado

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
				Cidades.IPATINGA to intArrayOf(0, 30, 20, 50, 80, 200, 60),
				Cidades.TIMOTEO to intArrayOf(30, 0, 15, 60, 100, 280, 90),
				Cidades.CORONEL_FABRICIANO to intArrayOf(20, 15, 0, 55, 85, 250, 75),
				Cidades.NAQUE to intArrayOf(50, 60, 55, 0, 30, 150, 30),
				Cidades.PERIQUITO to intArrayOf(80, 100, 85, 30, 0, 100, 50),
				Cidades.GOVERNADOR_VALADARES to intArrayOf(200, 280, 250, 150, 100, 0, 120),
				Cidades.SANTANA_DO_PARAISO to intArrayOf(60, 90, 75, 30, 50, 120, 0)
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
			val tabelaDeCustos = tabelaDeDistancias.map { (_, distancias) ->
				distancias.mapIndexed { index, aresta ->
					(
							aresta
									+ tabelaDePessoal[findCidade(index)]!! * 5
									+ (aresta / 12) * 3.5
							)
				}.toDoubleArray()
			}.toTypedArray()
			return Dijkstra.buscar(1, GrafoPonderado(tabelaDeCustos))
		}
	
	/**
	 * Enum contendo as cidades
	 * @property index Int: Índice da cidade
	 * @constructor
	 */
	enum class Cidades(val index: Int) {
		IPATINGA(0),
		TIMOTEO(1),
		CORONEL_FABRICIANO(2),
		NAQUE(3),
		PERIQUITO(4),
		GOVERNADOR_VALADARES(5),
		SANTANA_DO_PARAISO(6);
	}
	
	/**
	 * Encontrar uma cidade pelo seu índice
	 * @param index Int
	 * @return Cidades
	 */
	fun findCidade(index: Int): Cidades {
		if (index > 7) {
			throw RuntimeException("Indíce inexistente")
		}
		
		return Cidades.values()[index]
	}
}