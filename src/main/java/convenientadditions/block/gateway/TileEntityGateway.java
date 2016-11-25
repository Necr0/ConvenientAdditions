package convenientadditions.block.gateway;

import convenientadditions.base.TileEntityCABase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityGateway extends TileEntityCABase implements ITickable {
    public String name;
    public BlockPos destination;

    public TileEntityGateway() {
        name = "";
        destination = null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("NAME") && nbt.getTag("NAME") instanceof NBTTagString)
            name = nbt.getString("NAME");
        if (nbt.hasKey("DESTINATION") && nbt.getTag("DESTINATION") instanceof NBTTagIntArray) {
            int[] coords = nbt.getIntArray("DESTINATION");
            destination = (coords.length == 0) ? null : new BlockPos(coords[0], coords[1], coords[2]);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("NAME", name);
        nbt.setIntArray("DESTINATION", (destination != null) ? new int[]{destination.getX(), destination.getY(), destination.getZ()} : new int[0]);
        return nbt;
    }

    @Override
    public void update() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDestination(BlockPos destination) {
        this.destination = destination;
    }
}
