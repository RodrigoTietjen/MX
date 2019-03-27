package testemx.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testemx.dto.ConsultarProdutoResponseV6;
import testemx.dto.ProdutoV6;
import testemx.service.impl.ProdutoQueryServiceImpl;;

public class TabelaModel extends AbstractTableModel {
    
	private static final long serialVersionUID = 1L;
    ProdutoQueryServiceImpl service = new ProdutoQueryServiceImpl();
    private List<ProdutoV6> produtos = service.getCadastroProdutos();

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		if(produtos != null) {
			return produtos.size();
		} 
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return produtos.get(rowIndex).getCodigo();
		}
		return produtos.get(rowIndex).getDescricao();
	}
	
	@Override
	public String getColumnName(int column) {
		if(column == 0) {
			return "Código";
		}
		return "Descrição";
	}

	public void filtrarProdutos(String codigo, String descricao) {
		produtos = new ArrayList<>();
		produtos.addAll(service.getCadastroProdutos());
		List<ProdutoV6> retorno = new ArrayList<>();
			produtos.forEach(p -> {
				if((p.getCodigo().toString().toLowerCase().startsWith((codigo != null ? codigo : "").toLowerCase())) && 
						(p.getDescricao().toLowerCase().startsWith((descricao != null ? descricao : "").toLowerCase()))) {
					retorno.add(p);
				}
			});
		produtos = retorno;
	}
	
}
