package pl.gozdek.gwtProj.server;

import java.util.Date;
import java.util.HashMap;

import pl.gozdek.gwtProj.client.AuthService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthServiceImpl  extends RemoteServiceServlet implements
AuthService {
	private static final long serialVersionUID = -2065412172405526426L;
	
	public AuthServiceImpl() {}
	
	class TokenData {
		public String login;
		public String pass;
		public Date expires;
		public TokenData(String l, String p) {
			login = l;
			pass = p;
			expires = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2); // 2h
		}
	}
	static HashMap<String,TokenData> tokens = new HashMap<String,TokenData>();
	
	@Override
	public String isValidUserPassword(String login, String password) {
		if (login.compareTo(password) == 0) {
			String newtokenname = String.valueOf(Math.random()) + login.hashCode() + password.hashCode();
			tokens.put(newtokenname, new TokenData(login, password));
			return newtokenname;
		}
		return "";
	}

	@Override
	public boolean isTokenValid(String token) {
		if (tokens.get(token) != null) {
			TokenData td =tokens.get(token);
			if (td.expires.after(new Date(System.currentTimeMillis()))) {
				return true;
			} else {
				tokens.remove(token);
				return false;
			}
		}
		return false;
	}
	public static boolean isTokenValidStatic(String token) {
		if (tokens.get(token) != null) {
			TokenData td =tokens.get(token);
			if (td.expires.after(new Date(System.currentTimeMillis()))) {
				return true;
			} else {
				tokens.remove(token);
				return false;
			}
		}
		return false;
	}

}
