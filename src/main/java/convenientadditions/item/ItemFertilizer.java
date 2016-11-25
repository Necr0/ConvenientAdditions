package convenientadditions.item;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemFertilizer extends Item {
    public ItemFertilizer() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.fertilizerItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        ItemStack copy=itemStack.copy();
        for(int i=0;i<2+world.rand.nextInt(3);i++){
            ItemDye.applyBonemeal(copy,world,pos,player);
        }
        if(copy.stackSize<itemStack.stackSize){
            itemStack.stackSize--;
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.fertilizerItemName));
    }
}
