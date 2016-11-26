package convenientadditions.block;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BlockTreeBox extends Block {

    public BlockTreeBox() {
        super(Material.WOOD);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.treeBoxBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){
        worldIn.scheduleUpdate(pos, this, 1);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random){}

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random r) {
        Iterator<BlockPos> iter=BlockPos.getAllInBox(pos.add(-8,-2,-8),pos.add(8,12,8)).iterator();
        while(iter.hasNext()){
            BlockPos p=iter.next();

            IBlockState s=world.getBlockState(p);
            if(s.getBlock() instanceof BlockLeaves && Helper.canDecay(world,p,s)){
                List<ItemStack> l=s.getBlock().getDrops(world,p,s,1);
                world.setBlockToAir(p);
                for (ItemStack i:l) {
                    if(i.getItem() == Items.APPLE){
                        if(r.nextInt(50)==0){
                            i=new ItemStack(Items.GOLDEN_APPLE,1);
                        }else if(r.nextBoolean()){
                            i.shrink(1);
                        }
                    }
                    Helper.spawnItemInPlace(world,pos.getX()+.5,pos.getY()+1.5,pos.getZ()+.5,i);
                }
                break;
            }
        }
        world.scheduleUpdate(pos, this, 1);
    }
}
