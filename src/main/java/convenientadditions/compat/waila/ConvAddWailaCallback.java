package convenientadditions.compat.waila;

import convenientadditions.block.compostSoil.BlockCompostSoil;
import convenientadditions.block.composter.BlockComposter;
import convenientadditions.block.machine.hoverPad.BlockHoverPad;
import convenientadditions.block.powderkeg.BlockPowderKeg;
import convenientadditions.block.machine.proximitySensor.BlockProximitySensor;
import convenientadditions.compat.waila.provider.*;
import mcp.mobius.waila.api.IWailaRegistrar;

public class ConvAddWailaCallback {

    @SuppressWarnings("unused")
    public static void registerProvider(IWailaRegistrar registrar){
        registrar.registerBodyProvider(new ProviderPowderKeg(), BlockPowderKeg.class);
        registrar.registerBodyProvider(new ProviderComposter(), BlockComposter.class);
        registrar.registerBodyProvider(new ProviderCompostSoil(), BlockCompostSoil.class);
        registrar.registerBodyProvider(new ProviderProximitySensor(), BlockProximitySensor.class);
        registrar.registerBodyProvider(new ProviderHoverPad(), BlockHoverPad.class);
    }
}
