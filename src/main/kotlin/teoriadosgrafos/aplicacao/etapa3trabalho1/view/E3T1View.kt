package teoriadosgrafos.aplicacao.etapa3trabalho1.view

import javafx.beans.property.SimpleStringProperty
import teoriadosgrafos.aplicacao.etapa3trabalho1.controller.E3T1Controller
import teoriadosgrafos.ordemtopologica.khan
import tornadofx.*

var gtxt = SimpleStringProperty("")

fun gp(s: String) {
	gtxt.value = "${gtxt.value}$s\n"
}

class E3T1View : View("Etapa 3") {
	private val controller: E3T1Controller by inject()
	
	init {
		controller.grafo.ordemTopologica.khan()
	}
	
	override val root = stackpane {
		label(gtxt)
	}
}