package convenientadditions.worldgen;
	
import java.util.Random;

import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

//Taken and modified from Fireball's AE2 mod

public class OreTitaniumWorldGen implements IWorldGenerator {
	private final WorldGenMinable ore;

	public OreTitaniumWorldGen()
	{
		final IBlockState ores = ModBlocks.oreTitaniumBlock.getDefaultState();

		this.ore = new WorldGenMinable( ores, ModConfig.titanium_oresPerCluster );
	}

	@Override
	public void generate( final Random r, final int chunkX, final int chunkZ, final World w, final IChunkGenerator chunkGenerator, final IChunkProvider chunkProvider )
	{
		int seaLevel = w.provider.getAverageGroundLevel() + 1;

		if( seaLevel < 20 )
		{
			final int x = ( chunkX << 4 ) + 8;
			final int z = ( chunkZ << 4 ) + 8;
			seaLevel = w.getChunkFromChunkCoords(x, z).getLowestHeight();
		}

		final double oreDepthMultiplier = ModConfig.titanium_clusterFrequency * seaLevel / 64;
		final int scale = (int) Math.round( r.nextGaussian() * Math.sqrt( oreDepthMultiplier ) + oreDepthMultiplier );

		for( int x = 0; x < ( r.nextBoolean() ? scale * 2 : scale ) / 2; ++x )
		{
			final WorldGenMinable whichOre = this.ore;
			final int cx = chunkX * 16 + r.nextInt( 22 );
			final int cy = r.nextInt( 20 * seaLevel / 64 ) + r.nextInt( 12 * seaLevel / 64 ) + 6 * seaLevel / 64;
			final int cz = chunkZ * 16 + r.nextInt( 22 );
			whichOre.generate( w, r, new BlockPos(cx,cy,cz) );
		}
	}

}
