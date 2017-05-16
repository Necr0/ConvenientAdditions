package convenientadditions.api.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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
import net.minecraft.nbt.NBTTagCompound;
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
import java.util.List;
import java.util.Map;

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

	public static EntityItem spawnItem(World w,double x,double y,double z,ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
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

	public static boolean areBlockStatesEqual(IBlockState state1,IBlockState state2, boolean ignoreMeta){
		if(ignoreMeta){
			if(state1.getBlock()==Blocks.REDSTONE_ORE&&state2.getBlock()==Blocks.LIT_REDSTONE_ORE)
				return true;
			else if(state2.getBlock()==Blocks.REDSTONE_ORE&&state1.getBlock()==Blocks.LIT_REDSTONE_ORE)
				return true;
		}
		return ( (ignoreMeta && state1.getBlock()==state2.getBlock()) || state1==state2 );
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

	public static String getOreDictMatch(IBlockState b,String entry,boolean startsWith){
		if(b.getBlock()==Blocks.LIT_REDSTONE_ORE)
			b=Blocks.REDSTONE_ORE.getDefaultState();
		Multimap<String,ItemStack> m=ArrayListMultimap.create();
		if(startsWith){
			for(String n:OreDictionary.getOreNames()){
				if(n.startsWith(entry))
					m.putAll(n,OreDictionary.getOres(n));
			}
		}else{
			if(OreDictionary.doesOreNameExist(entry))
				m.putAll(entry,OreDictionary.getOres(entry));
		}
		for(Map.Entry<String,ItemStack> e:m.entries()){
			if(e.getValue().getItem() instanceof ItemBlock){
				ItemBlock ib=(ItemBlock)e.getValue().getItem();
				if(ib.getBlock()==b.getBlock()){
					return e.getKey();
				}
			}
		}
		return null;
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

	public static NBTTagCompound getPersistentTag(EntityPlayer p,String modid){
		NBTTagCompound tag = p.getEntityData();
		if(!tag.hasKey(p.PERSISTED_NBT_TAG))
			tag.setTag(p.PERSISTED_NBT_TAG,new NBTTagCompound());
		NBTTagCompound pers = tag.getCompoundTag(p.PERSISTED_NBT_TAG);
		if(!pers.hasKey(modid))
			pers.setTag(modid,new NBTTagCompound());
		return pers.getCompoundTag(modid);
	}
}
