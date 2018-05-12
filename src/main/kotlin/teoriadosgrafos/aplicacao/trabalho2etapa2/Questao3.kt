package teoriadosgrafos.aplicacao.trabalho2etapa2

import com.github.icarohs7.unoxlib.tables.ScrollTable
import net.miginfocom.swing.MigLayout
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.arvoregeradora.Kruskal
import teoriadosgrafos.arvoregeradora.MST
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * The type Questao 3.
 */
internal class Questao3 {
	/**
	 * Root panel.
	 */
	val rootPanel = JPanel(MigLayout("wrap 1"))
	
	/**
	 * Instantiates a new Questao 3.
	 */
	init {
		/* Criar e Definir componentes gráficos */
		criarComponentes()
	}
	
	/**
	 * Criar componentes.
	 */
	private fun criarComponentes() {
		/* Título */
		val titulo = JLabel("Questão 3. MST utilizando Kruskal")
		titulo.font = ViewUtil.h1
		/* Label 1 */
		val label1 = JLabel("Grafo 1")
		label1.font = ViewUtil.h2
		/* Label 2 */
		val label2 = JLabel("Grafo 2")
		label2.font = ViewUtil.h2
		/* Adicionar Componentes ao Painel */
		/* Título */
		rootPanel.add(titulo, "align center")
		/* Label 1 */
		rootPanel.add(label1, "align center")
		/* Tabela 1 */
		var dados = ViewUtil.replaceInfinity(primeiroGrafo().getTreeAsString())
		var colunas = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
		rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		/* Label 2 */
		rootPanel.add(label2, "align center")
		/* Tabela 2 */
		dados = ViewUtil.replaceInfinity(segundoGrafo().getTreeAsString())
		colunas = arrayOf("1", "2", "3", "4", "6", "7", "8")
		rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
	}
	
	/**
	 * Primeiro grafo mst.
	 * @return the mst
	 */
	private fun primeiroGrafo(): MST {
		/* Definição do grafo */
		return Kruskal.gerar(
			GrafoPonderado(10, false)
				.apply {
					/* Adição das Arestas */
					/* Vértice a */
					addAresta(1, 2, 60)
					addAresta(1, 3, 54)
					addAresta(1, 4, 42)
					/* Vértice b */
					addAresta(2, 4, 71)
					addAresta(2, 6, 29)
					/* Vértice c */
					addAresta(3, 4, 56)
					addAresta(3, 5, 67)
					/* Vértice d */
					addAresta(4, 5, 26)
					addAresta(4, 6, 52)
					addAresta(4, 7, 87)
					/* Vértice e */
					addAresta(5, 7, 70)
					addAresta(5, 9, 73)
					/* Vértice f */
					addAresta(6, 7, 20)
					addAresta(6, 8, 25)
					/* Vértice g */
					addAresta(7, 8, 36)
					addAresta(7, 9, 59)
					addAresta(7, 10, 32)
					/* Vértice h */
					addAresta(8, 10, 25)
					/* Vértice i */
					addAresta(9, 10, 26)
					/* Gerar MST utilizando o algoritmo de Kruskal */
				})
	}
	
	/**
	 * Segundo grafo mst.
	 * @return the mst
	 */
	private fun segundoGrafo(): MST {
		/* Criação do grafo */
		return Kruskal.gerar(
			GrafoPonderado(7, false)
				.apply {
					/* Adição das arestas */
					/* Vértice 1 */
					addAresta(1, 2, 2)
					addAresta(1, 3, 4)
					addAresta(1, 4, 5)
					/* Vértice 2 */
					addAresta(2, 4, 2)
					addAresta(2, 5, 7)
					/* Vértice 3 */
					addAresta(3, 4, 1)
					addAresta(3, 6, 4)
					/* Vértice 4 */
					addAresta(4, 5, 2)
					addAresta(4, 6, 3)
					/* Vértice 6 */
					addAresta(5, 6, 1)
					addAresta(5, 7, 4)
					/* Vértice 7 */
					addAresta(6, 7, 7)
				})
	}
}