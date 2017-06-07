package convenientadditions.block.machine.enderFlux.passiveGenerator;

import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Necro on 6/5/2017.
 */
public class BlockPassiveGenerator extends CABlockMachine {
    public BlockPassiveGenerator() {
        super(ModConstants.BlockNames.passiveGenerator, Material.IRON);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPassiveGenerator();
    }
}
