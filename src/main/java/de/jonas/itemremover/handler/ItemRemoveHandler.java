package de.jonas.itemremover.handler;

import de.jonas.ItemRemover;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe des {@link ItemRemoveHandler} lassen sich alle Items, die herumliegen, in allen Welten, die in der Config
 * hinterlegt sind, entfernen.
 */
public final class ItemRemoveHandler {

    //<editor-fold desc="utility">

    /**
     * Entfernt alle Items, die herumliegen, in allen Welten, die in der Config hinterlegt sind.
     */
    public static void removeItems() {
        for (@NotNull final World world : ItemRemover.getInstance().getWorlds()) {
            for (@NotNull final Entity entity : world.getEntities()) {
                if (entity.getType() != EntityType.DROPPED_ITEM) continue;

                entity.remove();
            }
        }
    }
    //</editor-fold>

}
