package dev.freeserver.utils.autoSelfTransfer;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public final class AutoSelfTransfer extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("AutoSelfTransfer plugin is enabled.");
    }

    @Override
    public void onDisable() {
        ProxyServer.getInstance().getPlayers().forEach(player -> {
            try {
            String playerConnectIP = player.getPendingConnection().getVirtualHost().getHostName();
            int playerConnectPort = player.getPendingConnection().getVirtualHost().getPort();

                player.transfer(playerConnectIP, playerConnectPort);
                getLogger().info("Player " + player.getName() + " has been transferred to IP: " + playerConnectIP + ", Port: " + playerConnectPort);
            } catch (Exception e) {
                if (e instanceof IllegalStateException) {
                    getLogger().warning("Player " + player.getName() + " is using unsupported version of Minecraft transfer (lower 1.20.5). Transfer skipped, the player will be kicked.");
                }
            }
        });
    }
}
