package convenientadditions.block.misc.seedbox;

import convenientadditions.api.block.tileentity.IConfigurable;
import convenientadditions.api.util.MathHelper;
import convenientadditions.base.block.tileentity.CATileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileEntitySeedBox extends CATileEntity implements IConfigurable {

    public HashMap<EnumFacing, Boolean> outletSides = new HashMap<>();

    SeedBoxItemStackHandler stackHandler = new SeedBoxItemStackHandler(this);

    public TileEntitySeedBox() {
        super();
        for (EnumFacing f : EnumFacing.VALUES) {
            outletSides.put(f, (f == EnumFacing.DOWN));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("OUTLET")) {
            byte in = nbt.getByte("OUTLET");
            MathHelper.Bitmask mask = new MathHelper.Bitmask(in);
            for (EnumFacing f : EnumFacing.VALUES) {
                outletSides.put(f, mask.getBit(f.ordinal()));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        MathHelper.Bitmask mask = new MathHelper.Bitmask(0);
        for (EnumFacing f : EnumFacing.VALUES) {
            mask.setBit(f.ordinal(), outletSides.get(f));
        }
        nbt.setByte("OUTLET", (byte) mask.get());
        return nbt;
    }

    @Override
    public boolean configureSide(EnumFacing f) {
        this.outletSides.put(f, !outletSides.get(f));
        this.markDirty();
        IBlockState state = getWorld().getBlockState(pos);
        getWorld().notifyBlockUpdate(pos, state, state, 3);
        return true;
    }

    public boolean isOutput(EnumFacing f) {
        return this.outletSides.get(f);
    }

    public boolean canOutput(EnumFacing f) {
        BlockPos posF = new BlockPos(pos.getX() + f.getFrontOffsetX(), pos.getY() + f.getFrontOffsetY(), pos.getZ() + f.getFrontOffsetZ());
        return isOutput(f) &&
                !getWorld().getBlockState(posF).isSideSolid(getWorld(), posF, f.getOpposite());
    }

    public List<EnumFacing> getValidOutputDirections() {
        ArrayList<EnumFacing> ret = new ArrayList<>();
        for (EnumFacing f : EnumFacing.VALUES) {
            if (canOutput(f))
                ret.add(f);
        }
        return ret;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == ITEM_HANDLER_CAPABILITY && !isOutput(facing) || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == ITEM_HANDLER_CAPABILITY && !isOutput(facing) ? (T) stackHandler : super.getCapability(capability, facing);
    }
}
