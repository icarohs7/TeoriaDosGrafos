package teoriadosgrafos.metodosdebusca.ponderado

interface Buscavel {
	fun buscar(origem: Int, destino: Int): List<Int>
}