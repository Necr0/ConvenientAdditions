package convenientadditions.block.machine.enderFlux.passiveGenerator;

import convenientadditions.api.capabilities.energy.EnderFluxStorageAutoSave;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.DimensionType;

/**
 * Created by Necro on 6/5/2017.
 */
public class TileEntityPassiveGenerator extends CATileEntity implements ITickable{
    public EnderFluxStorageAutoSave energy = addCapability(addAutoSavable(new EnderFluxStorageAutoSave(this,50000,false,true)));

    @Override
    public void update() {
        energy.insertFlux((int)(getEnergyGeneration()*getEndMultiplier()),false);
        System.out.println(energy.getFlux());
    }

    public int getEnergyGeneration(){
        return 48;
    }

    public float getEndMultiplier(){
        return getWorld().provider.getDimensionType() == DimensionType.THE_END ? 1.5f : 1f;
    }
}
