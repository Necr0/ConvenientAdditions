package convenientadditions.block.machine;

import convenientadditions.ModConstants;
import convenientadditions.api.block.IDismantleable;
import convenientadditions.base.CABlock;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import convenientadditions.init.ModNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BlockBlastPad extends CABlock implements IDismantleable {
    public static PropertyBool READY = PropertyBool.create("ready");

    public BlockBlastPad() {
        super(ModConstants.BlockNames.blastPad,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(READY,true));
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos from) {
        float str= EntityLaunchingArrow.EnumLaunchingArrowVariant.slime.strength*(float)Math.log(worldIn.isBlockIndirectlyGettingPowered(pos)+1)*.25f;
        if(state.getValue(READY) && worldIn.isBlockIndirectlyGettingPowered(pos)>0){
            List<EntityLivingBase> l = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.up()));
            for (EntityLivingBase e : l) {
                e.addVelocity(0,str,0);
                e.velocityChanged=true;
                if (e.motionY > -.666)
                    e.fallDistance = 0;
            }
            worldIn.playSound(null, pos, SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.explode")), SoundCategory.BLOCKS, .15f, 1.25f);
            ModNetworking.spawnParticle(worldIn, EnumParticleTypes.EXPLOSION_LARGE,pos.getX()+.5,pos.getY()+1.1,pos.getZ()+.5,1.3,0,0   );
            worldIn.setBlockState(pos, state.withProperty(READY,false),6);
        }else if(!state.getValue(READY).booleanValue() && worldIn.isBlockIndirectlyGettingPowered(pos)==0){
            worldIn.setBlockState(pos, state.withProperty(READY,true),6);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(READY, meta==0);
    }

    @Override
    public int getMetaFromState(IBlockState state){return state.getValue(READY)?0:1; }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, READY);
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, BlockPos pos) {
        return true;
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        ItemStack stack = new ItemStack(this);
        world.spawnEntity(new EntityItem(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, stack));
        NonNullList<ItemStack> arr = NonNullList.create();
        arr.add(stack);
        world.setBlockToAir(pos);
        return arr;
    }
}
