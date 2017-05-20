package convenientadditions.compat.oneprobe.provider;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.block.misc.compostSoil.BlockCompostSoil;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ProviderCompostSoil implements IProbeInfoProvider {

    @Override
    public String getID() {
        return ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.compostSoil;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        if(!(blockState.getBlock() instanceof BlockCompostSoil))
            return;
        probeInfo.text(Helper.localize("waila.convenientadditions:compostSoil.degradation", blockState.getValue(BlockCompostSoil.DEGRADATION)));
    }
}
