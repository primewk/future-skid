//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.gui.custom;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.gui.custom.guichatutil.MathUtil;
import me.akaishin.cracked.features.future.gui.custom.guichatutil.Timer;
import me.akaishin.cracked.features.future.gui.custom.guichatutil.Util;
import me.akaishin.cracked.features.modules.chat.ChatModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class GuiChat extends GuiNewChat implements Util {
   private static final Logger LOGGER;
   private final Minecraft mc;
   private final Timer messageTimer = new Timer();
   private final List<String> sentMessages = Lists.newArrayList();
   private final List<ChatLine> chatLines = Lists.newArrayList();
   private final List<ChatLine> drawnChatLines = Lists.newArrayList();
   private int scrollPos;
   private boolean isScrolled;
   public static float percentComplete;
   public static int newLines;
   public static long prevMillis;
   public static int messageAdd;
   public boolean configuring;

   public GuiChat(Minecraft mcIn) {
      super(mcIn);
      this.mc = mcIn;
   }

   public static void updatePercentage(long diff) {
      if (percentComplete < 1.0F) {
         percentComplete += 0.004F * (float)diff;
      }

      percentComplete = MathUtil.clamp(percentComplete, 0.0F, 1.0F);
   }

   public void drawChat(int updateCounter) {
      if (!this.configuring) {
         if (prevMillis == -1L) {
            prevMillis = System.currentTimeMillis();
         } else {
            long current = System.currentTimeMillis();
            long diff = current - prevMillis;
            prevMillis = current;
            updatePercentage(diff);
            float t = percentComplete;
            float percent = 1.0F - --t * t * t * t;
            percent = MathUtil.clamp(percent, 0.0F, 1.0F);
            if (this.mc.gameSettings.chatVisibility != EnumChatVisibility.HIDDEN) {
               int i = this.getLineCount();
               int j = this.drawnChatLines.size();
               float f = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;
               if (j > 0) {
                  boolean flag = this.getChatOpen();
                  float f1 = this.getChatScale();
                  GlStateManager.pushMatrix();
                  if (ChatModifier.getInstance().isOn() && (Boolean)ChatModifier.getInstance().smoothChat.getValue() && ChatModifier.getInstance().type.getValue() == ChatModifier.Type.VERTICAL && !this.isScrolled) {
                     GlStateManager.translate(2.0F + ((Double)ChatModifier.getInstance().xOffset.getValue()).floatValue(), 8.0F + ((Double)ChatModifier.getInstance().yOffset.getValue()).floatValue() + (9.0F - 9.0F * percent) * f1, 0.0F);
                  } else {
                     GlStateManager.translate(2.0F + ((Double)ChatModifier.getInstance().xOffset.getValue()).floatValue(), 8.0F + ((Double)ChatModifier.getInstance().yOffset.getValue()).floatValue(), 0.0F);
                  }

                  GlStateManager.scale(f1, f1, 1.0F);
                  int l = 0;

                  int i1;
                  int l2;
                  int l1;
                  for(i1 = 0; i1 + this.scrollPos < this.drawnChatLines.size() && i1 < i; ++i1) {
                     ChatLine chatline = (ChatLine)this.drawnChatLines.get(i1 + this.scrollPos);
                     if (chatline != null && ((l2 = updateCounter - chatline.getUpdatedCounter()) < 200 || flag)) {
                        double d0 = (double)l2 / 200.0D;
                        d0 = 1.0D - d0;
                        d0 *= 10.0D;
                        d0 = MathHelper.clamp(d0, 0.0D, 1.0D);
                        d0 *= d0;
                        l1 = (int)(255.0D * d0);
                        if (flag) {
                           l1 = 255;
                        }

                        l1 = (int)((float)l1 * f);
                        ++l;
                        if (l1 > 3) {
                           boolean i2 = false;
                           int j2 = -i1 * 9;
                           String s = chatline.getChatComponent().getFormattedText();
                           GlStateManager.enableBlend();
                           if ((Boolean)ChatModifier.getInstance().smoothChat.getValue() && i1 <= newLines) {
                              if (this.messageTimer.passedMs((long)((Double)ChatModifier.getInstance().vSpeed.getValue()).intValue()) && messageAdd < 0) {
                                 if ((messageAdd += ((Double)ChatModifier.getInstance().vIncrements.getValue()).intValue()) > 0) {
                                    messageAdd = 0;
                                 }

                                 this.messageTimer.reset();
                              }

                              this.mc.fontRenderer.drawStringWithShadow(s, 0.0F + (float)(ChatModifier.getInstance().type.getValue() == ChatModifier.Type.HORIZONTAL ? messageAdd : 0), (float)(j2 - 8), 16777215 + (l1 << 24));
                           } else {
                              this.mc.fontRenderer.drawStringWithShadow(s, 0.0F + (float)(ChatModifier.getInstance().type.getValue() == ChatModifier.Type.VERTICAL ? messageAdd : 0), (float)(j2 - 8), 16777215 + (l1 << 24));
                           }

                           GlStateManager.disableAlpha();
                           GlStateManager.disableBlend();
                        }
                     }
                  }

                  if (flag) {
                     i1 = this.mc.fontRenderer.FONT_HEIGHT;
                     GlStateManager.translate(-3.0F, 0.0F, 0.0F);
                     l2 = j * i1 + j;
                     int i3 = l * i1 + l;
                     int j3 = this.scrollPos * i3 / j;
                     int k1 = i3 * i3 / l2;
                     if (l2 != i3) {
                        l1 = j3 > 0 ? 170 : 96;
                        int l3 = this.isScrolled ? 13382451 : 3355562;
                        Gui.drawRect(0, -j3, 2, -j3 - k1, l3 + (l1 << 24));
                        Gui.drawRect(2, -j3, 1, -j3 - k1, 13421772 + (l1 << 24));
                     }
                  }

                  GlStateManager.popMatrix();
               }
            }

         }
      }
   }

   public void printChatMessage(ITextComponent chatComponent) {
      this.printChatMessageWithOptionalDeletion(chatComponent, 0);
   }

   public void printChatMessageWithOptionalDeletion(ITextComponent chatComponent, int chatLineId) {
      percentComplete = 0.0F;
      this.setChatLine(chatComponent, chatLineId, this.mc.ingameGUI.getUpdateCounter(), false);
      LOGGER.info("[CHAT] {}", chatComponent.getUnformattedText().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
   }

   private void setChatLine(ITextComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly) {
      messageAdd = -((Double)ChatModifier.getInstance().vLength.getValue()).intValue();
      if (chatLineId != 0) {
         this.deleteChatLine(chatLineId);
      }

      int i = MathHelper.floor((float)this.getChatWidth() / this.getChatScale());
      List<ITextComponent> list = GuiUtilRenderComponents.splitText(chatComponent, i, this.mc.fontRenderer, false, false);
      boolean flag = this.getChatOpen();
      newLines = list.size() - 1;

      ITextComponent itextcomponent;
      for(Iterator var8 = list.iterator(); var8.hasNext(); this.drawnChatLines.add(0, new ChatLine(updateCounter, itextcomponent, chatLineId))) {
         itextcomponent = (ITextComponent)var8.next();
         if (flag && this.scrollPos > 0) {
            this.isScrolled = true;
            this.scroll(1);
         }
      }

      while(this.drawnChatLines.size() > 100) {
         this.drawnChatLines.remove(this.drawnChatLines.size() - 1);
      }

      if (!displayOnly) {
         this.chatLines.add(0, new ChatLine(updateCounter, chatComponent, chatLineId));

         while(this.chatLines.size() > 100) {
            this.chatLines.remove(this.chatLines.size() - 1);
         }
      }

   }

   public void refreshChat() {
      this.drawnChatLines.clear();
      this.resetScroll();

      for(int i = this.chatLines.size() - 1; i >= 0; --i) {
         ChatLine chatline = (ChatLine)this.chatLines.get(i);
         this.setChatLine(chatline.getChatComponent(), chatline.getChatLineID(), chatline.getUpdatedCounter(), true);
      }

   }

   public List<String> getSentMessages() {
      return this.sentMessages;
   }

   public void addToSentMessages(String message) {
      if (this.sentMessages.isEmpty() || !((String)this.sentMessages.get(this.sentMessages.size() - 1)).equals(message)) {
         this.sentMessages.add(message);
      }

   }

   public void resetScroll() {
      this.scrollPos = 0;
      this.isScrolled = false;
   }

   public void scroll(int amount) {
      this.scrollPos += amount;
      int i = this.drawnChatLines.size();
      if (this.scrollPos > i - this.getLineCount()) {
         this.scrollPos = i - this.getLineCount();
      }

      if (this.scrollPos <= 0) {
         this.scrollPos = 0;
         this.isScrolled = false;
      }

   }

   @Nullable
   public ITextComponent getChatComponent(int mouseX, int mouseY) {
      if (!this.getChatOpen()) {
         return null;
      } else {
         ScaledResolution scaledresolution = new ScaledResolution(this.mc);
         int i = scaledresolution.getScaleFactor();
         float f = this.getChatScale();
         int j = mouseX / i - 2 - ((Double)ChatModifier.getInstance().xOffset.getValue()).intValue();
         int k = mouseY / i - 40 + ((Double)ChatModifier.getInstance().yOffset.getValue()).intValue();
         j = MathHelper.floor((float)j / f);
         k = MathHelper.floor((float)k / f);
         if (j >= 0 && k >= 0) {
            int l = Math.min(this.getLineCount(), this.drawnChatLines.size());
            if (j <= MathHelper.floor((float)this.getChatWidth() / this.getChatScale()) && k < this.mc.fontRenderer.FONT_HEIGHT * l + l) {
               int i1 = k / this.mc.fontRenderer.FONT_HEIGHT + this.scrollPos;
               if (i1 >= 0 && i1 < this.drawnChatLines.size()) {
                  ChatLine chatline = (ChatLine)this.drawnChatLines.get(i1);
                  int j1 = 0;
                  Iterator var12 = chatline.getChatComponent().iterator();

                  while(var12.hasNext()) {
                     ITextComponent itextcomponent = (ITextComponent)var12.next();
                     if (itextcomponent instanceof TextComponentString && (j1 += this.mc.fontRenderer.getStringWidth(GuiUtilRenderComponents.removeTextColorsIfConfigured(((TextComponentString)itextcomponent).getText(), false))) > j) {
                        return itextcomponent;
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public boolean getChatOpen() {
      return this.mc.currentScreen instanceof net.minecraft.client.gui.GuiChat;
   }

   public void deleteChatLine(int id) {
      Iterator iterator = this.drawnChatLines.iterator();

      ChatLine chatline1;
      while(iterator.hasNext()) {
         chatline1 = (ChatLine)iterator.next();
         if (chatline1.getChatLineID() == id) {
            iterator.remove();
         }
      }

      iterator = this.chatLines.iterator();

      while(iterator.hasNext()) {
         chatline1 = (ChatLine)iterator.next();
         if (chatline1.getChatLineID() == id) {
            iterator.remove();
            break;
         }
      }

   }

   public int getChatWidth() {
      return calculateChatboxWidth(this.mc.gameSettings.chatWidth);
   }

   public int getChatHeight() {
      return calculateChatboxHeight(this.getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
   }

   public float getChatScale() {
      return this.mc.gameSettings.chatScale;
   }

   public static int calculateChatboxWidth(float scale) {
      return MathHelper.floor(scale * 280.0F + 40.0F);
   }

   public static int calculateChatboxHeight(float scale) {
      return MathHelper.floor(scale * 160.0F + 20.0F);
   }

   public int getLineCount() {
      return this.getChatHeight() / 9;
   }

   static {
      LOGGER = Cracked.LOGGER;
      percentComplete = 0.0F;
      prevMillis = -1L;
   }
}
