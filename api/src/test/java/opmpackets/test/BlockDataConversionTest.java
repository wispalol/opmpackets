package opmpackets.test;

import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.world.states.WrappedBlockState;
import opmpackets.protocol.world.states.type.StateTypes;
import opmpackets.test.base.BaseDummyAPITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockDataConversionTest extends BaseDummyAPITest {

    @Test
    @DisplayName("Test BlockData string conversions")
    public void testMaterialConversions() {
        // this was generated from using: Material.WARPED_DOOR.createBlockData().getAsString(false)
        String warpedDoorString = "minecraft:warped_door[facing=north,half=lower,hinge=left,open=false,powered=false]";
        WrappedBlockState state = WrappedBlockState.getByString(ClientVersion.V_1_20_3, warpedDoorString, false);
        assertEquals(StateTypes.WARPED_DOOR, state.getType());
    }

}
