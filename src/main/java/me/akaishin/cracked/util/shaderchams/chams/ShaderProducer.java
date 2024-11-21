package me.akaishin.cracked.util.shaderchams.chams;

import me.akaishin.cracked.util.shaderchams.chams.FramebufferShader;

@FunctionalInterface
public interface ShaderProducer {
    FramebufferShader INSTANCE();
}

