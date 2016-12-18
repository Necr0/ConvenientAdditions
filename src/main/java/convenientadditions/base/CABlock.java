package convenientadditions.base;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class CABlock extends Block {
    public CABlock(String name,Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.add(StringHelper.getInfo(stack));
    }
}
