package convenientadditions.compat.oneprobe;

import com.google.common.base.Function;
import convenientadditions.ModConstants;
import convenientadditions.compat.oneprobe.provider.ProviderCompostSoil;
import convenientadditions.compat.oneprobe.provider.ProviderComposter;
import convenientadditions.compat.oneprobe.provider.ProviderHoverPad;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ConvAddOneProbePlugin {

    public static void init(){
        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", ModConstants.Compat.TheOneProbe.registerProviderFQCN);
    }

    @Optional.Interface(iface = "java.util.function.Function", modid = "theoneprobe")
    public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {
        @Nullable
        @Override
        public Void apply(ITheOneProbe probe) {
            probe.registerProvider(new ProviderComposter());
            probe.registerProvider(new ProviderCompostSoil());
            probe.registerProvider(new ProviderHoverPad());
            return null;
        }
    }
}
