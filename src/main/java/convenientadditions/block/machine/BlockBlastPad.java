package convenientadditions.block.machine;

import convenientadditions.ModConstants;
import convenientadditions.api.block.IDismantleable;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.block.CABlock;
import convenientadditions.base.item.EnumItemCategory;
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
import net.minecraft.init.SoundEvents;
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
        this.setCategory(EnumItemCategory.MACHINE);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos from) {
        if(state.getValue(READY) && world.isBlockIndirectlyGettingPowered(pos)>0){
            List<EntityLivingBase> l = Helper.getEntitiesInAABBStrict(world, EntityLivingBase.class, new AxisAlignedBB(pos.up()));
            for (EntityLivingBase e : l) {
                e.addVelocity(0,str,0);
                e.velocityChanged=true;
                if (e.motionY > -.666)
                    e.fallDistance = 0;
            }
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, .15f, 1.25f);
            ModNetworking.spawnParticle(world, EnumParticleTypes.EXPLOSION_LARGE,pos.getX()+.5,pos.getY()+1.1,pos.getZ()+.5,1.3,0,0   );
            world.setBlockState(pos, state.withProperty(READY,false),6);
        }else if(!state.getValue(READY) && world.isBlockIndirectlyGettingPowered(pos)==0){
            world.setBlockState(pos, state.withProperty(READY,true),6);
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
