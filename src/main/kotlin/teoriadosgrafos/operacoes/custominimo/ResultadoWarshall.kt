package teoriadosgrafos.operacoes.custominimo

import java.io.PrintStream
import java.util.Arrays

/**
 * Classe responsável por armazenar o conjunto de dados gerados de uma busca utilizando o algoritmo de Floyd-Warshall
 * @property distancias Array -- A matriz contendo as menores distâncias
 * @property proximos Array -- A matriz indicando os próximos elementos
 * @constructor
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
data class ResultadoWarshall(private val distancias: Array<DoubleArray>, private val proximos: Array<Array<Int>>) {
	/**
	 * Retorna o menor caminho saindo da origem geradora do resultado atual em direção ao destino informado
	 * @param origem Int -- O vértice origem
	 * @param destino Int -- O vértice que se deseja alcançar apartir da origem
	 * @return List<Int> -- O menor caminho partindo da origem ao destino
	 */
	fun buscar(origem: Int, destino: Int): List<Int> {
		val o = origem - 1
		val d = destino - 1
		if (proximos[o][d] == -1) {
			return arrayListOf()
		}
		val caminho = arrayListOf<Int>()
		caminho.add(origem)
		var i = o
		while (i != d) {
			i = proximos[i][d]
			caminho.add(i + 1)
		}
		return caminho
	}
	
	/**
	 * Retorna a matriz de distâncias no formato matriz de strings
	 * @return Array<Array<String>> -- A matriz de strings
	 */
	fun getDistanciasAsStringArray(): Array<Array<String>> {
		/* Criar a matriz de strings */
		val stringArray = Array(distancias.size) { Array(distancias.size) { "" } }
		
		for (i in 0 until distancias.size) {
			for (j in 0 until distancias.size) {
				/* Atribuir cada elemento da matriz de distâncias para a matriz de strings no devido formato */
				stringArray[i][j] = distancias[i][j].toString()
			}
		}
		
		/* Retornar a matriz */
		return stringArray
	}
	
	/**
	 * Implementação da comparação de igualdade entre 2 objetos ResultadoPonderado
	 * @param other Any?
	 * @return Boolean
	 */
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as ResultadoWarshall
		
		if (!Arrays.equals(distancias, other.distancias)) return false
		if (!Arrays.equals(proximos, other.proximos)) return false
		
		return true
	}
	
	/**
	 * Implementação do hashing de um objeto filho da classe atual baseado em seus 2 atributos
	 * @return Int
	 */
	override fun hashCode(): Int {
		var result = Arrays.hashCode(distancias)
		result = 31 * result + Arrays.hashCode(proximos)
		return result
	}
	
	
	/**
	 * Imprimir a matriz de distâncias
	 * @param out PrintStream -- A saída onde será impressa a matriz
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun printDistancias(out: PrintStream) {
		distancias.forEach { linha ->
			linha.forEach { elem ->
				out.print("$elem ")
			}
			out.println()
		}
	}
	
	/**
	 * Imprimir a matriz de distâncias no console
	 */
	@Suppress("unused")
	fun printDistancias() {
		printDistancias(System.out)
	}
	
}