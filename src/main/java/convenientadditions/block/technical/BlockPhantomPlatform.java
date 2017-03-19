package convenientadditions.block.technical;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.block.platform.BlockPlatform;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockPhantomPlatform extends Block {
    public static final PropertyBool DESPAWN = PropertyBool.create("despawn");

    public BlockPhantomPlatform() {
        super(MaterialNotQuiteAir.NOT_QUITE_AIR);
        this.setRegistryName(ModConstants.BlockNames.phantomPlatform).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.phantomPlatform);
        this.disableStats();
        this.translucent = true;
        this.setDefaultState(this.blockState.getBaseState().withProperty(DESPAWN, true));
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if (!Helper.getClientPlayer().getHeldItemOffhand().isEmpty())
            if (Helper.getClientPlayer().getHeldItemOffhand().getItem() == ItemBlock.getItemFromBlock(state.getBlock()))
                world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, 0, 0, 0);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity ent, boolean dontknow) {
        if (ent instanceof EntityPlayer && ent.posY >= (pos.getY() + 1) && !ent.isSneaking())
            addCollisionBoxToList(pos, entityBox, collidingBoxes, BlockPlatform.AABBPlatform);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random r) {
        if (state.getValue(DESPAWN)) {
            boolean prevent = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2)).size() > 0;
            if (prevent)
                world.scheduleUpdate(pos, this, 1);
            else
                world.setBlockToAir(pos);
        }
    }

    @Override
    public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
    }

    //
    // BLOCKSTATE STUFF
    //
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(DESPAWN, meta == 0);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(DESPAWN) ? 0 : 1;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,DESPAWN);
    }
}
