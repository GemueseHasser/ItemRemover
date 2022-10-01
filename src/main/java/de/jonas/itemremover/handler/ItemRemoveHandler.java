package de.jonas.itemremover.handler;

import de.jonas.ItemRemover;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe des {@link ItemRemoveHandler} lassen sich alle Items, die herumliegen, in allen Welten, die in der Config
 * hinterlegt sind, entfernen.
 */
public final class ItemRemoveHandler {

    //<editor-fold desc="utility">

    /**
     * Entfernt alle Items, die herumliegen, in allen Welten, die in der Config hinterlegt sind.
     *
     * @return Die Anzahl an entfernten Items.
     */
    public static int removeItems() {
        int itemCount = 0;

        for (@NotNull final World world : ItemRemover.getInstance().getWorlds()) {
            for (@NotNull final Entity entity : world.getEntities()) {
                if (entity.getType() != EntityType.DROPPED_ITEM) continue;

                // get item
                final Item item = (Item) entity;

                // increment item-count
                itemCount += item.getItemStack().getAmount();

                // remove entity
                entity.remove();
            }
        }

        return itemCount;
    }
    //</editor-fold>

}
