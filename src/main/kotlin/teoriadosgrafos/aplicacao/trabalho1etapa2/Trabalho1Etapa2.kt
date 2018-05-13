package teoriadosgrafos.aplicacao.trabalho1etapa2

import com.jtattoo.plaf.acryl.AcrylLookAndFeel
import net.miginfocom.swing.MigLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.ScrollPaneConstants
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException

/**
 * Primeiro trabalho da segunda etapa de teoria dos grafos
 * CCO 6 - Facúldade Única de Ipatinga
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 * @param args Array<String>
 */
fun main(args: Array<String>) {
	/* Definir o Look And Feel da aplicação */
	try {
		UIManager.setLookAndFeel(AcrylLookAndFeel())
	} catch (e: UnsupportedLookAndFeelException) {
		e.printStackTrace()
	}
	
	/* Criar painel raiz */
	val panel = JPanel(MigLayout("wrap 1"))
		.apply {
			/* Adicionar componentes ao painel */
			add(Questao1().rootPanel, "align center")
			add(Questao2().rootPanel, "align center")
			add(Questao3().rootPanel, "align center")
		}
	
	/* Scroll pane que embrulhará o painel raiz, permitindo o scroll */
	val scroll = JScrollPane(panel)
		.apply {
			/* Desativar o scroll horizontal */
			horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			/* Definir velocidade do scroll vertical */
			verticalScrollBar.unitIncrement = 16
		}
	
	/* Criar frame */
	JFrame("Etapa 2 Trabalho 1 TeoriaDosGrafos").apply {
		/* Definir painel raiz da aplicação no frame */
		contentPane = scroll
		/* Operação de fechamento */
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		/* Dimensiona os componentes */
		pack()
		/* Define o tamanho da janela */
		setSize((Questao2().rootPanel.preferredSize.width * 1.05).toInt(), 600)
		/* Centraliza a janela da aplicação */
		setLocationRelativeTo(null)
		/* Torna a aplicação visivel */
		isVisible = true
	}
}
