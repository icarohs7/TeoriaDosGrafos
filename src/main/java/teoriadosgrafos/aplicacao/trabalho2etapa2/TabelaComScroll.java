package teoriadosgrafos.aplicacao.trabalho2etapa2;

import org.jetbrains.annotations.NotNull;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Sobrescrita da JTable padrão para fazer o tamanho do JScroll se adaptar automaticamente à tabela
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class TabelaComScroll extends JTable {
	/**
	 * Instantiates a new Tabela com scroll.
	 *
	 * @param objects  objects
	 * @param objects1 objects 1
	 */
	public TabelaComScroll( @NotNull Object[][] objects, @NotNull Object[] objects1 ) {
		super( objects, objects1 );
		/* Desativar a reordenação das colunas */
		tableHeader.setReorderingAllowed( false );
		/* Desativar o redimensionamento das colunas */
		tableHeader.setResizingAllowed( false );
	}
	
	/**
	 * Retorna um JScrollPane contendo a tabela atual
	 *
	 * @return the scrollable table
	 */
	public JScrollPane getScrollableTable() {
		return new JScrollPane( this );
	}
	
	/**
	 * Desativar a seleção de células
	 *
	 * @param i  the
	 * @param i1 the 1
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isCellSelected( int i, int i1 ) {
		return false;
	}
	
	/**
	 * Desativar a edição de células
	 *
	 * @param i  the
	 * @param i1 the 1
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isCellEditable( int i, int i1 ) {
		return false;
	}
	
	/**
	 * Definir o tamanho da tabela quando embrulhada para o seu tamanho completo
	 *
	 * @return the preferred scrollable viewport size
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}
	
	
}
