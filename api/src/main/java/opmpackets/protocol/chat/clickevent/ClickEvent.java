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

package opmpackets.protocol.chat.clickevent;

import opmpackets.protocol.dialog.Dialog;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTEnd;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.adventure.AdventureSupportUtil;
import opmpackets.util.adventure.NbtTagHolder;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.text.event.ClickEvent.Payload;
import org.jspecify.annotations.NullMarked;

import java.util.Locale;

@NullMarked
public interface ClickEvent {

    static ClickEvent decode(NBTCompound compound, PacketWrapper<?> wrapper) {
        String actionName = compound.getStringTagValueOrThrow("action");
        ClickEventAction<?> action = ClickEventActions.getRegistry().getByNameOrThrow(actionName);
        return action.decode(compound, wrapper);
    }

    @SuppressWarnings("unchecked") // not unchecked
    static void encode(NBTCompound compound, PacketWrapper<?> wrapper, ClickEvent clickEvent) {
        compound.set("action", clickEvent.getAction().getName(), ResourceLocation::encode, wrapper);
        ((ClickEventAction<? super ClickEvent>) clickEvent.getAction()).encode(compound, wrapper, clickEvent);
    }

    ClickEventAction<?> getAction();

    static ClickEvent fromAdventure(net.kyori.adventure.text.event.ClickEvent clickEvent) {
        switch (clickEvent.action().name().toLowerCase(Locale.ROOT)) {
            case "open_url":
                return new OpenUrlClickEvent(AdventureSupportUtil.getStringValue(clickEvent));
            case "open_file":
                return new OpenFileClickEvent(AdventureSupportUtil.getStringValue(clickEvent));
            case "run_command":
                return new RunCommandClickEvent(AdventureSupportUtil.getStringValue(clickEvent));
            case "suggest_command":
                return new SuggestCommandClickEvent(AdventureSupportUtil.getStringValue(clickEvent));
            case "change_page":
                return new ChangePageClickEvent(AdventureSupportUtil.getStringValue(clickEvent));
            case "copy_to_clipboard":
                return new CopyToClipboardClickEvent(AdventureSupportUtil.getStringValue(clickEvent));
            case "show_dialog":
                return new ShowDialogClickEvent((Dialog) ((Payload.Dialog) clickEvent.payload()).dialog());
            case "custom":
                Payload.Custom payload = (Payload.Custom) clickEvent.payload();
                NbtTagHolder nbtTag = (NbtTagHolder) payload.nbt();
                return new CustomClickEvent(
                        new ResourceLocation(payload.key()),
                        nbtTag == null || nbtTag.getTag() instanceof NBTEnd ? null : nbtTag.getTag()
                );
            default:
                throw new UnsupportedOperationException("Unsupported clickevent: " + clickEvent);
        }
    }

    net.kyori.adventure.text.event.ClickEvent asAdventure();
}
