package teoriadosgrafos.aplicacao

import com.jtattoo.plaf.acryl.AcrylLookAndFeel
import com.jtattoo.plaf.aero.AeroLookAndFeel
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
import com.jtattoo.plaf.fast.FastLookAndFeel
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel
import com.jtattoo.plaf.hifi.HiFiLookAndFeel
import com.jtattoo.plaf.luna.LunaLookAndFeel
import com.jtattoo.plaf.mcwin.McWinLookAndFeel
import com.jtattoo.plaf.mint.MintLookAndFeel
import com.jtattoo.plaf.noire.NoireLookAndFeel
import com.jtattoo.plaf.smart.SmartLookAndFeel
import com.jtattoo.plaf.texture.TextureLookAndFeel
import net.miginfocom.swing.MigLayout
import java.awt.Window
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.ListSelectionModel
import javax.swing.SwingUtilities
import javax.swing.UIManager

/**
 * Classe contendo o frame utilizado para a seleção do tema
 * Maioria do código reutilizado da aplicação modelo
 * Do look&feel JTattoo: código em http://www.jtattoo.net/Samples.html
 */
class LookAndFeelChoice(private val filho: JFrame) : JFrame() {
	/* Constantes para os temas */
	private val acryl = 0
	private val aero = 1
	private val aluminium = 2
	private val bernstein = 3
	private val fast = 4
	private val graphite = 5
	private val hifi = 6
	private val luna = 7
	private val mcwin = 8
	private val lafMint = 9
	private val noire = 10
	private val lafSmart = 11
	private val lafTexture = 12
	
	/* Nome dos temas */
	private val lafNames = arrayOf(
		"Acryl", "Aero", "Aluminium", "Bernstein",
		"Fast", "Graphite", "HiFi", "Luna", "McWin",
		"Mint", "Noire", "Smart", "Texture")
	
	/* Tema selecionado */
	private var selectedLaf = 0
	
	/* JList com os temas */
	private val lafList = JList(lafNames)
	
	/**
	 * Painel raiz da aplicação
	 */
	private val root = JPanel(MigLayout("fillx"))
	
	init {
		try {
			/* Definir Look&Feel inicial */
			UIManager.setLookAndFeel(AcrylLookAndFeel())
			/* Definição do título */
			title = "Escolha a Aparência"
			/* Procedimento de definição de componentes */
			criarComponentes()
			/* Definir painel contendo os componentes */
			contentPane = root
			/* Ajustar tamanho da janela aos componentes */
			pack()
			/* Definir a operação de fechamento da janela */
			defaultCloseOperation = JFrame.EXIT_ON_CLOSE
			/* Centralizar a janela */
			setLocationRelativeTo(null)
			/* Tornar visivel */
			isVisible = true
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
	
	/**
	 * Criar componentes
	 */
	private fun criarComponentes() {
		root.apply {
			add(JLabel("Escolha o tema da aplicação").apply { font = h1 }, "span, grow")
			lafList.apply {
				border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
				selectedIndex = 0
				selectionMode = ListSelectionModel.SINGLE_SELECTION
				addListSelectionListener { e ->
					if (!e.valueIsAdjusting) {
						if (selectedIndex != -1) {
							if (selectedLaf != selectedIndex) {
								selectedLaf = selectedIndex
								// We change the look and feel after all pending events are dispatched,
								// otherwise there will be some serious redrawing problems.
								SwingUtilities.invokeLater { setLookAndFeel() }
							}
						} else {
							// We don't want the list to be unselected, so if user unselects the list
							// we do select the last selected entry
							setSelectedIndex(selectedLaf)
						}
					}
				}
			}
			add(JScrollPane(lafList), "span, grow, center,wrap")
			add(JButton("Ok").apply {
				addActionListener {
					dispose()
					SwingUtilities.updateComponentTreeUI(filho)
					filho.pack()
					filho.isVisible = true
				}
			}, "grow")
		}
	}
	
	private fun setLookAndFeel() {
		try {
			val theme = "Default"
			when (selectedLaf) {
				acryl -> {
					// First set the theme of the look and feel. This must be done first because there
					// is some static initializing (color values etc.) when calling setTheme.
					// Another reason is that the theme variables are shared with all look and feels, so
					// without calling the setTheme method the look and feel will look ugly (wrong colors).
					com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(theme)
					// Now we can set the look and feel
					UIManager.setLookAndFeel(AcrylLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				aero -> {
					com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(AeroLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				aluminium -> {
					com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(AluminiumLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				bernstein -> {
					com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(BernsteinLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				fast -> {
					com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(FastLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				graphite -> {
					com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(GraphiteLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				hifi -> {
					com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(HiFiLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				luna -> {
					com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(LunaLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				mcwin -> {
					com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(McWinLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				lafMint -> {
					com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(MintLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				noire -> {
					com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(NoireLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				lafSmart -> {
					com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(SmartLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
				lafTexture -> {
					com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(theme)
					UIManager.setLookAndFeel(TextureLookAndFeel())
					SwingUtilities.updateComponentTreeUI(this)
					pack()
				}
			}
			val windows = Window.getWindows()
			for (window in windows) {
				if (window.isDisplayable) {
					SwingUtilities.updateComponentTreeUI(window)
				}
			}
			scrollSelectedToVisible(lafList)
		} catch (ex: Exception) {
			ex.printStackTrace()
		}
		
	} // end setLookAndFeel
	
	private fun scrollSelectedToVisible(list: JList<*>) {
		// Because of the different font size the selected item
		// maybe out of the visible area. So we correct this.
		val idx = list.leadSelectionIndex
		val rect = list.getCellBounds(idx, idx)
		if (rect != null) {
			list.scrollRectToVisible(rect)
		}
	} // end scrollSelectedToVisible
}
