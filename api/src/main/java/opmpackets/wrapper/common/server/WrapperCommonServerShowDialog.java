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

package opmpackets.wrapper.common.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.dialog.Dialog;
import opmpackets.protocol.packettype.PacketTypeCommon;
import opmpackets.wrapper.PacketWrapper;

public class WrapperCommonServerShowDialog<T extends WrapperCommonServerShowDialog<T>> extends PacketWrapper<T> {

    protected Dialog dialog;

    public WrapperCommonServerShowDialog(PacketSendEvent event) {
        super(event);
    }

    public WrapperCommonServerShowDialog(PacketTypeCommon packetType, Dialog dialog) {
        super(packetType);
        this.dialog = dialog;
    }

    @Override
    public void read() {
        this.dialog = Dialog.read(this);
    }

    @Override
    public void write() {
        Dialog.write(this, this.dialog);
    }

    @Override
    public void copy(T wrapper) {
        this.dialog = wrapper.getDialog();
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
