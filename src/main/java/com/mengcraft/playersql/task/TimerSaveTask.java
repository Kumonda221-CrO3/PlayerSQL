package com.mengcraft.playersql.task;

import java.util.UUID;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.mengcraft.playersql.DataCompound;
import com.mengcraft.playersql.Main;
import com.mengcraft.playersql.SyncManager;

public class TimerSaveTask implements Runnable {

    private final Server server;
    private final UUID uuid;
    private final SyncManager manager;

    public TimerSaveTask(Main main, UUID uuid) {
        this.server = main.getServer();
        this.uuid = uuid;
        this.manager = main.manager;
    }

    @Override
    public void run() {
        Player p = server.getPlayer(uuid);
        if (p != null && p.isOnline()) {
            manager.save(p, false);
        } else {
            int id = DataCompound.DEFAULT.task().remove(uuid);
            server.getScheduler().cancelTask(id);
        }
    }

}
