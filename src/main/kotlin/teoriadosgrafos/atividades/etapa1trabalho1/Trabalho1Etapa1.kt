package teoriadosgrafos.atividades.etapa1trabalho1

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel
import java.util.Arrays
import javax.swing.UnsupportedLookAndFeelException


/**
 * Primeiro trabalho avaliativo - Teoria dos grafos<br></br>
 * Data limite p/ entrega: 28/03/2018<br></br>
 * <br></br>
 * Grupo:<br></br>
 * Icaro R D Temponi - CCO6<br></br>
 * Daniel G Moura - CCO5<br></br>
 * <br></br>
 * Look and Feel utilizado: [WebLaF](http://weblookandfeel.com/)
 * @param args
 */
fun main(args: Array<String>) {
	// Selecionar o Look & Feel da aplicação
	try {
		javax.swing.UIManager.setLookAndFeel(GraphiteLookAndFeel())
	} catch (e: UnsupportedLookAndFeelException) {
		println(Arrays.toString(e.stackTrace))
	}
	
	//Instanciar e tornar visível a interface da aplicação
	View().apply {
		isVisible = true
	}
}
