package convenientadditions.api.capabilities.energy;

/**
 * Created by Necro on 6/4/2017.
 */
public interface IEnderFluxStorage {
        int insertFlux(int amount, boolean simulate);
        int extractFlux(int amount, boolean simulate);
        int getFlux();
        int getFluxCapacity();
        boolean doesAccept();
        boolean doesSupply();
}
