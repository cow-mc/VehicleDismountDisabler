package farm.the.vehicledismountdisabler;

import farm.the.vehicledismountdisabler.listener.VehicleDismountListener;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class VehicleDismountDisabler extends JavaPlugin {
    public static VehicleDismountDisabler plugin;
    public static Logger log;
    public static Set<EntityType> vehicles;

    @Override
    public void onEnable() {
        plugin = this;
        log = getLogger();
        loadConfig();

        getServer().getPluginManager().registerEvents(new VehicleDismountListener(), this);
    }

    @Override
    public void onDisable() {
    }

    private void loadConfig() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            log.info("config.yml not found, creating!");
            saveDefaultConfig();
        }

        vehicles = new HashSet<>();
        for (String entity : getConfig().getStringList("vehicles")) {
            try {
                EntityType entityType = EntityType.valueOf(entity);
                vehicles.add(entityType);
            } catch (IllegalArgumentException e) {
                log.severe("Ignoring invalid vehicle type in config.yml: " + entity);
            }
        }
    }
}
