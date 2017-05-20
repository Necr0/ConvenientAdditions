package convenientadditions.compat.oneprobe.provider;


import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.block.misc.composter.BlockComposter;
import convenientadditions.block.misc.composter.TileEntityComposter;
import convenientadditions.config.ModConfigMisc;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ProviderComposter implements IProbeInfoProvider {

    @Override
    public String getID() {
        return ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.composter;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        if(!(blockState.getBlock() instanceof BlockComposter))
            return;
        TileEntity te=world.getTileEntity(data.getPos());
        if(te!=null&&te instanceof TileEntityComposter){
            probeInfo.text(Helper.localize("waila." + ModConstants.Mod.MODID + ":compostingMass",((TileEntityComposter)te).content, ModConfigMisc.composter_capacity, ((TileEntityComposter)te).getContentCapacityPercentage()));
        }
    }
}
