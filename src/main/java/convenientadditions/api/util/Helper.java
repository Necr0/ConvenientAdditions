package convenientadditions.api.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Helper {
	public static EntityItem spawnItemInPlace(World w,double x,double y,double z,ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
		e.motionX = 0;
		e.motionY = 0;
		e.motionZ = 0;
		if(!w.isRemote)
			w.spawnEntity(e);
		return e;
	}
	
	public static boolean checkForFire(IBlockAccess world,BlockPos pos){
		for (EnumFacing f:EnumFacing.values()) {
			if(world.getBlockState(pos.offset(f)).getBlock()==Blocks.FIRE)
				return true;
		}
		return false;
	}
	
	public static class FloodFill{
		
		public static List<BlockPos> getAdjacentBlockPosList(BlockPos pos,int mode){
			List<BlockPos> ret=new ArrayList<>();
			
			for(EnumFacing f:EnumFacing.VALUES){
				ret.add(pos.add(f.getDirectionVec()));
			}
			if(mode>0){
				for(EnumFacing f:EnumFacing.HORIZONTALS){
					ret.add(pos.add(EnumFacing.UP.getDirectionVec()).add(f.getDirectionVec()));
					ret.add(pos.add(EnumFacing.DOWN.getDirectionVec()).add(f.getDirectionVec()));
					ret.add(pos.add(f.getDirectionVec()).add(f.rotateY().getDirectionVec()));
				}
			}
			if(mode>1){
				for(EnumFacing f:EnumFacing.HORIZONTALS){
					ret.add(pos.add(EnumFacing.UP.getDirectionVec()).add(f.getDirectionVec()).add(f.rotateY().getDirectionVec()));
					ret.add(pos.add(EnumFacing.DOWN.getDirectionVec()).add(f.getDirectionVec()).add(f.rotateY().getDirectionVec()));
				}
			}
			
			return ret;
		}
		
		public static List<BlockPos> floodFill(World w,BlockPos pos,IBlockState state,int mode){
			return floodFill(w, pos, state, mode, 64, false, true);
		}
		
		public static List<BlockPos> floodFill(World w,BlockPos pos,IBlockState state,int mode,int limit,boolean ignoreMeta,boolean ignoreFirst){
			List<BlockPos> ret=new ArrayList<>();
			if(ignoreFirst){
				for(BlockPos p:getAdjacentBlockPosList(pos, mode)){
					floodFillRecursive(w, p, state, mode, limit, ignoreMeta, ret);
				}
			}
			return ret;
		}
		
		public static void floodFillRecursive(World w,BlockPos pos,IBlockState state,int mode,int limit,boolean ignoreMeta,List<BlockPos> markers){
			if(areBlockStatesEqual(state, w.getBlockState(pos), ignoreMeta)){
				if(!doesContainBlockPos(markers, pos)){
					if(markers.size()<limit||limit==-1){
						markers.add(pos);
						for(BlockPos p:getAdjacentBlockPosList(pos, mode)){
							floodFillRecursive(w, p, state, mode, limit, ignoreMeta, markers);
						}
					}
				}
			}
		}
		
		public static boolean doesContainBlockPos(Collection<BlockPos> c,BlockPos pos){
			for(BlockPos p:c){
				if(areBlockPosEqual(pos, p))
					return true;
			}
			return false;
		}
	}
	
	public static boolean areBlockStatesEqual(IBlockState state1,IBlockState state2, boolean ignoreMeta){
		if(ignoreMeta){
			if(state1.getBlock()==Blocks.REDSTONE_ORE&&state2.getBlock()==Blocks.LIT_REDSTONE_ORE)
				return true;
			else if(state2.getBlock()==Blocks.REDSTONE_ORE&&state1.getBlock()==Blocks.LIT_REDSTONE_ORE)
				return true;
		}
		return ( (ignoreMeta || state1.getBlock().getMetaFromState(state1)==state2.getBlock().getMetaFromState(state2)) && state1.getBlock()==state2.getBlock() );
	}
	
	public static boolean areBlockPosEqual(BlockPos pos1,BlockPos pos2){
		return pos1.getX()==pos2.getX()&&pos1.getY()==pos2.getY()&&pos1.getZ()==pos2.getZ();
	}
	
	public static boolean canEntitySeeSky(Entity e){
		return e.getEntityWorld().canBlockSeeSky(new BlockPos(e));
	}

	@SideOnly(Side.CLIENT)
	public static EntityPlayerSP getClientPlayer(){
		return FMLClientHandler.instance().getClient().player;
	}

	@SideOnly(Side.CLIENT)
	public static WorldClient getClientWorld(){
		return FMLClientHandler.instance().getClient().world;
	}

	@SideOnly(Side.CLIENT)
	public static String localize(String in,Object... replace){
		return I18n.format(in, replace);
	}
	
	public static boolean doesOreDictMatch(IBlockState b,String entry,boolean startsWith){
		if(b.getBlock()==Blocks.LIT_REDSTONE_ORE)
			b=Blocks.REDSTONE_ORE.getDefaultState();
		List<ItemStack> l=new ArrayList<>();
		if(startsWith){
			for(String n:OreDictionary.getOreNames()){
				if(n.startsWith(entry))
					l.addAll(OreDictionary.getOres(n));
			}
		}else{
			if(OreDictionary.doesOreNameExist(entry))
				l.addAll(OreDictionary.getOres(entry));
		}
		for(ItemStack s:l){
			if(s.getItem() instanceof ItemBlock){
				ItemBlock ib=(ItemBlock)s.getItem();
				if(ib.getBlock()==b.getBlock()){
					return true;
				}
			}
		}
		return false;
	}

	public static void insertOrDrop(EntityPlayer p,ItemStack stack){
		if(stack.getCount()==0)
			return;
		World w=p.world;
		if(p.inventory.addItemStackToInventory(stack))
			w.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((w.rand.nextFloat() - w.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		if(!stack.isEmpty()){
			EntityItem e=Helper.spawnItemInPlace(w, p.posX, p.posY, p.posZ, stack);
			e.setOwner(p.getName());
		}
	}
}
