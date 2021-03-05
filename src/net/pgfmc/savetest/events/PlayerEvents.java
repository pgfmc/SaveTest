package net.pgfmc.savetest.events;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.pgfmc.savetest.Main;

public class PlayerEvents implements Listener {
	
	@EventHandler
	public void onBreak(PlayerInteractEvent e) // When a player interacts with a block
	{
		
		if (!e.getAction().equals(Action.LEFT_CLICK_BLOCK)) { return; } // Kick us out if the player isn't breaking a block
		
		UUID uuid = e.getPlayer().getUniqueId(); // Gets the UUID of the player
		
		Main.blBr.put(uuid.toString(), e.getPlayer().getLocation()); // Adds a new set to blBr <Player's UUID, Player's Location> --> If the key (UUID) is already present, it overwrites the value of the key, no duplicates
		
	}

}
