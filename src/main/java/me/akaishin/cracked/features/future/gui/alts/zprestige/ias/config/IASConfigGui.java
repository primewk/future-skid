package me.akaishin.cracked.features.future.gui.alts.zprestige.ias.config;

import me.akaishin.cracked.features.future.gui.alts.zprestige.ias.IAS;
import me.akaishin.cracked.features.future.gui.alts.zprestige.ias.tools.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class IASConfigGui extends GuiConfig {

	public IASConfigGui(GuiScreen parentScreen) {
		super(parentScreen, new ConfigElement(IAS.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MODID, false, false, GuiConfig.getAbridgedConfigPath(IAS.config.toString()));
	}

}
