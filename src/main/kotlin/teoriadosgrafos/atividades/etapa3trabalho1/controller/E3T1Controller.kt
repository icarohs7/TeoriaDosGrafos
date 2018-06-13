package teoriadosgrafos.atividades.etapa3trabalho1.controller

import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.extensoes.plusAssign
import tornadofx.*

class E3T1Controller : Controller() {
	val grafo = GrafoNaoPonderado(11, true).also { grafo ->
		grafo += 7 to 8
		grafo += 7 to 11
		
		grafo += 5 to 11
		
		grafo += 3 to 8
		grafo += 3 to 10
		
		grafo += 11 to 2
		grafo += 11 to 9
		grafo += 11 to 10
		
		grafo += 8 to 9
	}
}