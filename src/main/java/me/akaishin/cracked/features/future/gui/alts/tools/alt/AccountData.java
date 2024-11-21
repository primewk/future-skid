package me.akaishin.cracked.features.future.gui.alts.tools.alt;

import me.akaishin.cracked.features.future.gui.alts.zprestige.iasencrypt.EncryptionTools;

import java.io.Serializable;

public class AccountData implements Serializable {
	public static final long serialVersionUID = 0xF72DEBAC;
	public final String user;
	public final String pass;
	public String alias;

	protected AccountData(String user, String pass, String alias) {
		this.user = EncryptionTools.encode(user);
		this.pass = EncryptionTools.encode(pass);
		this.alias = alias;
	}

	public boolean equalsBasic(Object obj){
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AccountData other = (AccountData) obj;
		return user.equals(other.user);
	}
}
