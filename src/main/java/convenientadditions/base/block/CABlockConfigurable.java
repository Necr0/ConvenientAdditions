package convenientadditions.base.block;

import convenientadditions.StringHelper;
import convenientadditions.api.block.tileentity.IConfigurable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class CABlockConfigurable extends CABlockContainer {

    public CABlockConfigurable(String name, Material materialIn) {
        super(name,materialIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.add(StringHelper.getInfo(stack));
    }

    @Override
    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing axis) {
        TileEntity te = worldObj.getTileEntity(pos);
        if (te != null && te instanceof IConfigurable) {
            return ((IConfigurable) te).configureSide(axis);
        }
        return super.rotateBlock(worldObj, pos, axis);
    }
}
