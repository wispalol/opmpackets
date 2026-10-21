package opmpackets.wrapper.common.client;

import opmpackets.event.PacketReceiveEvent;
import opmpackets.protocol.packettype.PacketTypeCommon;
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.PacketWrapper;

public abstract class WrapperCommonClientPluginMessage<T extends WrapperCommonClientPluginMessage<T>> extends PacketWrapper<T> {

    protected String channelName;
    protected byte[] data;

    protected WrapperCommonClientPluginMessage(PacketReceiveEvent event) {
        super(event);
    }

    protected WrapperCommonClientPluginMessage(PacketTypeCommon packetType) {
        super(packetType);
    }

    protected WrapperCommonClientPluginMessage(PacketTypeCommon packetType, ResourceLocation channelName, byte[] data) {
        this(packetType, channelName.toString(), data);
    }

    protected WrapperCommonClientPluginMessage(PacketTypeCommon packetType, String channelName, byte[] data) {
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
