package convenientadditions.base.block;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class CABlockContainer extends CABlock {

    public CABlockContainer(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(ConvenientAdditions.CREATIVETAB);
    }

    public CABlockContainer(String name, Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(name);
    }

    @Override
    public boolean hasTileEntity(IBlockState state){ return true; }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(World world, IBlockState state);
}
