package convenientadditions.api.capabilities.energy;

import convenientadditions.api.capabilities.IAutoSavable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

/**
 * Created by Necro on 6/4/2017.
 */
public class EnergyStorageAutoSave extends EnergyStorage implements IEnergyStorageModifiable, INBTSerializable<NBTTagCompound>, IAutoSavable {
    public TileEntity te;
    public boolean causeUpdate=false;
    public int updateFlag=3;
    public String name="POWER";

    public EnergyStorageAutoSave(TileEntity te, int capacity)
    {
        this(te,capacity,capacity,capacity,0);
    }

    public EnergyStorageAutoSave(TileEntity te, int capacity, int maxTransfer)
    {
        this(te,capacity,maxTransfer,maxTransfer,0);
    }

    public EnergyStorageAutoSave(TileEntity te, int capacity, int maxInput, int maxOutput)
    {
        this(te,capacity,maxInput,maxOutput,0);
    }

    public EnergyStorageAutoSave(TileEntity te, int capacity, int maxInput, int maxOutput, int energy)
    {
        super(capacity,maxInput,maxOutput,energy);
        this.te=te;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public int setEnergy(int amount) {
        int newLevel = Math.max(0,Math.min(amount,capacity));
        if(newLevel!=energy){
            this.energy=newLevel;
            onEnergyChanged();
        }
        return energy;
    }

    @Override
    public void clearEnergy() {
        if(energy!=0){
            this.energy=0;
            onEnergyChanged();
        }
    }

    protected void onEnergyChanged() {
        te.markDirty();
        if(causeUpdate){
            IBlockState state = te.getWorld().getBlockState(te.getPos());
            te.getWorld().notifyBlockUpdate(te.getPos(), state, state, updateFlag);
        }
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt=new NBTTagCompound();
        nbt.setInteger("ENERGY",getEnergyStored());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        setEnergy(nbt.hasKey("ENERGY", Constants.NBT.TAG_INT) ? nbt.getInteger("ENERGY") : 0);
    }

    public EnergyStorageAutoSave setCauseUpdate(boolean causeUpdate) {
        this.causeUpdate = causeUpdate;
        return this;
    }

    public EnergyStorageAutoSave setUpdateFlag(int updateFlag) {
        this.updateFlag = updateFlag;
        return this;
    }

    public EnergyStorageAutoSave setName(String name) {
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
