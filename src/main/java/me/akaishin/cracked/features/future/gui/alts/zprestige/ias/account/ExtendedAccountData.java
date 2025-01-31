package me.akaishin.cracked.features.future.gui.alts.zprestige.ias.account;

import me.akaishin.cracked.features.future.gui.alts.tools.alt.AccountData;
import me.akaishin.cracked.features.future.gui.alts.zprestige.ias.enums.EnumBool;
import me.akaishin.cracked.features.future.gui.alts.zprestige.ias.tools.JavaTools;

import java.util.Arrays;

public class ExtendedAccountData extends AccountData {
	private static final long serialVersionUID = -909128662161235160L;

	public EnumBool premium;
	public int[] lastused;
	public int useCount;

	public ExtendedAccountData(String user, String pass, String alias) {
		super(user, pass, alias);
		useCount = 0;
		lastused = JavaTools.getJavaCompat().getDate();
		premium = EnumBool.UNKNOWN;
	}

	public ExtendedAccountData(String user, String pass, String alias, int useCount, int[] lastused, EnumBool premium) {
		super(user, pass, alias);
		this.useCount = useCount;
		this.lastused = lastused;
		this.premium = premium;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ExtendedAccountData other = (ExtendedAccountData) obj;
		if (!Arrays.equals(lastused, other.lastused)) {
			return false;
		}
		if (premium != other.premium) {
			return false;
		}
		if (useCount != other.useCount) {
			return false;
		}
		return user.equals(other.user) && pass.equals(other.pass);
	}
}
