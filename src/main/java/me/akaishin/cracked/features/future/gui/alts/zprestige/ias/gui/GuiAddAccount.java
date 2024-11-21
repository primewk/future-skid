package me.akaishin.cracked.features.future.gui.alts.zprestige.ias.gui;

import me.akaishin.cracked.features.future.gui.alts.tools.alt.AltDatabase;
import me.akaishin.cracked.features.future.gui.alts.zprestige.ias.account.ExtendedAccountData;

public class GuiAddAccount extends AbstractAccountGui {

	public GuiAddAccount()
	{
		super("Add account");
	}

	@Override
	public void complete()
	{
		AltDatabase.getInstance().getAlts().add(new ExtendedAccountData(getUsername(), getPassword(), getUsername()));
	}
}
