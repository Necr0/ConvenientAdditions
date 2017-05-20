package convenientadditions.compat.oneprobe.provider;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.block.machine.hoverPad.BlockHoverPad;
import convenientadditions.block.machine.hoverPad.TileEntityHoverPad;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ProviderHoverPad implements IProbeInfoProvider {

    @Override
    public String getID() {
        return ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.hoverPad;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        if(!(blockState.getBlock() instanceof BlockHoverPad))
            return;
        TileEntity te=world.getTileEntity(data.getPos());
        if(te!=null&&te instanceof TileEntityHoverPad){
            probeInfo.text(Helper.localize("waila." + ModConstants.Mod.MODID + ":powerLevel",world.isBlockIndirectlyGettingPowered(data.getPos())));
        }
    }

}
