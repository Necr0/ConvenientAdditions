package convenientadditions.api.capabilities.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by Necro on 6/4/2017.
 */
public class EnderFluxStorage implements IEnderFluxStorage, IEnderFluxStorageModifiable, INBTSerializable<NBTTagCompound> {
    protected int flux;
    protected int capacity;
    protected boolean doesAccept;
    protected boolean doesSupply;

    public EnderFluxStorage(int capacity)
    {
        this(capacity,true,true);
    }

    public EnderFluxStorage(int capacity, boolean doesAccept, boolean doesSupply)
    {
        this.capacity = capacity;
        this.doesAccept = doesAccept;
        this.doesSupply = doesSupply;
    }

    @Override
    public int insertFlux(int amount, boolean simulate)
    {
        int newLevel = flux+amount;
        int overflow = capacity<newLevel?newLevel-capacity:0;
        if (!simulate)
            flux=Math.min(newLevel,capacity);
        if(overflow!=amount)
            onFluxChanged();
        return overflow;
    }

    @Override
    public int extractFlux(int amount, boolean simulate)
    {
        int newLevel = flux-amount;
        int underflow = 0>newLevel?-newLevel:0;
        if (!simulate)
            flux=Math.max(newLevel,0);
        if(underflow!=amount)
            onFluxChanged();
        return amount-underflow;
    }

    @Override
    public int getFlux(){return flux;}

    @Override
    public int getFluxCapacity()
    {
        return capacity;
    }

    @Override
    public boolean doesAccept() {
        return doesAccept;
    }

    @Override
    public boolean doesSupply() {
        return false;
    }

    @Override
    public int setFlux(int amount) {
        int newLevel = Math.max(0,Math.min(amount,capacity));
        if(newLevel!=flux){
            this.flux=newLevel;
            onFluxChanged();
        }
        return flux;
    }

    @Override
    public void clearFlux() {
        if(flux!=0){
            this.flux=0;
            onFluxChanged();
        }
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt=new NBTTagCompound();
        nbt.setInteger("Flux",getFlux());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        setFlux(nbt.hasKey("Flux", Constants.NBT.TAG_INT) ? nbt.getInteger("Flux") : 0);
    }

    protected void onFluxChanged()
    {

    }
}
