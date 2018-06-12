package teoriadosgrafos.aplicacao.etapa3trabalho1.controller

import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.extensoes.plusAssign
import tornadofx.*

class E3T1Controller : Controller() {
	val grafo = GrafoNaoPonderado(11, direcionado = true).also { grafo ->
		
		/* Vértice 7 */
		grafo += 7 to 8
		grafo += 7 to 11
		
		/* Vértice 5 */
		grafo += 5 to 11
		
		/* Vértice 3 */
		grafo += 3 to 8
		grafo += 3 to 10
		
		/* Vértice 11 */
		grafo += 11 to 2
		grafo += 11 to 9
		grafo += 11 to 10
		
		/* Vértice 8 */
		grafo += 8 to 9
	}
}