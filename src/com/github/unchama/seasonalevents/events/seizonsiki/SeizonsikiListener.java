package com.github.unchama.seasonalevents.events.seizonsiki;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.unchama.seasonalevents.SeasonalEvents;

import net.md_5.bungee.api.ChatColor;

public class SeizonsikiListener implements Listener {
	private Seizonsiki parent;

	public SeizonsikiListener(Seizonsiki parent) {
		this.parent = parent;
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().getType().equals(EntityType.ZOMBIE) &&
				(event.getEntity().getKiller() != null)) {
			parent.killEvent(event.getEntity().getKiller(), event.getEntity().getLocation());
		}
	}

	@EventHandler
	public void onplayerJoinEvent(PlayerJoinEvent event) {
		if (parent.isdrop) {
			event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + Seizonsiki.DROPDAYDISP + "までの期間限定で、シーズナルイベント『チャラゾンビたちの成ゾン式！』を開催しています。");
			event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "詳しくは下記wikiをご覧ください。");
			event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + SeasonalEvents.config.getWikiAddr());
		}
	}

	@EventHandler
	public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event) {
		if (parent.checkPrize(event.getItem())) {
			parent.usePrize(event.getPlayer());
		}
	}
}
