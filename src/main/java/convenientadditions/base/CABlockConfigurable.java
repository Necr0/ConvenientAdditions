package convenientadditions.base;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.api.block.BlockConfigurable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class CABlockConfigurable extends BlockConfigurable {
    public CABlockConfigurable(String name, Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.add(StringHelper.getInfo(stack));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
