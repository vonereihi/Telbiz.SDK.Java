package com.telbiz.model;

public class TokenEndPointRequest {
	public String getSecret() {
		return Secret;
	}
	public void setSecret(String secret) {
		Secret = secret;
	}
	public String getGrantType() {
		return GrantType;
	}
	public void setGrantType(String grantType) {
		GrantType = grantType;
	}
	public String getScope() {
		return Scope;
	}
	public void setScope(String scope) {
		Scope = scope;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRedirectUri() {
		return RedirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		RedirectUri = redirectUri;
	}
	public String getClientID() {
		return ClientID;
	}
	public void setClientID(String clientID) {
		ClientID = clientID;
	}
	public String RedirectUri;
	public String ClientID;
	public String Secret;
    public String GrantType;
	public String Scope;
	public String Username;
	public String Password;
}
