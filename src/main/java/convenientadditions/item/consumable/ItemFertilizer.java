package convenientadditions.item.consumable;

import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFertilizer extends CAItem {
    public ItemFertilizer() {
        super(ModConstants.ItemNames.fertilizer);
        this.setCategory(EnumItemCategory.CONSUMABLE);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        ItemStack copy=player.getHeldItem(hand).copy();
        for(int i=0;i<2+world.rand.nextInt(3);i++){
            ItemDye.applyBonemeal(copy,world,pos,player);
        }
        if(copy.getCount()<player.getHeldItem(hand).getCount()){
            player.getHeldItem(hand).shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
