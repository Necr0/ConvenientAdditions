package convenientadditions.item.charge.enderPlate;

import java.util.List;

import conveniencecore.api.item.IPlayerInventoryTick;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.item.charge.ItemChargeable;
import convenientadditions.block.technical.BlockPhantomPlatform;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnderPlate extends ItemChargeable implements IPlayerInventoryTick{
	public static ItemStack FULLY_CHARGED;

	public ItemEnderPlate() {
		super(96000, true, true);//8
		this.setUnlocalizedName(ModConstants.Mod.MODID+":"+ModConstants.ItemNames.enderPlateItemName)
		.setCreativeTab(ConvenientAdditions.CREATIVETAB)
		.setHasSubtypes(true)
		.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
		int dmg=itemStack.getItemDamage();
    	if(!world.isRemote)
    		if(dmg==0&&getCharge(itemStack)!=0){
    			itemStack.setItemDamage(1);
    		}else
    			itemStack.setItemDamage(0);
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,itemStack);
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
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List<ItemStack> l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
	
	public boolean isActive(ItemStack stack){
		return (getCharge(stack))>0&&stack.getItemDamage()==1;
	}

	@Override
	public void onPlayerInventoryTick(ItemStack item, int slot, EntityPlayer player) {
		if(player.worldObj.isRemote||!(slot>=0&&slot<=9||slot==255))
			return;
		WorldServer world = (WorldServer)player.worldObj;
		if(isActive(item)){
    		consumeCharge(item, 6);
    		for(int x=0;x<3;x++){
    			for(int y=0;y<3;y++){
    				for(int z=0;z<3;z++){
    					BlockPos player_pos=player.getPosition();
    					BlockPos pos=new BlockPos(x-1+player_pos.getX(),y-2+player_pos.getY(),z-1+player_pos.getZ());
    					IBlockState state=world.getBlockState(pos);
    					Block b=state.getBlock();
    					if(b.isAir(state,world,pos)&&b!=ModBlocks.phantomPlatformBlock){
    						world.setBlockState(pos, ModBlocks.phantomPlatformBlock.getDefaultState().withProperty(BlockPhantomPlatform.DESPAWN, true), 3+4);
    						world.scheduleBlockUpdate(pos, ModBlocks.phantomPlatformBlock, 1, 0);
    					}
            		}
        		}
    		}
		}
	}
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		list.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":enderSlate"));
		super.addInformation(stack, player, list, par4);
		list.add(TextFormatting.DARK_GRAY+ItemChargeable.localize("tooltip."+ModConstants.Mod.MODID+":enderSlateDrained"));
		if(isActive(stack))
			list.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":enderSlateActive"));
		else
			list.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":enderSlateInactive"));
	}
}
