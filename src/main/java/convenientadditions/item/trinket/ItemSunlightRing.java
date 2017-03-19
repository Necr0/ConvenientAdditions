package convenientadditions.item.trinket;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemSunlightRing extends CAItem implements IBauble {

    public ItemSunlightRing() {
        super(ModConstants.ItemNames.sunlightRing);
        this.setDefaultJoke(true).setMaxStackSize(1);
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote)
            return;
        World world = player.getEntityWorld();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                for (int z = 0; z < 9; z++) {
                    if(world.rand.nextBoolean()) {
                        BlockPos pos = new BlockPos(x - 4 + (int) player.posX, y - 4 + (int) player.posY, z - 4 + (int) player.posZ);
                        IBlockState state = world.getBlockState(pos);
                        Block b = state.getBlock();
                        if (b.isAir(state, world, pos) && b != ModBlocks.tempLightBlock && b != ModBlocks.phantomPlatformBlock) {
                            world.setBlockState(pos, ModBlocks.tempLightBlock.getDefaultState(), 3);
                            world.scheduleBlockUpdate(pos, ModBlocks.tempLightBlock, 80 + world.rand.nextInt(140), 0);
                        }
                    }
                }
            }
        }
    }
}
