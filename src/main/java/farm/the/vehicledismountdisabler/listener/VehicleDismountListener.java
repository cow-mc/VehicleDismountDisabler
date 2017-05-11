package farm.the.vehicledismountdisabler.listener;

import farm.the.vehicledismountdisabler.VehicleDismountDisabler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class VehicleDismountListener implements Listener {
    @EventHandler
    public void onVehicleDismount(EntityDismountEvent e) {
        if (!e.getDismounted().isDead() && VehicleDismountDisabler.vehicles.contains(e.getDismounted().getType())
                && VehicleDismountDisabler.plugin.getConfig().getStringList("worlds").contains(e.getDismounted().getWorld().getName())) {
            e.getDismounted().addPassenger(e.getEntity());
        }
    }
}
