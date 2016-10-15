package convenientadditions.worldgen;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import convenientadditions.ModConstants;
import convenientadditions.block.gateway.TileEntityGateway;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenPortal implements IWorldGenerator {
	ResourceLocation structure=new ResourceLocation(ModConstants.Mod.MODID, "GatewaySmall");

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		if(!(world instanceof WorldServer))
			return;
		WorldServer sWorld = (WorldServer) world;

		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		BlockPos xzPos = new BlockPos(x, 1, z);
		Biome biome = world.getBiomeForCoordsBody(xzPos);
		if(biome != Biomes.OCEAN && biome != Biomes.DEEP_OCEAN && biome != Biomes.RIVER && biome != Biomes.VOID && biome != Biomes.HELL) {
			if(random.nextInt(1) == 0) {
				BlockPos pos = sWorld.getHeight(xzPos);
				tryGeneratePortal(sWorld, pos.add(0,-1,0), random);
			}
		}
	}
	
	public void tryGeneratePortal(WorldServer w,BlockPos p,Random r){
		MinecraftServer server = w.getMinecraftServer();
		Template template = w.getStructureTemplateManager().func_189942_b(server, structure);
		BlockPos size = template.getSize();
		PlacementSettings settings = new PlacementSettings();
		settings.setRotation(Rotation.values()[r.nextInt(Rotation.values().length)]);
		
		for(int x = 0; x < size.getX(); x++)
			for(int y = 0; y < size.getY(); y++)
				for(int z = 0; z < size.getZ(); z++) {
					BlockPos checkPos = p.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
					IBlockState checkState = w.getBlockState(checkPos);
					if(!isBlockReplacable(w, checkPos, checkState))
						return; // Obstructed, can't generate here
				}
		template.addBlocksToWorld(w, p, settings);
		
		Map<BlockPos,String> data=template.getDataBlocks(p, settings);
		for(Map.Entry<BlockPos,String> entry:data.entrySet()){
			if(entry.getValue().equals("GatewayPortal")){
				w.setBlockState(entry.getKey(), ModBlocks.gatewayBlock.getDefaultState());
				((TileEntityGateway)w.getTileEntity(entry.getKey())).setName(w.getBiomeForCoordsBody(entry.getKey()).getBiomeName());
			}
		}
	}
	
	public boolean isBlockReplacable(World w,BlockPos p,IBlockState s){
		if(w.isAirBlock(p))
			return true;
		if(s.getBlock().isReplaceable(w, p))
			return true;
		Block b=s.getBlock();
		Block[] replacable=new Block[]{Blocks.DIRT,Blocks.GRASS,Blocks.STONE,Blocks.SANDSTONE,Blocks.GRAVEL,Blocks.CLAY,Blocks.HARDENED_CLAY,Blocks.PACKED_ICE};
		if(Arrays.asList(replacable).contains(b))
			return true;
		return false;
	}

	public static void init(){
		GameRegistry.registerWorldGenerator(new WorldGenPortal(), 0);
	}
}
