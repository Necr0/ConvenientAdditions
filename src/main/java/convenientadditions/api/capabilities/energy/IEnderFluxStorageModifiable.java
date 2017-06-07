package convenientadditions.api.capabilities.energy;

/**
 * Created by Necro on 6/4/2017.
 */
public interface IEnderFluxStorageModifiable extends IEnderFluxStorage {
    int setFlux(int amount);
    void clearFlux();
}
