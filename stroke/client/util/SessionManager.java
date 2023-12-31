package net.stroke.client.util;

import java.util.UUID;

import com.mojang.authlib.Agent;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionManager {
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static UserAuthentication auth;
	
	public static Session session;
	
	public SessionManager() {
		String uuid = UUID.randomUUID().toString();
		
		AuthenticationService authService = new YggdrasilAuthenticationService(mc.getProxy(), uuid);
		auth = authService.createUserAuthentication(Agent.MINECRAFT);
		authService.createMinecraftSessionService();
	}
	
	public static void setUsername(String username) {
		session = new Session(username, username, "0", "legacy");
		mc.session = session;
	}
}
