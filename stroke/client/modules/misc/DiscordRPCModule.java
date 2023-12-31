package net.stroke.client.modules.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class DiscordRPCModule extends BaseModule {
	public DiscordRichPresence presence;
	public DiscordRPC lib;
	
	public DiscordRPCModule() {
		super("DiscordRPC", "Enables Discord Rich Presence", 0x00, ModuleCategory.Misc, false);
	}
	
	public void onEnable() {
	    lib = DiscordRPC.INSTANCE;
	    DiscordEventHandlers handlers = new DiscordEventHandlers();
	    lib.Discord_Initialize("1189215310159560714", handlers, true, "");
	    
	    presence = new DiscordRichPresence();
		presence.startTimestamp = System.currentTimeMillis() / 1000;
		presence.details = "Playing Minecraft 1.12.2";
		presence.state = "Username: " + mc.getSession().getUsername();
		presence.largeImageKey = "icon";
		presence.largeImageText = "[stroke] client";
	    
	    lib.Discord_UpdatePresence(presence);
	    new Thread(() -> {
	        while (!Thread.currentThread().isInterrupted()) {
	            lib.Discord_RunCallbacks();
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException ignored) {}
	        }
	    }, "RPC-Callback-Handler").start();
	}
	
	public void onDisable() {
		lib.Discord_Shutdown();
	}
}
