package convenientadditions.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemAdventurersPickaxe extends Item implements IModelResourceLocationProvider {
	
	public ItemAdventurersPickaxe(){
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB).setMaxStackSize(1);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
		if(state.getBlock().getHarvestTool(state)==null||isBroken(stack))
			return 1.0f;
        if(state.getBlock().getHarvestTool(state).equals("pickaxe")){
        	float f=(float)getToolProperty(stack,"mining_speed");
        	return f+(state.getBlock().getHarvestLevel(state)<getHarvestLevel(stack, "pickaxe")?(float)getToolProperty(stack,"mining_soft_speed"):0f);
        }
        if(state.getBlock().getHarvestTool(state).equals("shovel")){
        	return (float)getToolProperty(stack,"digging_speed");
        }
		return 1.0f;
    }
	
    public int getHarvestLevel(ItemStack stack, String toolClass)
    {
    	if(toolClass.equals("pickaxe"))
    		return (int)getToolProperty(stack,"mining_level");
    	else
    		return super.getHarvestLevel(stack, toolClass);
    }
	
    public boolean isItemTool(ItemStack stack)
    {
        return true;
    }
    
    public int getXPRequiredForLvlUp(int current){
    	return (int)(13*Math.pow(1.13,current));
    }
    
    public void gainXP(ItemStack s,int amount){
    	setToolProperty(s, "xp", (int)getToolProperty(s, "xp")+amount);
    	int lvl=(int)getToolProperty(s, "lvl");
    	int xp=(int)getToolProperty(s, "xp");
    	if(xp>=getXPRequiredForLvlUp(lvl)){
    		lvlUp(s);
    	}
    }
    
    public void lvlUp(ItemStack s){
    	setToolProperty(s, "lvl", (int)getToolProperty(s, "lvl")+1);
    	setToolProperty(s, "xp", 0);
    	int lvl=(int)getToolProperty(s, "lvl");
    	setToolProperty(s, "durability", (int)getToolProperty(s, "durability")+lvl*5);
    	applyRandomUpgrade(s);
    	if(lvl==5){
        	setToolProperty(s, "mining_level", 1);
        }else if(lvl==10){
    		setToolProperty(s, "magnetic", true);
        }else if(lvl==15){
        	setToolProperty(s, "mining_level", 2);
    	}else if(lvl==20){
    		setToolProperty(s, "mining_luck", 1);
    	}else if(lvl==25){
    		setToolProperty(s, "mining_level", 3);
    	}else if(lvl==30){
    		setToolProperty(s, "soulbound", true);
    	}else if(lvl==35){
    		setToolProperty(s, "mining_luck", 2);
    	}else if(lvl==40){
    		setToolProperty(s, "mining_silktouch", true);
    	}else if(lvl==45){
    		setToolProperty(s, "mining_luck", 3);
		}
    }
    
    public void applyRandomUpgrade(ItemStack s){
    	//fix
    	List<Tuple<String,Integer>> u=new ArrayList<Tuple<String,Integer>>();
    	int sum=0;
    	for(String p:getUpgradableStats(s)){
    		System.out.println(p);
    		if(p.equals("mining_speed")){
    			u.add(new Tuple<String, Integer>(p, (int)getToolProperty(s, "blocks_mined")+20));
    			sum+=(int)getToolProperty(s, "stone_mined")+20;
    		}else if(p.equals("digging_speed")){
    			u.add(new Tuple<String, Integer>(p, (int)getToolProperty(s, "blocks_digged")+20));
    			sum+=(int)getToolProperty(s, "blocks_digged")+20;
    		}else if(p.equals("mining_veins")){
    			u.add(new Tuple<String, Integer>(p, (int)getToolProperty(s, "ore_mined")+13));
    			sum+=(int)getToolProperty(s, "ore_mined")+13;
    		}else if(p.equals("mining_soft_speed")){
    			u.add(new Tuple<String, Integer>(p, (int)getToolProperty(s, "stone_mined")+20));
    			sum+=(int)getToolProperty(s, "stone_mined")+20;
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

    public void applyUpgrade(ItemStack s,String type){
		if(type.equals("mining_speed")){
	    	setToolProperty(s, type, (float)getToolProperty(s, type)+0.25f);
		}else if(type.equals("digging_speed")){
	    	setToolProperty(s, type, (float)getToolProperty(s, type)+0.5f);
		}else if(type.equals("mining_veins")){
	    	setToolProperty(s, type, (int)getToolProperty(s, type)+12);
		}else if(type.equals("mining_soft_speed")){
	    	setToolProperty(s, type, (float)getToolProperty(s, type)+0.5f);
		}
    }
    
    public List<String> getUpgradableStats(ItemStack s){
    	ArrayList<String> ret=new ArrayList<String>();
    	if((float)getToolProperty(s, "mining_speed")<15f)
    		ret.add("mining_speed");
    	if((float)getToolProperty(s, "digging_speed")<7.5f)
    		ret.add("digging_speed");
    	if((int)getToolProperty(s, "mining_veins")<48)
    		ret.add("mining_veins");
    	if((float)getToolProperty(s, "mining_soft_speed")<5.0f)
    		ret.add("mining_soft_speed");
    	return ret;
    }
    
    public void setToolProperty(ItemStack s,String p,Object o){
    	initTagCompound(s);
    	NBTTagCompound nbt=s.getTagCompound();
    	NBTTagCompound props=(NBTTagCompound)nbt.getTag("TOOL_PROPERTIES");
    	NBTTagCompound stats=(NBTTagCompound)props.getTag("STATISTICS");
    	NBTTagCompound specs=(NBTTagCompound)props.getTag("SPECIFICATIONS");
    	switch (p.toLowerCase()){
			case "durability":
				specs.setInteger("DURABILITY",(int)o);
				break;
    		case "mining_speed":
    			specs.setFloat("MINING_SPEED",(float)o);
				break;
    		case "mining_soft_speed":
    			specs.setFloat("MINING_SOFT_SPEED",(float)o);
				break;
    		case "mining_level":
    			 specs.setInteger("MINING_LEVEL",(int)o);
    			 break;
    		case "mining_veins":
    			 specs.setInteger("MINING_VEINS",(int)o);
    			 break;
    		case "mining_luck":
    			 specs.setInteger("MINING_LUCK",(int)o);
    			 break;
    		case "mining_silktouch":
    			 specs.setBoolean("MINING_SILKTOUCH",(boolean)o);
    			 break;
    		case "digging_speed":
    			 specs.setFloat("DIGGING_SPEED",(float)o);
    			 break;
    		case "blocks_digged":
    			 stats.setInteger("BLOCKS_DIGGED",(int)o);
    			 break;
    		case "blocks_mined":
    			 stats.setInteger("BLOCKS_MINED",(int)o);
    			 break;
    		case "ore_mined":
    			 stats.setInteger("ORE_MINED",(int)o);
    			 break;
    		case "stone_mined":
    			 stats.setInteger("STONE_MINED",(int)o);
    			 break;
    		case "xp":
    			 props.setInteger("XP",(int)o);
    			 break;
    		case "lvl":
    			 props.setInteger("LVL",(int)o);
    			 break;
    		case "magnetic":
    			specs.setBoolean("MAGNETIC",(boolean)o);
   			 	break;
    		case "soulbound":
    			specs.setBoolean("SOULBOUND",(boolean)o);
   			 	break;
    	}
    }
    
    public Object getToolProperty(ItemStack s,String p){
    	initTagCompound(s);
    	NBTTagCompound nbt=s.getTagCompound();
    	NBTTagCompound props=(NBTTagCompound)nbt.getTag("TOOL_PROPERTIES");
    	NBTTagCompound stats=(NBTTagCompound)props.getTag("STATISTICS");
    	NBTTagCompound specs=(NBTTagCompound)props.getTag("SPECIFICATIONS");
    	switch (p.toLowerCase()){
			case "durability":
				return specs.getInteger("DURABILITY");
    		case "mining_speed":
    			return specs.getFloat("MINING_SPEED");
    		case "mining_soft_speed":
    			return specs.getFloat("MINING_SOFT_SPEED");
    		case "mining_level":
    			return specs.getInteger("MINING_LEVEL");
    		case "mining_veins":
    			return specs.getInteger("MINING_VEINS");
    		case "mining_luck":
    			return specs.getInteger("MINING_LUCK");
    		case "mining_silktouch":
    			return specs.getBoolean("MINING_SILKTOUCH");
    		case "digging_speed":
    			return specs.getFloat("DIGGING_SPEED");
    		case "blocks_digged":
    			return stats.getInteger("BLOCKS_DIGGED");
    		case "blocks_mined":
    			return stats.getInteger("BLOCKS_MINED");
    		case "ore_mined":
    			return stats.getInteger("ORE_MINED");
    		case "stone_mined":
    			return stats.getInteger("STONE_MINED");
    		case "xp":
    			return props.getInteger("XP");
    		case "lvl":
    			return props.getInteger("LVL");
    		case "magnetic":
    			return specs.getBoolean("MAGNETIC");
    		case "soulbound":
    			return specs.getBoolean("SOULBOUND");
			default:
				return null;
    	}
    }
    
    public void initTagCompound(ItemStack s){
    	if(!s.hasTagCompound()){
    		s.setTagCompound(new NBTTagCompound());
    	}
    	
    	NBTTagCompound nbt=s.getTagCompound();
    	if(!nbt.hasKey("TOOL_PROPERTIES")){
    		nbt.setTag("TOOL_PROPERTIES", new NBTTagCompound());
    	}
    	
    	NBTTagCompound prop=(NBTTagCompound)nbt.getTag("TOOL_PROPERTIES");
    	if(!prop.hasKey("STATISTICS")){
    		prop.setTag("STATISTICS", new NBTTagCompound());
    	}
    	NBTTagCompound stats=(NBTTagCompound)prop.getTag("STATISTICS");
    	initStatistics(stats);
    	
    	if(!prop.hasKey("SPECIFICATIONS")){
    		prop.setTag("SPECIFICATIONS", new NBTTagCompound());
    	}
    	NBTTagCompound specs=(NBTTagCompound)prop.getTag("SPECIFICATIONS");
    	initSpecifications(specs);
    	
    	if(!prop.hasKey("XP")){
    		prop.setInteger("XP", 0);
    	}
    	if(!prop.hasKey("LVL")){
    		prop.setInteger("LVL", 0);
    	}
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		boolean shift=Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)||Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		int lvl=(int)getToolProperty(stack, "lvl");
		list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"MiningLevel","%l",Helper.localize(ConvenientAdditions.MODID+":miningLevel"+(int)getToolProperty(stack, "mining_level"))));
		list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"LVL","%l",""+lvl));
		list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"XP","%x",""+(int)getToolProperty(stack, "xp"),"%X",""+getXPRequiredForLvlUp(lvl)));
		if(!shift){
			list.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ConvenientAdditions.MODID+":shiftInfo"));
		}else{
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"MiningSpeed","%s",""+(float)getToolProperty(stack, "mining_speed")));
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"DiggingSpeed","%s",""+(float)getToolProperty(stack, "digging_speed")));
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"MiningVeins","%v",""+(int)getToolProperty(stack, "mining_veins")));
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"SoftSpeed","%s",""+(float)getToolProperty(stack, "mining_soft_speed")));
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":"+Reference.adventurersPickaxeItemName+"Durability","%c",""+((int)getToolProperty(stack, "durability")-stack.getItemDamage()),"%D",""+(int)getToolProperty(stack, "durability")));
		}
	}
    
    public void initStatistics(NBTTagCompound c){
    	if(!c.hasKey("BLOCKS_MINED")){
    		c.setInteger("BLOCKS_MINED", 0);
    	}
    	if(!c.hasKey("STONE_MINED")){
    		c.setInteger("STONE_MINED", 0);
    	}
    	if(!c.hasKey("ORE_MINED")){
    		c.setInteger("ORE_MINED", 0);
    	}
    	if(!c.hasKey("BLOCKS_DIGGED")){
    		c.setInteger("BLOCKS_DIGGED", 0);
    	}
    }

    public void initSpecifications(NBTTagCompound c){
    	if(!c.hasKey("DURABILITY")){
    		c.setInteger("DURABILITY", 130);
    	}
    	if(!c.hasKey("MINING_LEVEL")){
    		c.setInteger("MINING_LEVEL", 0);
    	}
    	if(!c.hasKey("MINING_SPEED")){
    		c.setFloat("MINING_SPEED", 2.0F);
    	}
    	if(!c.hasKey("MINING_SOFT_SPEED")){
    		c.setFloat("MINING_SOFT_SPEED", 0F);
    	}
    	if(!c.hasKey("MINING_VEINS")){
    		c.setInteger("MINING_VEINS", 0);
    	}
    	if(!c.hasKey("MINING_LUCK")){
    		c.setInteger("MINING_LUCK", 0);
    	}
    	if(!c.hasKey("MINING_LUCK")){
    		c.setInteger("MINING_LUCK", 0);
    	}
    	if(!c.hasKey("MINING_SILKTOUCH")){
    		c.setBoolean("MINING_SILKTOUCH", false);
    	}
    	if(!c.hasKey("DIGGING_SPEED")){
    		c.setFloat("DIGGING_SPEED", 1.0F);
    	}
    	if(!c.hasKey("MAGNETIC")){
    		c.setBoolean("MAGNETIC", false);
    	}
    	if(!c.hasKey("SOULBOUND")){
    		c.setBoolean("SOULBOUND", false);
    	}
    }
    
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
    	if(worldIn.isRemote)
    		return false;
    	if(!isBroken(stack)){
	    	stack.setItemDamage(stack.getItemDamage()+1);
	    	setToolProperty(stack, "blocks_mined", (int)getToolProperty(stack, "blocks_mined")+1);
	    	if(Helper.doesOreDictMatch(state, "stone", false)){
	        	setToolProperty(stack, "stone_mined", (int)getToolProperty(stack, "stone_mined")+1);
	    		gainXP(stack, 1);
	    	}else if(Helper.doesOreDictMatch(state, "ore", true)){
	        	setToolProperty(stack, "ore_mined", (int)getToolProperty(stack, "ore_mined")+1);
	    		gainXP(stack,(state.getBlock().getHarvestLevel(state)+1)*5);
	    	}else if(state.getBlock().getHarvestTool(state).equals("shovel")){
	        	setToolProperty(stack, "blocks_digged", (int)getToolProperty(stack, "blocks_digged")+1);
	    		gainXP(stack, 1);
	    	}else{
	    		gainXP(stack, 1);
	    	}
    	}
        return false;
    }
    
    public boolean isBroken(ItemStack item){ return item.getItemDamage()>=(int)getToolProperty(item, "durability"); }

	@Override
    public boolean showDurabilityBar(ItemStack item){ return item.getItemDamage()!=0; }
    
	@Override
    public double getDurabilityForDisplay(ItemStack item)
    {
        return ((double)item.getItemDamage()/(int)getToolProperty(item,"durability"));
    }

	@SubscribeEvent
	public void onBlockBreak(HarvestDropsEvent e)
    {
    	if(e.getHarvester()!=null&&e.getHarvester().getHeldItemMainhand()!=null&&e.getHarvester().getHeldItemMainhand().getItem()==this){
			if(Helper.doesOreDictMatch(e.getState(), "ore", true)&&(int)getToolProperty(e.getHarvester().getHeldItemMainhand(), "mining_veins")>0){
	    		List<BlockPos> l=Helper.floodFill(e.getWorld(), e.getPos(), e.getState(), 2, (int)getToolProperty(e.getHarvester().getHeldItemMainhand(), "mining_veins"), true, true);
	    		e.getWorld().setBlockState(e.getPos(), e.getWorld().getBlockState(l.get(l.size()-1)));
	    		e.getWorld().setBlockToAir(l.get(l.size()-1));
    		}
    	}
    }
}
