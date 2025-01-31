//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.guinew.gui.effect.Particle;

import net.minecraft.client.gui.*;

import javax.vecmath.*;

import me.akaishin.cracked.features.modules.client.Particles;
import me.akaishin.cracked.util.rendergui.*;

public final class ParticleSystem
{
    private final int PARTS = 200;
    private final Particle[] particles;
    private ScaledResolution scaledResolution;
    
    public ParticleSystem(final ScaledResolution scaledResolution) {
        this.particles = new Particle[200];
        this.scaledResolution = scaledResolution;
        for (int i = 0; i < 200; ++i) {
            this.particles[i] = new Particle(new Vector2f((float)(Math.random() * scaledResolution.getScaledWidth()), (float)(Math.random() * scaledResolution.getScaledHeight())));
        }
    }
    
    public void update() {
        for (int i = 0; i < 200; ++i) {
            final Particle particle = this.particles[i];
            if (this.scaledResolution != null) {
                final boolean isOffScreenX = particle.getPos().x > this.scaledResolution.getScaledWidth() || particle.getPos().x < 0.0f;
                final boolean isOffScreenY = particle.getPos().y > this.scaledResolution.getScaledHeight() || particle.getPos().y < 0.0f;
                if (isOffScreenX || isOffScreenY) {
                    particle.respawn(this.scaledResolution);
                }
            }
            particle.update();
        }
    }
    
    public void render(final int mouseX, final int mouseY) {
        if (!(boolean)Particles.getInstance().isEnabled()) {
            return;
        }
        for (int i = 0; i < 200; ++i) {
            final Particle particle = this.particles[i];
            for (int j = 1; j < 200; ++j) {
                if (i != j) {
                    final Particle otherParticle = this.particles[j];
                    final Vector2f diffPos = new Vector2f(particle.getPos());
                    diffPos.sub((Tuple2f)otherParticle.getPos());
                    final float diff = diffPos.length();
                    final int distance = Particles.getInstance().particleLength.getValue() / ((this.scaledResolution.getScaleFactor() <= 1) ? 3 : this.scaledResolution.getScaleFactor());
                    if (diff < distance) {
                        final int lineAlpha = (int)map(diff, distance, 0.0, 0.0, 127.0);
                        if (lineAlpha > 8) {
                            RenderUtil.drawLine(particle.getPos().x + particle.getSize() / 2.0f, particle.getPos().y + particle.getSize() / 2.0f, otherParticle.getPos().x + otherParticle.getSize() / 2.0f, otherParticle.getPos().y + otherParticle.getSize() / 2.0f, 1.0f, Particle.changeAlpha(ColorUtil.toRGBA(Particles.getInstance().particlered.getValue(), Particles.getInstance().particlegreen.getValue(), Particles.getInstance().particleblue.getValue()), lineAlpha));
                        }
                    }
                }
            }
            particle.render(mouseX, mouseY);
        }
    }
    
    public static double map(double value, final double a, final double b, final double c, final double d) {
        value = (value - a) / (b - a);
        return c + value * (d - c);
    }
    
    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
    
    public void setScaledResolution(final ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
}
