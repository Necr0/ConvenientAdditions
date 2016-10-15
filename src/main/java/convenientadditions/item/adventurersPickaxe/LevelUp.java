package convenientadditions.item.adventurersPickaxe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import conveniencecore.util.Helper;
import convenientadditions.ModConstants;
import convenientadditions.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextComponentString;

public class LevelUp {
    
    public static int getXPRequiredForLvlUp(int current){
    	return (int)(13*Math.pow(1.13,current));
    }
    
    public static void gainXP(EntityPlayer p,ItemStack s,int amount){
    	ModItems.itemAdventurersPickaxe.setToolProperty(s, "xp", (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "xp")+amount);
    	int lvl=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "lvl");
    	int xp=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "xp");
    	if(xp>=getXPRequiredForLvlUp(lvl)){
    		lvlUp(p,s);
    	}
    }
    
    public static void lvlUp(EntityPlayer p,ItemStack s){
    	ModItems.itemAdventurersPickaxe.setToolProperty(s, "lvl", (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "lvl")+1);
    	ModItems.itemAdventurersPickaxe.setToolProperty(s, "xp", 0);
    	int lvl=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "lvl");
    	
    	ModItems.itemAdventurersPickaxe.setToolProperty(s, "durability", (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "durability")+(lvl*2));
    	s.setItemDamage(s.getItemDamage()+(int)(lvl*1.8));
    	
    	applyRandomUpgrade(s);
    	
    	if(lvl==5){
    		ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_level", 1);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpMiningLevel",
        					"%m",Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[1])
        				)
        			)
        		);
        	}
        }else if(lvl==10){
        	ModItems.itemAdventurersPickaxe.setToolProperty(s, "magnetic", true);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpMagnetic")
        			)
        		);
        	}
        }else if(lvl==15){
        	ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_level", 2);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpMiningLevel",
        					"%m",Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[2])
        				)
        			)
        		);
        	}
    	}else if(lvl==20){
    		ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 1);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpLuck",
        					"%m","1"
        				)
        			)
        		);
        	}
    	}else if(lvl==25){
    		ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_level", 3);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpMiningLevel",
        					"%m",Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[3])
        				)
        			)
        		);
        	}
    	}else if(lvl==30){
    		ModItems.itemAdventurersPickaxe.setToolProperty(s, "soulbound", true);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpSoulbound")
        			)
        		);
        	}
    	}else if(lvl==35){
    		ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 2);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpLuck",
        					"%m","2"
        				)
        			)
        		);
        	}
    	}else if(lvl==45){
    		ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 3);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpLuck",
        					"%m","3"
        				)
        			)
        		);
        	}
		}else if(lvl==55){
			ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 4);
        	if(p!=null){
        		p.addChatMessage(
        			new TextComponentString(
        				Helper.localize("message."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.adventurersPickaxeItemName+"LevelUpLuck",
        					"%m","4"
        				)
        			)
        		);
        	}
		}
    	
    	NBTTagCompound nbt=s.getTagCompound();
    	NBTTagCompound props=(NBTTagCompound)nbt.getTag("TOOL_PROPERTIES");
    	props.setTag("STATISTICS",new NBTTagCompound());
        p.worldObj.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
    
    public static void applyRandomUpgrade(ItemStack s){
    	//fix
    	List<Tuple<String,Integer>> u=new ArrayList<Tuple<String,Integer>>();
    	int sum=0;
    	for(String p:getUpgradableStats(s)){
    		if(p.equals("mining_speed")){
    			u.add(new Tuple<String, Integer>(p, (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_mined")+20));
    			sum+=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "stone_mined")+20;
    		}else if(p.equals("digging_speed")){
    			u.add(new Tuple<String, Integer>(p, (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_digged")+20));
    			sum+=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_digged")+20;
    		}else if(p.equals("mining_veins")){
    			u.add(new Tuple<String, Integer>(p, (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "ore_mined")+13));
    			sum+=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "ore_mined")+13;
    		}else if(p.equals("mining_soft_speed")){
    			u.add(new Tuple<String, Integer>(p, (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "stone_mined")+20));
    			sum+=(int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "stone_mined")+20;
    		}
    	}
    	int num=new Random().nextInt(sum)+1;
    	int track=0;
    	for (Tuple<String,Integer> y : u) {
    	    track += y.getSecond();
    	    if (num <= track) {
    	    	applyUpgrade(s,y.getFirst());
    	        return;
    	    }
    	}
    }

    public static void applyUpgrade(ItemStack s,String type){
		if(type.equals("mining_speed")){
			ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (float)ModItems.itemAdventurersPickaxe.getToolProperty(s, type)+0.6f);
		}else if(type.equals("digging_speed")){
			ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (float)ModItems.itemAdventurersPickaxe.getToolProperty(s, type)+0.5f);
		}else if(type.equals("mining_veins")){
			ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (int)ModItems.itemAdventurersPickaxe.getToolProperty(s, type)+12);
		}else if(type.equals("mining_soft_speed")){
			ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (float)ModItems.itemAdventurersPickaxe.getToolProperty(s, type)+0.75f);
		}
    }
    
    public static List<String> getUpgradableStats(ItemStack s){
    	ArrayList<String> ret=new ArrayList<String>();
    	if((float)ModItems.itemAdventurersPickaxe.getToolProperty(s, "mining_speed")<33f)
    		ret.add("mining_speed");
    	if((float)ModItems.itemAdventurersPickaxe.getToolProperty(s, "digging_speed")<7.5f)
    		ret.add("digging_speed");
    	if((int)ModItems.itemAdventurersPickaxe.getToolProperty(s, "mining_veins")<48)
    		ret.add("mining_veins");
    	if((float)ModItems.itemAdventurersPickaxe.getToolProperty(s, "mining_soft_speed")<7.5f)
    		ret.add("mining_soft_speed");
    	return ret;
    }

}
