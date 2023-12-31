package net.stroke.client.modules.combat;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AntiKnockback extends BaseModule {
	public static AntiKnockback instance;
	
	public AntiKnockback() {
		super("AntiKnockBack", "Disables knockback", 0x00, ModuleCategory.Combat, false);
		instance = this;
		// EDITS: net.minecraft.network.play.server.SPacketEntityVelocity
	}
}
