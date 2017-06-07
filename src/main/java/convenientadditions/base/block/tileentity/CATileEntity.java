package convenientadditions.base.block.tileentity;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import convenientadditions.api.capabilities.IAutoSavable;
import convenientadditions.api.capabilities.energy.IEnderFluxStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CATileEntity extends TileEntity {
    @CapabilityInject(IItemHandler.class)
    public static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
    @CapabilityInject(IFluidHandler.class)
    public static Capability<IFluidHandler> FLUID_HANDLER_CAPABILITY = null;
    @CapabilityInject(IEnergyStorage.class)
    public static Capability<IEnergyStorage> ENERGY = null;
    @CapabilityInject(IEnderFluxStorage.class)
    public static Capability<IEnderFluxStorage> ENDER_FLUX=null;

    public List<IAutoSavable> autoSavables = new ArrayList<>();
    public Table<Capability,Optional<EnumFacing>,Object> mapped_capabilities = HashBasedTable.create();

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = super.getUpdateTag();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
        IBlockState state = getWorld().getBlockState(pos);
        getWorld().notifyBlockUpdate(pos, state, state, 3);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        for(IAutoSavable autoSavable:autoSavables){
            autoSavable.loadFromTagCompound(nbt);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        for(IAutoSavable autoSavable:autoSavables){
            autoSavable.saveToTagCompound(nbt);
        }
        return nbt;
    }

    public <T extends IAutoSavable> T addAutoSavable(T autoSavable){
        autoSavables.add(autoSavable);
        return autoSavable;
    }

    public void causeUpdate(int flag){
        markDirty();
        getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), flag);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return mapped_capabilities.contains(capability,Optional.ofNullable(facing))||super.hasCapability(capability,facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(mapped_capabilities.contains(capability,Optional.ofNullable(facing)))
            return (T) mapped_capabilities.get(capability,Optional.ofNullable(facing));
        return super.getCapability(capability,facing);
    }

    public <T> T addCapability(Capability<T> capability, T implementation, EnumFacing... faces){
        if(faces.length!=0){
            for(EnumFacing f:faces){
                if(!mapped_capabilities.contains(capability,f)){
                    mapped_capabilities.put(capability,Optional.ofNullable(f),implementation);
                }
            }
        }else{
            for(EnumFacing f:EnumFacing.VALUES){
                if(!mapped_capabilities.contains(capability,f)){
                    mapped_capabilities.put(capability,Optional.of(f),implementation);
                }
            }
            mapped_capabilities.put(capability,Optional.empty(),implementation);
        }
        return implementation;
    }

    public <T> T addCapability(T implementation, EnumFacing... faces) {
        if(implementation instanceof IItemHandler)
            addCapability(ITEM_HANDLER_CAPABILITY,(IItemHandler) implementation, faces);
        else if(implementation instanceof IFluidHandler)
            addCapability(FLUID_HANDLER_CAPABILITY,(IFluidHandler) implementation, faces);
        else if(implementation instanceof IEnergyStorage)
            addCapability(ENERGY,(IEnergyStorage) implementation, faces);
        else if(implementation instanceof IEnderFluxStorage)
            addCapability(ENDER_FLUX,(IEnderFluxStorage) implementation, faces);
        return implementation;
    }
}
