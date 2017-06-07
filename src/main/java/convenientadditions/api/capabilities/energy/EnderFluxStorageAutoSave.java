package convenientadditions.api.capabilities.energy;

import convenientadditions.api.capabilities.IAutoSavable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

/**
 * Created by Necro on 6/4/2017.
 */
public class EnderFluxStorageAutoSave extends EnderFluxStorage implements IAutoSavable {
    public TileEntity te;
    public boolean causeUpdate=false;
    public int updateFlag=3;
    public String name="POWER";

    public EnderFluxStorageAutoSave(TileEntity te, int capacity)
    {
        super(capacity);
        this.te=te;
    }

    public EnderFluxStorageAutoSave(TileEntity te, int capacity, boolean doesAccept, boolean doesSupply)
    {
        super(capacity, doesAccept, doesSupply);
        this.te=te;
    }

    public EnderFluxStorageAutoSave setCauseUpdate(boolean causeUpdate) {
        this.causeUpdate = causeUpdate;
        return this;
    }

    public EnderFluxStorageAutoSave setUpdateFlag(int updateFlag) {
        this.updateFlag = updateFlag;
        return this;
    }

    @Override
    protected void onFluxChanged() {
        te.markDirty();
        if(causeUpdate){
            IBlockState state = te.getWorld().getBlockState(te.getPos());
            te.getWorld().notifyBlockUpdate(te.getPos(), state, state, updateFlag);
        }
    }

    public EnderFluxStorageAutoSave setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public void loadFromTagCompound(NBTTagCompound nbt) {
        if(nbt.hasKey(name, Constants.NBT.TAG_COMPOUND))
            deserializeNBT(nbt.getCompoundTag(name));
    }

    @Override
    public void saveToTagCompound(NBTTagCompound nbt) {
        nbt.setTag(name,serializeNBT());
    }
}
