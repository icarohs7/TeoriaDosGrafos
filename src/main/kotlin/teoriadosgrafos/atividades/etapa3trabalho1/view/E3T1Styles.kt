package teoriadosgrafos.atividades.etapa3trabalho1.view

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*


class E3T1Styles : Stylesheet() {
	init {
		importStylesheet("/bootstrapfx.css")
		
		val centered = mixin {
			alignment = Pos.CENTER
			maxWidth = Double.MAX_VALUE.px
		}
		
		label {
			fontSize = 18.pt
			+centered
		}
		
		datagridCell {
			backgroundColor += Color.TRANSPARENT
			+centered
		}
	}
}