package convenientadditions.api.capabilities.energy;

/**
 * Created by Necro on 6/4/2017.
 */
public interface IEnergyStorageModifiable {
    int setEnergy(int amount);
    void clearEnergy();
}
