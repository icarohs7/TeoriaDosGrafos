package teoriadosgrafos.aplicacao.trabalho1etapa1;

/**
 * Implementação do modelo de tabela que permite a edição da mesma após a
 * instanciação<br>
 * <br>
 * Feito por codejava.net, Código e documentação em<br>
 * <a href="http://www.codejava.net/java-se/swing/editable-jtable-example">
 * http://www.codejava.net/java-se/swing/editable-jtable-example
 * </a>
 */
public class EditableTableModel extends javax.swing.table.AbstractTableModel {
	
	/**
	 * Títulos das colunas
	 */
	private String[] columnTitles;
	
	/**
	 * Dados contidos na tabela
	 */
	private Object[][] dataEntries;
	
	/**
	 * Quantidade de linhas da tabela
	 */
	private int rowCount;
	
	/**
	 * Inicializar tabela
	 *
	 * @param columnTitles Títulos das colunas
	 * @param dataEntries  Dados contidos nas colunas
	 */
	public EditableTableModel( String[] columnTitles, Object[][] dataEntries ) {
		this.columnTitles = columnTitles;
		this.dataEntries = dataEntries;
	}
	
	/**
	 * Retorna o número de linhas contidas na tabela
	 *
	 * @return O número de linhas da tabela
	 */
	@Override
	public int getRowCount() {
		return dataEntries.length;
	}
	
	/**
	 * Retorna o número de colunas contidas na tabela
	 *
	 * @return O número de colunas da tabela
	 */
	@Override
	public int getColumnCount() {
		return columnTitles.length;
	}
	
	/**
	 * Retorna o valor contido na célula selecionada
	 *
	 * @param row    Índice da linha da célula a ser selecionada, de 0 a n
	 * @param column Índice da coluna da célula a ser selecionada, de 0 a n
	 *
	 * @return O conteúdo da célula selecionada
	 */
	@Override
	public Object getValueAt( int row, int column ) {
		return dataEntries[row][column];
	}
	
	/**
	 * Retorna o nome da coluna selecionada
	 *
	 * @param column Índice da coluna que se deseja obter o nome, de 0 a n
	 *
	 * @return O nome da coluna indicada
	 */
	@Override
	public String getColumnName( int column ) {
		return columnTitles[column];
	}
	
	/**
	 * Retorna se a célula indicada é ou não editável
	 *
	 * @param row    Índice da linha da célula a ser selecionada, de 0 a n
	 * @param column Índice da coluna da célula a ser selecionada, de 0 a n
	 *
	 * @return Se a célula é ou não editável
	 */
	@Override
	public boolean isCellEditable( int row, int column ) {
		return true;
	}
	
	/**
	 * Edita uma célula da tabela
	 *
	 * @param value  O conteúdo que se deseja inserir na célula
	 * @param row    Índice da linha onde a célula desejada se encontra, de 0 a n
	 * @param column Índice da coluna onde a célula desejada se encontra, de 0 a
	 *               n
	 */
	@Override
	public void setValueAt( Object value, int row, int column ) {
		dataEntries[row][column] = value;
	}
	
}
