package opmpackets.wrapper.common.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.packettype.PacketTypeCommon;
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.PacketWrapper;

public abstract class WrapperCommonServerPluginMessage<T extends WrapperCommonServerPluginMessage<T>> extends PacketWrapper<T> {

    protected String channelName;
    protected byte[] data;

    protected WrapperCommonServerPluginMessage(PacketSendEvent event) {
        super(event);
    }

    protected WrapperCommonServerPluginMessage(PacketTypeCommon packetType) {
        super(packetType);
    }

    protected WrapperCommonServerPluginMessage(PacketTypeCommon packetType, ResourceLocation channelName, byte[] data) {
        this(packetType, channelName.toString(), data);
    }

    protected WrapperCommonServerPluginMessage(PacketTypeCommon packetType, String channelName, byte[] data) {
        super(packetType);
        this.channelName = channelName;
        this.data = data;
    }

    @Override
    public void copy(T wrapper) {
        this.channelName = wrapper.channelName;
        this.data = wrapper.data;
    }

    /**
     * The channel name of the plugin message.
     * @return The channel name.
     */
    public final String getChannelName() {
        return channelName;
    }

    /**
     * Sets the channel name of the plugin message.
     * @param channelName The channel name.
     */
    public final void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * The data of the plugin message.
     *
     * @return The data.
     */
    public final byte[] getData() {
        return data;
    }

    /**
     * Sets the data of the plugin message.
     *
     * @param data The data.
     */
    public final void setData(byte[] data) {
        this.data = data;
    }

}
