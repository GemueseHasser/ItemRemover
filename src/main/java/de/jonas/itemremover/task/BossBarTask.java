package de.jonas.itemremover.task;

import de.jonas.ItemRemover;
import de.jonas.itemremover.handler.ItemRemoveHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

/**
 * Ein {@link BossBarTask} stellt eine sich konstant wiederholende Prozedur dar, in der von {@code BOSSBAR_DURATION}
 * runtergezählt wird dann alle Items entfernt werden.
 */
@NotNull
public final class BossBarTask extends BukkitRunnable {

    //<editor-fold desc="CONSTANTS">
    /** Der {@link NamespacedKey} womit sich die {@link BossBar} identifizieren lässt. */
    @NotNull
    public static final NamespacedKey BOSS_BAR_KEY = new NamespacedKey(ItemRemover.getInstance(), "bossBar");
    /** Die {@link BossBar}, der jeder Spieler des Netzwerks hinzugefügt wird, bevor alle Items entfernt werden. */
    @NotNull
    private static final BossBar BOSS_BAR = Bukkit.createBossBar(BOSS_BAR_KEY, "", BarColor.PINK, BarStyle.SOLID);
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der Counter bis zum Entfernen aller Items. */
    private int count = ItemRemoveTask.BOSSBAR_DURATION;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void run() {
        // check if count is 0
        if (count <= 0) {
            BOSS_BAR.removeAll();
            ItemRemoveHandler.removeItems();
            this.cancel();
            return;
        }

        // remove all player
        BOSS_BAR.removeAll();

        // add all player
        for (@NotNull final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            BOSS_BAR.addPlayer(onlinePlayer);
        }

        // update boss-bar and counter
        BOSS_BAR.setTitle(
            ChatColor.GRAY + "Item-Clear in " + ChatColor.LIGHT_PURPLE + count + ChatColor.GRAY + " Sekunden"
        );
        BOSS_BAR.setProgress((1.0 / ItemRemoveTask.BOSSBAR_DURATION) * this.count);

        count--;
    }
    //</editor-fold>

}
