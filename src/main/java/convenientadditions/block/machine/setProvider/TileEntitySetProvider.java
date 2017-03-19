package convenientadditions.block.machine.setProvider;

import convenientadditions.api.util.FillSetFilter;
import convenientadditions.api.block.tileentity.IConfigurable;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveOutputOnly;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.HashMap;

public class TileEntitySetProvider extends CATileEntity implements IConfigurable, ITickable {

    public HashMap<EnumFacing, EnumOutletMode> outletSides = new HashMap<EnumFacing, EnumOutletMode>();

    public boolean ignoreDV = false;
    public boolean ignoreNBT = false;
    public byte pushMode=0;//0=pulse,1=high,2=low,3=always
    public boolean ignoreOutput=false;
    public boolean powered;
    public boolean pulseOpen;
    public boolean filteredInput;

    public ItemStackHandlerSPFiltered input;
    public ItemStackHandlerAutoSaveOutputOnly output;
    public ItemStackHandlerAutoSave filter;

    public TileEntitySetProvider() {
        super();
        for (EnumFacing f : EnumFacing.VALUES) {
            outletSides.put(f, EnumOutletMode.disabled);
        }
        this.input = new ItemStackHandlerSPFiltered(this, 18);
        this.output = new ItemStackHandlerAutoSaveOutputOnly(this, 18);
        this.filter = new ItemStackHandlerAutoSave(this, 9);
    }

    @Override
    public void update() {
        if (getWorld().isRemote)
            return;

        if (pushMode == 1 && getWorld().isBlockIndirectlyGettingPowered(getPos()) > 0) {
            tryPush();
        } else if (pushMode == 2 && getWorld().isBlockIndirectlyGettingPowered(getPos()) == 0) {
            tryPush();
        } else if (pushMode == 3 && getWorld().isBlockIndirectlyGettingPowered(getPos()) == 0) {
            tryPush();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        for (EnumFacing f : EnumFacing.VALUES) {
            if (nbt.hasKey("OUTLET_" + f.ordinal())) {
                outletSides.put(f, EnumOutletMode.fromByte(nbt.getByte("OUTLET_" + f.ordinal())));
            }
        }
        if (nbt.hasKey("INPUT"))
            input.deserializeNBT((NBTTagCompound) nbt.getTag("INPUT"));
        if (nbt.hasKey("OUTPUT"))
            output.deserializeNBT((NBTTagCompound) nbt.getTag("OUTPUT"));
        if (nbt.hasKey("FILTER"))
            filter.deserializeNBT((NBTTagCompound) nbt.getTag("FILTER"));
        if (nbt.hasKey("IGNOREDV"))
            ignoreDV = nbt.getBoolean("IGNOREDV");
        if (nbt.hasKey("IGNORENBT"))
            ignoreNBT = nbt.getBoolean("IGNORENBT");
        if (nbt.hasKey("FILTERINPUT"))
            filteredInput = nbt.getBoolean("FILTERINPUT");
        if (nbt.hasKey("PUSHMODE"))
            pushMode = nbt.getByte("PUSHMODE");
        if (nbt.hasKey("IGNOREOUTPUT"))
            ignoreOutput = nbt.getBoolean("IGNOREOUTPUT");
        if (nbt.hasKey("POWERED"))
            powered = nbt.getBoolean("POWERED");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        for (EnumFacing f : EnumFacing.VALUES) {
            nbt.setByte("OUTLET_" + f.ordinal(), (byte) outletSides.get(f).ordinal());
        }
        nbt.setTag("INPUT", input.serializeNBT());
        nbt.setTag("OUTPUT", output.serializeNBT());
        nbt.setTag("FILTER", filter.serializeNBT());
        nbt.setBoolean("IGNOREDV", ignoreDV);
        nbt.setBoolean("IGNORENBT", ignoreNBT);
        nbt.setBoolean("FILTERINPUT", filteredInput);
        nbt.setByte("PUSHMODE", pushMode);
        nbt.setBoolean("IGNOREOUTPUT", ignoreOutput);
        nbt.setBoolean("POWERED", powered);
        return nbt;
    }

    @Override
    public boolean configureSide(EnumFacing f) {
        outletSides.put(f, EnumOutletMode.fromByte((byte) (outletSides.get(f).ordinal() + 1)));
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 0);
        return true;
    }

    public void tryPush() {
        if (slotConfigReady()){
            FillSetFilter FILLter = new FillSetFilter(input.getStacks(), filter.getStacks(), output.getStacks(), ignoreDV, ignoreNBT);
            if (FILLter.isReadyForOutput()) {
                input.setStacks(FILLter.getInput());
                output.setStacks(FILLter.getOutput());
            }
        }
        pulseOpen = false;
    }

    public boolean slotConfigReady() {
        if(!ignoreOutput){
            for (ItemStack s : output.getStacks()) {
                if (!s.isEmpty())
                    return false;
            }
        }
        boolean flag=false;
        for (ItemStack s : input.getStacks()) {
            if (!s.isEmpty())
                flag=true;
        }
        if(!flag)
            return false;
        for (ItemStack s : filter.getStacks()) {
            if (!s.isEmpty())
                return true;
        }
        return false;
    }

    public void setIgnoreDV(boolean ignoreDV) {
        this.ignoreDV = ignoreDV;
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 0);
    }

    public void setIgnoreNBT(boolean ignoreNBT) {
        this.ignoreNBT = ignoreNBT;
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 0);
    }

    public void setFilterInput(boolean filterInput) {
        this.filteredInput = filterInput;
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 0);
    }

    public void setPushMode(byte resetMode) {
        this.pushMode = resetMode;
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 0);
    }

    public void setIgnoreOutput(boolean ignoreOutput) {
        this.ignoreOutput=ignoreOutput;
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 0);
    }

    public void updateRS(boolean power) {
        if (powered != power) {
            if (power && pushMode == 0)
                this.pulseOpen = true;
            else if (!power && pushMode == 0 && pulseOpen) {
                tryPush();
            }
            powered = power;
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && outletSides.get(facing) != EnumOutletMode.disabled) || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && outletSides.get(facing) != EnumOutletMode.disabled) ?
                (outletSides.get(facing) == EnumOutletMode.input ? (T) input : (T) output)
                : super.getCapability(capability, facing);
    }

    public enum EnumOutletMode implements IStringSerializable {
        disabled,
        output,
        input;

        public static EnumOutletMode fromByte(byte bt) {
            return EnumOutletMode.values()[bt % values().length];
        }

        @Override
        public String getName() {
            return this.name();
        }
    }
}
