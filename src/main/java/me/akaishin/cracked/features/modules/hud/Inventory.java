//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.hud;

import net.minecraft.util.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;

import me.akaishin.cracked.event.events.*;
import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.setting.*;
import me.akaishin.cracked.util.inventory.InventoryRender;
import net.minecraft.inventory.*;   
import net.minecraft.client.renderer.*;

public class Inventory extends Module
{
    private static final ResourceLocation box;
    public Setting<Boolean> inventory;
    public Setting<Integer> invX;
    public Setting<Integer> invY;
    public Setting<Integer> fineinvX;
    public Setting<Integer> fineinvY;
    public Setting<Boolean> renderXCarry;
    public Setting<Integer> invH;
    
    public Inventory() {
        super("Inventory", "", Category.HUD, true, false, false);
        this.inventory = (Setting<Boolean>)this.register(new Setting("Inventory", false));
        this.invX = (Setting<Integer>)this.register(new Setting("InvX", 564, 0, 1000, v -> this.inventory.getValue()));
        this.invY = (Setting<Integer>)this.register(new Setting("InvY", 467, 0, 1000, v -> this.inventory.getValue()));
        this.fineinvX = (Setting<Integer>)this.register(new Setting("InvFineX", 0, v -> this.inventory.getValue()));
        this.fineinvY = (Setting<Integer>)this.register(new Setting("InvFineY", 0, v -> this.inventory.getValue()));
        this.renderXCarry = (Setting<Boolean>)this.register(new Setting("RenderXCarry", Boolean.FALSE, v -> this.inventory.getValue()));
        this.invH = (Setting<Integer>)this.register(new Setting("InvH", 3, v -> this.inventory.getValue()));
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (fullNullCheck()) {
            return;
        }
        if (this.inventory.getValue()) {
            this.renderInventory();
        }
    }
    
    public void renderInventory() {
        this.boxrender(this.invX.getValue() + this.fineinvX.getValue(), this.invY.getValue() + this.fineinvY.getValue());
        this.itemrender((NonNullList<ItemStack>)Inventory.mc.player.inventory.mainInventory, this.invX.getValue() + this.fineinvX.getValue(), this.invY.getValue() + this.fineinvY.getValue());
    }
    
    private static void preboxrender() {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        GlStateManager.enableBlend();
        GlStateManager.color(255.0f, 255.0f, 255.0f, 255.0f);
    }
    
    private void boxrender(final int x, final int y) {
        preboxrender();
        Inventory.mc.renderEngine.bindTexture(Inventory.box);
        InventoryRender.drawTexturedRect(x, y, 0, 0, 176, 16, 500);
        InventoryRender.drawTexturedRect(x, y + 16, 0, 16, 176, 54 + this.invH.getValue(), 500);
        InventoryRender.drawTexturedRect(x, y + 16 + 54, 0, 160, 176, 8, 500);
        postboxrender();
    }
    
    private static void postboxrender() {
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
    }
    
    private void itemrender(final NonNullList<ItemStack> items, final int x, final int y) {
        for (int i = 0; i < items.size() - 9; ++i) {
            final int iX = x + i % 9 * 18 + 8;
            final int iY = y + i / 9 * 18 + 18;
            final ItemStack itemStack = (ItemStack)items.get(i + 9);
            preitemrender();
            Inventory.mc.getRenderItem().zLevel = 501.0f;
            InventoryRender.itemRender.renderItemAndEffectIntoGUI(itemStack, iX, iY);
            InventoryRender.itemRender.renderItemOverlayIntoGUI(Inventory.mc.fontRenderer, itemStack, iX, iY, (String)null);
            Inventory.mc.getRenderItem().zLevel = 0.0f;
            postitemrender();
        }
        if (this.renderXCarry.getValue()) {
            for (int i = 1; i < 5; ++i) {
                final int iX = x + (i + 4) % 9 * 18 + 8;
                final ItemStack itemStack2 = Inventory.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (!itemStack2.isEmpty) {
                    preitemrender();
                    Inventory.mc.getRenderItem().zLevel = 501.0f;
                    InventoryRender.itemRender.renderItemAndEffectIntoGUI(itemStack2, iX, y + 1);
                    InventoryRender.itemRender.renderItemOverlayIntoGUI(Inventory.mc.fontRenderer, itemStack2, iX, y + 1, (String)null);
                    Inventory.mc.getRenderItem().zLevel = 0.0f;
                    postitemrender();
                }
            }
        }
    }
    
    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
    }
    
    private static void postitemrender() {
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
    }
}
