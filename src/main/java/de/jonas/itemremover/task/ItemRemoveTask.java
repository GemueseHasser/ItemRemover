package de.jonas.itemremover.task;

import de.jonas.ItemRemover;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

/**
 * Ein {@link ItemRemoveTask} stellt eine sich konstant wiederholende Prozedur dar, in der in einem gewissen Abstand,
 * der in der Config hinterlegt ist, der {@link BossBarTask} initialisiert wird.
 */
@NotNull
public final class ItemRemoveTask extends BukkitRunnable {

    //<editor-fold desc="CONSTANTS">
    /** Die Zeit in Sekunden, die jeder Spieler vorgewarnt werden soll, dass alle Items entfernt werden. */
    public static final int BOSSBAR_DURATION = 30;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void run() {
        new BossBarTask().runTaskTimer(ItemRemover.getInstance(), 0, 20);
    }
    //</editor-fold>

}
