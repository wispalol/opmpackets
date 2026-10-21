package opmpackets.wrapper.play.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.wrapper.PacketWrapper;

public class WrapperPlayServerSetDisplayChatPreview extends PacketWrapper<WrapperPlayServerSetDisplayChatPreview> {
    private boolean chatPreviewDisplay;

    public WrapperPlayServerSetDisplayChatPreview(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerSetDisplayChatPreview(boolean chatPreviewDisplay) {
        super(PacketType.Play.Server.DISPLAY_CHAT_PREVIEW);
        this.chatPreviewDisplay = chatPreviewDisplay;
    }

    @Override
    public void read() {
        chatPreviewDisplay = readBoolean();
    }

    @Override
    public void write() {
        writeBoolean(chatPreviewDisplay);
    }

    @Override
    public void copy(WrapperPlayServerSetDisplayChatPreview wrapper) {
        chatPreviewDisplay = wrapper.chatPreviewDisplay;
    }

    public boolean isChatPreviewDisplay() {
        return chatPreviewDisplay;
    }

    public void setChatPreviewDisplay(boolean chatPreviewDisplay) {
        this.chatPreviewDisplay = chatPreviewDisplay;
    }
}