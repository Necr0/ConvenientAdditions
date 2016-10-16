package conveniencecore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.oredict.OreDictionary;

public class Helper {
	public static void spawnItemInPlace(World w,double x,double y,double z,ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
		e.motionX = 0;
		e.motionY = 0;
		e.motionZ = 0;
		if(!w.isRemote)
			w.spawnEntityInWorld(e);
	}
	
	public static boolean checkForFire(IBlockAccess world,BlockPos pos){
		int x=pos.getX(),y=pos.getY(),z=pos.getZ();
    	return world.getBlockState(new BlockPos(x+1, y, z)).getBlock()==Blocks.FIRE||
    			world.getBlockState(new BlockPos(x, y+1, z)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x, y, z+1)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x-1, y, z)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x, y-1, z)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x, y, z-1)).getBlock()==Blocks.FIRE;
    }
	
	public static class FloodFill{
		
		public static List<BlockPos> getAdjacentBlockPosList(BlockPos pos,int mode){
			List<BlockPos> ret=new ArrayList<BlockPos>();
			
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
			List<BlockPos> ret=new ArrayList<BlockPos>();
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
		return ( (ignoreMeta?true:state1.getBlock().getMetaFromState(state1)==state2.getBlock().getMetaFromState(state2)) && state1.getBlock()==state2.getBlock() );
	}
	
	public static boolean areBlockPosEqual(BlockPos pos1,BlockPos pos2){
		return pos1.getX()==pos2.getX()&&pos1.getY()==pos2.getY()&&pos1.getZ()==pos2.getZ();
	}
	
	public static boolean canEntitySeeSky(Entity e){
		return e.worldObj.canBlockSeeSky(new BlockPos(MathHelper.floor_double(e.posX), MathHelper.floor_double(e.posY), MathHelper.floor_double(e.posZ)));
	}
	
	public static EntityPlayer getClientPlayer(){
		return FMLClientHandler.instance().getClient().thePlayer;
	}
	
	public static World getClientWorld(){
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	public static String localize(String in,String... replace){
		String tmp=I18n.format(in, new Object[0]);
		if(tmp.startsWith("Format error: "))
			tmp=tmp.replaceFirst("Format error: ", "");
		String match = null;
		for(String s:replace){
			if(match==null){
				match=s;
			}else{
				tmp=tmp.replace(match, s);
				match=null;
			}
		}
		return tmp;
	}
	
	public static boolean doesOreDictMatch(IBlockState b,String entry,boolean startsWith){
		if(b.getBlock()==Blocks.LIT_REDSTONE_ORE)
			b=Blocks.REDSTONE_ORE.getDefaultState();
		List<ItemStack> l=new ArrayList<ItemStack>();
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
}
