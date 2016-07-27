package convenientadditions.item;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBandage extends Item implements IModelResourceLocationProvider {
	public ItemBandage(){
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.bandageItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB).setMaxStackSize(4);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        playerIn.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        if (!worldIn.isRemote)
        {
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 5*20, 2));
        }

        return stack;
    }
    
    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
    	if(count%5==0){
    		if(player.worldObj.isRemote){
    			Minecraft.getMinecraft().getItemRenderer().resetEquippedProgress(player.getActiveHand());
    		}else{
				player.worldObj.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_CLOTH_HIT, SoundCategory.PLAYERS, .1F, .6F);
			}
    	}
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 100;
    }
	
	@Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }
}