package de.jonas;

import de.jonas.itemremover.task.BossBarTask;
import de.jonas.itemremover.task.ItemRemoveTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Die Haupt- und Main-Klasse dieses Plugins, durch die das gesamte Plugin initialisiert wird. Der
 * {@link ItemRemover} ist die Klasse, in der alle Unterinstanzen registriert werden.</p>
 *
 * <p>Der {@link ItemRemover} entfernt periodisch alle Items, die in in der Config aufgeführten Welten herumliegen.</p>
 */
public final class ItemRemover extends JavaPlugin {

    //<editor-fold desc="STATIC FIELDS">
    /** Die Instanz, womit man auf dieses Plugin zugreifen kann. */
    @Getter
    private static ItemRemover instance;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Eine Liste aller Welten, die durch diesen {@link ItemRemover} beeinflusst werden sollen. */
    @Getter
    @NotNull
    private final List<World> worlds = new ArrayList<>();
    /** Die Dauer in Sekunden, die die Anzahl an entfernten Items in der BossBar angezeigt werden soll. */
    @Getter
    private int removedDisplayTime;
    //</editor-fold>


    //<editor-fold desc="setup and start">
    @Override
    public void onEnable() {
        super.onEnable();

        // initialize plugin instance
        instance = this;

        // load config
        getConfig().options().copyDefaults(true);
        saveConfig();

        // load config values
        final int removePeriod = getConfig().getInt("removePeriodMinutes");
        this.removedDisplayTime = getConfig().getInt("removedDisplayTimeSeconds");

        for (@NotNull final String worldName : getConfig().getStringList("worlds")) {
            final World world = Bukkit.getWorld(worldName);

            if (world == null) {
                getLogger().info("Es gibt keine Welt mit dem Namen " + worldName + ".");
                continue;
            }

            this.worlds.add(world);
        }

        // schedule periodic item removing
        new ItemRemoveTask().runTaskTimer(
            this,
            0,
            (long) removePeriod * 20 * 60
        );

        getLogger().info("Das Plugin wurde erfolgreich aktiviert!");
    }
    //</editor-fold>

    //<editor-fold desc="shutdown">
    @Override
    public void onDisable() {
        super.onDisable();

        // remove boss bar
        final BossBar bossBar = Bukkit.getBossBar(BossBarTask.BOSS_BAR_KEY);

        assert bossBar != null;
        bossBar.removeAll();
        bossBar.setVisible(false);

        getLogger().info("Das Plugin wurde deaktiviert.");
    }
    //</editor-fold>
}
