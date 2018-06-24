package teoriadosgrafos

data class Aresta(val origem: Int, val destino: Int, val peso: Double) {
	override fun toString(): String {
		return "Aresta($origem,$destino) = $peso"
	}
}
