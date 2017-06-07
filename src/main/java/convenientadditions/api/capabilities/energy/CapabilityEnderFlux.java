package convenientadditions.api.capabilities.energy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by Necro on 6/4/2017.
 */
public class CapabilityEnderFlux {
    private static boolean registered=false;

    @CapabilityInject(IEnderFluxStorage.class)
    public static Capability<IEnderFluxStorage> INSTANCE=null;

    public static void register(){
        if(registered)
            return;
        CapabilityManager.INSTANCE.register(IEnderFluxStorage.class, new Storage(), () -> new EnderFluxStorage(1000));
        registered=true;
    }

    static class Storage implements Capability.IStorage<IEnderFluxStorage>{
        @Override
        public NBTBase writeNBT(Capability<IEnderFluxStorage> capability, IEnderFluxStorage instance, EnumFacing side)
        {
            if (!(instance instanceof EnderFluxStorage))
                throw new IllegalArgumentException("Can not serialize to an instance that isn't the default implementation");
            return ((EnderFluxStorage)instance).serializeNBT();
        }

        @Override
        public void readNBT(Capability<IEnderFluxStorage> capability, IEnderFluxStorage instance, EnumFacing side, NBTBase nbt)
        {
            if (!(instance instanceof EnderFluxStorage))
                throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
            ((EnderFluxStorage)instance).deserializeNBT((NBTTagCompound)nbt);
        }
    }
}
