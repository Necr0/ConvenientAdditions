package convenientadditions.item.charge;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSunstone extends ItemSunlightChargeableBehaviour implements IPlayerInventoryTick,IModelResourceLocationProvider {
	public static ItemStack FULLY_CHARGED;
    
	public ItemSunstone(){
		super(60000,true,true,20);
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		int dmg=itemStack.getItemDamage();
    	if(!world.isRemote)
    		if(dmg==0&&getCharge(itemStack)!=0){
    			itemStack.setItemDamage(1);
    		}else
    			itemStack.setItemDamage(0);
    	return itemStack;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstone"));
		super.addInformation(stack,player,list,par4);
		if(isActive(stack))
			list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneActive"));
		else
			list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneInactive"));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public int consumeCharge(ItemStack item,int amount)
    {
        int ret=super.consumeCharge(item, amount);
        if(this.getCharge(item)==0)
        	item.setItemDamage(0);
        return ret;
    }
	
	public boolean isActive(ItemStack stack){
		return (getCharge(stack))>0&&stack.getItemDamage()==1;
	}

	@Override
	public void onPlayerInventoryTick(ItemStack item, int slot, EntityPlayer player) {
		if(player.worldObj.isRemote)
			return;
		WorldServer world = (WorldServer)player.worldObj;
		Random random = new Random();
		if(ModItems.itemSunstone.isActive(item)){
    		consumeCharge(item, 1);
    		for(int x=0;x<9;x++){
    			for(int y=0;y<9;y++){
    				for(int z=0;z<9;z++){
    					BlockPos pos=new BlockPos(x-4+(int)player.posX,y-4+(int)player.posY,z-4+(int)player.posZ);
    					Block b=world.getBlockState(pos).getBlock();
    					if(b.isAir(world,pos)&&b!=ModBlocks.tempLightBlock){
    						world.setBlockState(pos, ModBlocks.tempLightBlock.getStateFromMeta(0), 3);
    						world.scheduleBlockUpdate(pos, ModBlocks.tempLightBlock, 20+random.nextInt(20), 0);
    					}
            		}
        		}
    		}
		}
	}

	@Override
	public boolean isSunlightChargeable(ItemStack item,int slot) {
		return !isActive(item)&&(slot>=0&&slot<=9||slot==-5);
	}
}
