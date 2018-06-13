package teoriadosgrafos.atividades.etapa2trabalho1

import com.github.icarohs7.unoxlib.tables.ScrollTable
import net.miginfocom.swing.MigLayout
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.atividades.h1
import teoriadosgrafos.atividades.h2
import teoriadosgrafos.extensoes.replaceInfinity
import teoriadosgrafos.operacoes.arvoregeradora.Kruskal
import teoriadosgrafos.operacoes.arvoregeradora.MST
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
		titulo.font = h1
		/* Label 1 */
		val label1 = JLabel("Grafo 1")
		label1.font = h2
		/* Label 2 */
		val label2 = JLabel("Grafo 2")
		label2.font = h2
		/* Adicionar Componentes ao Painel */
		/* Título */
		rootPanel.add(titulo, "align center")
		/* Label 1 */
		rootPanel.add(label1, "align center")
		/* Tabela 1 */
		var dados = primeiroGrafo().getTreeAsString().replaceInfinity()
		var colunas = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
		rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		/* Label 2 */
		rootPanel.add(label2, "align center")
		/* Tabela 2 */
		dados = segundoGrafo().getTreeAsString().replaceInfinity()
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
					addAresta(1, 2, 60.0)
					addAresta(1, 3, 54.0)
					addAresta(1, 4, 42.0)
					/* Vértice b */
					addAresta(2, 4, 71.0)
					addAresta(2, 6, 29.0)
					/* Vértice c */
					addAresta(3, 4, 56.0)
					addAresta(3, 5, 67.0)
					/* Vértice d */
					addAresta(4, 5, 26.0)
					addAresta(4, 6, 52.0)
					addAresta(4, 7, 87.0)
					/* Vértice e */
					addAresta(5, 7, 70.0)
					addAresta(5, 9, 73.0)
					/* Vértice f */
					addAresta(6, 7, 20.0)
					addAresta(6, 8, 25.0)
					/* Vértice g */
					addAresta(7, 8, 36.0)
					addAresta(7, 9, 59.0)
					addAresta(7, 10, 32.0)
					/* Vértice h */
					addAresta(8, 10, 25.0)
					/* Vértice i */
					addAresta(9, 10, 26.0)
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
					addAresta(1, 2, 2.0)
					addAresta(1, 3, 4.0)
					addAresta(1, 4, 5.0)
					/* Vértice 2 */
					addAresta(2, 4, 2.0)
					addAresta(2, 5, 7.0)
					/* Vértice 3 */
					addAresta(3, 4, 1.0)
					addAresta(3, 6, 4.0)
					/* Vértice 4 */
					addAresta(4, 5, 2.0)
					addAresta(4, 6, 3.0)
					/* Vértice 6 */
					addAresta(5, 6, 1.0)
					addAresta(5, 7, 4.0)
					/* Vértice 7 */
					addAresta(6, 7, 7.0)
				})
	}
}