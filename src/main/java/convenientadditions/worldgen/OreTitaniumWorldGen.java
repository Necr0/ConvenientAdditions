package convenientadditions.worldgen;
	
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.Reference;
import cpw.mods.fml.common.IWorldGenerator;

//Took and modified from Fireballs AE2 mod

public class OreTitaniumWorldGen implements IWorldGenerator {
	private final WorldGenMinable ore;

	public OreTitaniumWorldGen()
	{
		final Block ore = ModBlocks.oreTitaniumBlock;

		this.ore = new WorldGenMinable( ore, 0, Reference.titaniumPerCluster, Blocks.stone );
	}

	@Override
	public void generate( final Random r, final int chunkX, final int chunkZ, final World w, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider )
	{
		int seaLevel = w.provider.getAverageGroundLevel() + 1;

		if( seaLevel < 20 )
		{
			final int x = ( chunkX << 4 ) + 8;
			final int z = ( chunkZ << 4 ) + 8;
			seaLevel = w.getHeightValue( x, z );
		}

		final double oreDepthMultiplier = Reference.titaniumOresClusterAmount * seaLevel / 64;
		final int scale = (int) Math.round( r.nextGaussian() * Math.sqrt( oreDepthMultiplier ) + oreDepthMultiplier );

		for( int x = 0; x < ( r.nextBoolean() ? scale * 2 : scale ) / 2; ++x )
		{
			final WorldGenMinable whichOre = this.ore;
			final int cx = chunkX * 16 + r.nextInt( 22 );
			final int cy = r.nextInt( 20 * seaLevel / 64 ) + r.nextInt( 12 * seaLevel / 64 ) + 6 * seaLevel / 64;
			final int cz = chunkZ * 16 + r.nextInt( 22 );
			whichOre.generate( w, r, cx, cy, cz );
		}
	}

}
