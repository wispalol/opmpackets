/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2025 wispalol and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package opmpackets.bukkit;

import opmpackets.PacketEvents;
import opmpackets.PacketEventsAPI;
import opmpackets.event.UserLoginEvent;
import opmpackets.protocol.player.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InternalGlobalBukkitListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PacketEventsAPI<?> api = PacketEvents.getAPI();
        User user = api.getPlayerManager().getUser(event.getPlayer());
        PacketEvents.getAPI().getEventManager().callEvent(new UserLoginEvent(user, event.getPlayer()));
    }
}
