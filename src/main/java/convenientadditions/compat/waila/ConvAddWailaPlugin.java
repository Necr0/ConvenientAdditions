package convenientadditions.compat.waila;

import convenientadditions.ModConstants;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ConvAddWailaPlugin {

    public static void init(){
        FMLInterModComms.sendMessage("Waila", "register", ModConstants.Compat.Waila.registerProviderFQCN);
    }

}
