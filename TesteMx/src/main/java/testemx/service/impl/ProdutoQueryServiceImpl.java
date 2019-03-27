package testemx.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;

import testemx.autenticacao.BaseAuth;
import testemx.dto.ConsultarProdutoResponseV6;
import testemx.dto.ProdutoV6;
import testemx.service.ProdutoQueryService;

public class ProdutoQueryServiceImpl implements ProdutoQueryService {
	
	private static List<ProdutoV6> produtos;

	@Override
	public List<ProdutoV6> getCadastroProdutos() {
		if(produtos == null) {
			List<ProdutoV6> retorno = null;
			try {
				URL url = new URL("http://servicosflex.rpinfo.com.br:9000/v1.6/produtounidade/listaprodutos/0/unidade/00000000000000");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Cookie", BaseAuth.getCookie());
				
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				Gson g = new Gson();
				ConsultarProdutoResponseV6 response = g.fromJson(content.toString(), ConsultarProdutoResponseV6.class);
				retorno = response.getResponse().getProdutos();
				in.close();
				con.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			produtos = retorno;
		}
		return produtos;
	}

}
