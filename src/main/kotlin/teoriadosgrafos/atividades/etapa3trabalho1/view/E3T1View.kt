package teoriadosgrafos.atividades.etapa3trabalho1.view

import javafx.geometry.Insets
import javafx.geometry.Pos
import org.kordamp.bootstrapfx.scene.layout.Panel
import teoriadosgrafos.atividades.etapa3trabalho1.controller.E3T1Controller
import teoriadosgrafos.operacoes.customaximo.caminhoMaximo
import teoriadosgrafos.operacoes.customaximo.custoMaximo
import teoriadosgrafos.operacoes.ordemtopologica.khan
import tornadofx.*

class E3T1View : View("Etapa 3") {
	private val controller: E3T1Controller by inject()
	
	override val root = vbox {
		padding = Insets(10.0)
		
		gridpane {
			spacing = 10.0
			maxWidth = Double.MAX_VALUE
			alignment = Pos.CENTER
			addClass("h1")
			label("Trabalho 1") {
				addClass("lbl", "lbl-info")
			}
		}
		
		add(Panel("Grafo ordenado topológicamente:").apply {
			addClass("panel", "panel-info")
			body = label("${controller.grafo.ordemTopologica.khan()}")
		})
		
		add(Panel("Custo máximo do grafo:").apply {
			addClass("panel", "panel-info")
			body = label("${controller.grafo.custoMaximo.custoMaximo()}")
		})
		
		add(Panel("Caminho máximo:").apply {
			addClass("panel", "panel-info")
			body = label("${controller.grafo.custoMaximo.caminhoMaximo()}")
		})
	}
}