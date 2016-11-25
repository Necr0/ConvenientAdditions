package convenientadditions.block;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockPlatform extends Block {
    public static AxisAlignedBB AABBPlatform=new AxisAlignedBB(0d,15/16d,0d,1d,1d,1d);

    public BlockPlatform() {
        super(Material.GLASS);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.platformBlockName).setHardness(2F).setResistance(.5F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
        this.setSoundType(SoundType.GLASS);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity ent) {
        if (ent instanceof EntityPlayer){
            if(ent.posY >= (pos.getY() + 1) && !ent.isSneaking())
                addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBPlatform);
        }else{
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBPlatform);
        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABBPlatform;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
