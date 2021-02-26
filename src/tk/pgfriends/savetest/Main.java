package tk.pgfriends.savetest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import tk.pgfriends.savetest.events.PlayerEvents;

public class Main extends JavaPlugin {
	
	public static HashMap<String, Location> blBr = new HashMap<String, Location>(); // For orginization. String is UUID.toString()
	
	@Override
	public void onEnable() // When the plugin turns on
	{
		File file = new File(getDataFolder() + File.separator + "database.yml"); // Creates a new file in file path (where the plugin is at, so the plugin folder)/SaveTest/database.yml
		FileConfiguration database = YamlConfiguration.loadConfiguration(file); // Creates a YAML file type from File and loads it in the plugin
		
		if (!file.exists()) // If the file doesn't exist, create a new one
		{
			try {
				file.createNewFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (file.exists()) // This will be used to load in data in the future, not completely necessary to load in the whole file unless you want to
		{
			// get the variables from database.yml here, look up how
		}
		
		
		
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this); // Registers PlayerEvents class
	}
	
	@Override
	public void onDisable() // When the plugin turns off
	{
		
		File file = new File(getDataFolder() + File.separator + "database.yml"); // Creates a new file in file path (where the plugin is at, so the plugin folder)/SaveTest/database.yml
		FileConfiguration database = YamlConfiguration.loadConfiguration(file); // Creates a YAML file type from File and loads it in the plugin
		
		for (String uuid : blBr.keySet()) // For every key in blBr<String, Location>
		{
			database.set("blockBroken.playerData." + uuid, blBr.get(uuid).toString()); // Add to the database.yml file the UUID in String form and the Location in String form under the UUID
		}
		
		
		
		
		try {
			database.save(file); // Tries to save the file

		} catch (IOException e) {
			e.printStackTrace(); // Doesn't completely crash the plugin if the above fails
		}
	}
}