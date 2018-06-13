package teoriadosgrafos.atividades.etapa2trabalhoextra

import com.github.icarohs7.unoxlib.tables.ScrollTable
import net.miginfocom.swing.MigLayout
import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.extensoes.toDouble2DArray
import teoriadosgrafos.operacoes.custominimo.dijkstra
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

internal class View constructor(s: String) : JFrame(s) {
	/**
	 * Root.
	 */
	private val root = JPanel(MigLayout("wrap 1"))
	
	init {
		/* Instanciar painel raiz da aplicação */
		/* Definir o container raiz da view */
		contentPane = root
		/* Definir operação de fechamento */
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		/* Inicializar componentes */
		criarComponentes()
		/* Dimensionar a view de acordo com seus componentes */
		pack()
		/* Centralizar Janela */
		setLocationRelativeTo(null)
	}
	
	private fun criarComponentes() {
		@Suppress("LocalVariableName")
		val NA = Grafo.INFINITO.toInt()
		/* Matriz geradora do grafo */
		val matriz = arrayOf(
			/*                a   b   c   d   e   f   g   h   i   j   l   m */
			/*a*/ intArrayOf(NA, 61, 50, 42, NA, NA, NA, NA, NA, NA, NA, NA),
			/*b*/ intArrayOf(61, NA, NA, 32, NA, 29, NA, NA, NA, 17, NA, NA),
			/*c*/ intArrayOf(50, NA, NA, 56, 67, NA, NA, NA, NA, NA, NA, NA),
			/*d*/ intArrayOf(42, 32, 56, NA, 45, 62, 85, NA, NA, NA, NA, NA),
			/*e*/ intArrayOf(NA, NA, 67, 45, NA, NA, 72, NA, 73, NA, NA, NA),
			/*f*/ intArrayOf(NA, 29, NA, 62, NA, NA, 20, 35, NA, 30, 45, NA),
			/*g*/ intArrayOf(NA, NA, NA, 85, 72, 20, NA, 40, 60, NA, NA, 32),
			/*h*/ intArrayOf(NA, NA, NA, NA, NA, 35, 40, NA, NA, NA, 50, 21),
			/*i*/ intArrayOf(NA, NA, NA, NA, 73, NA, 60, NA, NA, NA, NA, 50),
			/*j*/ intArrayOf(NA, 17, NA, NA, NA, 30, NA, NA, NA, NA, 30, NA),
			/*l*/ intArrayOf(NA, NA, NA, NA, NA, 45, NA, 50, NA, 30, NA, NA),
			/*m*/ intArrayOf(NA, NA, NA, NA, NA, 32, NA, 21, 50, NA, NA, NA)).toDouble2DArray()
		
		/* Instanciar o grafo */
		val grafo = GrafoPonderado(matriz, true)
		
		/* Guardar o resultado da busca */
		val resultado = grafo.custoMinimo.dijkstra(1)
		
		/* Nomes das colunas das tabelas */
		val colunas = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l", "m")
		
		/* Variável que armazenará os dados passados para as tabelas */
		var dados: Array<Array<String>>
		
		/* Adicionar componentes ao painel */
		
		/* Label1 */
		root.add(JLabel("Tabela de distâncias"), "align center")
		
		/* Tabela 1 */
		dados = arrayOf(resultado.distanciasAsStringArray())
		root.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		
		/* Label2 */
		root.add(JLabel("Tabela de predecessores"), "align center")
		
		/* Tabela 2 */
		dados = arrayOf(resultado.precedentesAsStringArray())
		root.add(ScrollTable(dados, colunas).scrollableTable, "align center")
	}
}
