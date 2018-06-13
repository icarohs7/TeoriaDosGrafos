package teoriadosgrafos.atividades.etapa2trabalho2

import com.github.icarohs7.unoxlib.tables.ScrollTable
import net.miginfocom.swing.MigLayout
import teoriadosgrafos.Aresta
import teoriadosgrafos.atividades.h1
import teoriadosgrafos.atividades.h2
import teoriadosgrafos.extensoes.getResource
import teoriadosgrafos.extensoes.replaceInfinity
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Tela View
 * @param titulo String: Título da janela
 */
class View(titulo: String) : JFrame(titulo) {
	/**
	 * Painel raiz da aplicação
	 */
	private val root = JPanel(MigLayout("fillx, wrap 3"))
	
	init {
		/* Ícone da janela */
		iconImage = toolkit.getImage(getResource("drawable/icon.png"))
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
	}
	
	/**
	 * Criar componentes
	 */
	private fun criarComponentes() {
		root.apply {
			/* Labels */
			val lblTitulo1 = JLabel("Questão 1 - Saindo de Ipatinga").apply { font = h1 }
			val lblDistancias = JLabel("Tabela de distâncias").apply { font = h2 }
			val lblPrecedentes = JLabel("Tabela de precedentes").apply { font = h2 }
			val lblTitulo2 = JLabel("Questão 2 - Mínimo de pontes").apply { font = h1 }
			val lblMST = JLabel("Tabela com o mínimo de pontes necessárias").apply { font = h2 }
			val lblArestas = JLabel("Pontes a serem construídas:").apply { font = h2 }
			
			/* Tabelas */
			var colunas = arrayOf(
				"Ipatinga", "Timóteo", "Coronel Fabriciano", "Naque",
				"Periquito", "Governador Valadares", "Santada do Paraíso"
			)
			val tblDistQuestao1 =
				ScrollTable(arrayOf(Questao1.buscaDeDistancias.distanciasAsStringArray()), colunas)
					.scrollableTable
			
			val precedentes = Questao1.buscaDeDistancias.predecessores
				.map { Questao1.findCidade(it) }
			val tblPredQuestao1 =
				ScrollTable(arrayOf(precedentes.map { it.toString() }.toTypedArray()), colunas)
					.scrollableTable
			
			colunas = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")
			val tblMSTQuestao2 =
				ScrollTable(
					Questao2.arvoreMinima.getTreeAsString().replaceInfinity(), colunas)
					.scrollableTable
			
			/* Adição dos componentes ao painel */
			add(lblTitulo1, "span, center, wrap")
			add(lblDistancias, "span, center, wrap")
			add(tblDistQuestao1, "span, grow, center,  wrap")
			add(lblPrecedentes, "span, center, wrap")
			add(tblPredQuestao1, "span, grow, center, wrap, gapbottom 20")
			add(lblTitulo2, "span, center, wrap")
			add(lblMST, "span, center, wrap")
			add(tblMSTQuestao2, "span, grow, center, wrap")
			add(lblArestas, "span, center, wrap")
			
			/* Mapear todas as arestas incrementando sua origem e destino em 1,
			 * Depois quebrar a lista em listas menores com tamanho máximo de 3 elementos,
			 * Por fim, criar paineis contendo cada sublista gerada com cada elemento sendo
			 * Uma label */
			Questao2.arvoreMinima.getArestas()
				.map { Aresta(it.origem + 1, it.destino + 1, it.peso) }
				.chunked(3)
				.forEach { chunk ->
					add(JPanel(MigLayout())
						.apply {
							chunk.forEach {
								add(JLabel(
									"De ${it.origem} para ${it.destino} com peso ${it.peso}"),
									"gap 20 20")
							}
						}, "span, center, wrap")
				}
		}
	}
}
