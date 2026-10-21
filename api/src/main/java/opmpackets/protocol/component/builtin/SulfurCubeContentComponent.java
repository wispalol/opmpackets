package opmpackets.protocol.component.builtin;

import opmpackets.protocol.item.ItemStack;
import opmpackets.protocol.item.ItemStackSerialization;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

/**
 * @versions 26.2+
 */
@NullMarked
public class SulfurCubeContentComponent {

    private ItemStack stack;

    public SulfurCubeContentComponent(ItemStack stack) {
        this.stack = stack;
    }

    public static SulfurCubeContentComponent read(PacketWrapper<?> wrapper) {
        ItemStack stack = ItemStackSerialization.readTemplate(wrapper);
        return new SulfurCubeContentComponent(stack);
    }

    public static void write(PacketWrapper<?> wrapper, SulfurCubeContentComponent component) {
        ItemStackSerialization.writeTemplate(wrapper, component.stack);
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        SulfurCubeContentComponent that = (SulfurCubeContentComponent) obj;
        return this.stack.equals(that.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.stack);
    }
}
