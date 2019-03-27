package testemx.dto;

import java.util.List;

public class Response {
	
	private String response;
	
	private String message;
	
	private List<Message> messages;
	
	private List<ProdutoV6> produtos;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<ProdutoV6> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoV6> produtos) {
		this.produtos = produtos;
	}

}
