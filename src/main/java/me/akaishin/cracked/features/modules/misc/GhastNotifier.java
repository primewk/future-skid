//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package me.akaishin.cracked.features.modules.misc;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import me.akaishin.cracked.features.command.Command;
/*    */ import me.akaishin.cracked.features.modules.Module;
/*    */ import me.akaishin.cracked.features.setting.Setting;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GhastNotifier
/*    */   extends Module
/*    */ {
/* 16 */   private final Set<Entity> ghasts = new HashSet<>();
/* 17 */   public Setting<Boolean> Chat = register(new Setting("Chat", Boolean.valueOf(true)));
/* 18 */   public Setting<Boolean> Sound = register(new Setting("Sound", Boolean.valueOf(true)));
/*    */ 
/*    */   
/*    */   public GhastNotifier() {
/* 22 */     super("GhastNotifier", "Helps you find ghasts", Module.Category.CHAT, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 28 */     this.ghasts.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 34 */     for (Entity entity : mc.world.getLoadedEntityList()) {
/* 35 */       if (!(entity instanceof net.minecraft.entity.monster.EntityGhast) || this.ghasts.contains(entity))
/* 36 */         continue;  if (((Boolean)this.Chat.getValue()).booleanValue()) {
/* 37 */         Command.sendMessage("Ghast Detected at: " + entity.getPosition().getX() + "x, " + entity.getPosition().getY() + "y, " + entity.getPosition().getZ() + "z.");
/*    */       }
/* 39 */       this.ghasts.add(entity);
/* 40 */       if (!((Boolean)this.Sound.getValue()).booleanValue())
/* 41 */         continue;  mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }