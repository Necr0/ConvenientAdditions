package convenientadditions.compat.waila;

import convenientadditions.ModConstants;
import convenientadditions.block.compostSoil.BlockCompostSoil;
import convenientadditions.block.composter.BlockComposter;
import convenientadditions.block.hoverPad.BlockHoverPad;
import convenientadditions.block.powderkeg.BlockPowderKeg;
import convenientadditions.block.proximitySensor.BlockProximitySensor;
import convenientadditions.compat.waila.provider.*;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class ConvAddWailaPlugin {

    public static void init(){
        FMLInterModComms.sendMessage("Waila", "register", ModConstants.Compat.Waila.registerProviderFQCN);
    }

    @SuppressWarnings("unused")
    public static void registerProvider(IWailaRegistrar registrar){
        registrar.registerBodyProvider(new ProviderPowderKeg(), BlockPowderKeg.class);
        registrar.registerBodyProvider(new ProviderComposter(), BlockComposter.class);
        registrar.registerBodyProvider(new ProviderCompostSoil(), BlockCompostSoil.class);
        registrar.registerBodyProvider(new ProviderProximitySensor(), BlockProximitySensor.class);
        registrar.registerBodyProvider(new ProviderHoverPad(), BlockHoverPad.class);
    }

}
