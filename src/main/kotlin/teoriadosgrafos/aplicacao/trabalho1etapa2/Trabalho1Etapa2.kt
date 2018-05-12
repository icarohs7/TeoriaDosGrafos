package teoriadosgrafos.aplicacao.trabalho1etapa2

import com.jtattoo.plaf.acryl.AcrylLookAndFeel
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException

/**
 * Trabalho Extra, Etapa 2, Teoria dos Grafos
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
object Trabalho1Etapa2 {
	@JvmStatic
	fun main(args: Array<String>) {
		/* Definir Look&Feel da aplicação */
		try {
			UIManager.setLookAndFeel(AcrylLookAndFeel())
		} catch (e: UnsupportedLookAndFeelException) {
			e.printStackTrace()
		}
		/* Criar a view */
		View("Teoria dos Grafos - Trabalho Extra Etapa 2").apply { isVisible = true }
	}
}
