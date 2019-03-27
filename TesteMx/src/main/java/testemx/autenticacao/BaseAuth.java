package testemx.autenticacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import testemx.dto.UsuarioDTO;

public class BaseAuth {
	
	private static String cookie = null;
	
	public static void autenticar() {
		if(cookie != null) {
			return;
		}
		UsuarioDTO user = new UsuarioDTO();
		user.setUsuario("100000");
		user.setSenha("123456");
   	 
    	URL url;
		try {
			url = new URL("http://servicosflex.rpinfo.com.br:9000/v1.0/auth");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			
			Gson g = new Gson();
			con.getOutputStream().write(g.toJson(user).getBytes("UTF8"));
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			con.getHeaderFields().entrySet().stream()
			.filter(entry -> entry.getKey() != null)
			.forEach(entry -> {
				if(entry.getKey().equals("Set-Cookie")) {
					for (Object value : entry.getValue()) {
						if(value.toString().startsWith("PLAY_SESSION")) {
							cookie = value.toString();
						}
					}
				}
			});
			
			in.close();
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCookie() {
		return cookie;
	}

}
