package convenientadditions.item.slime;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.IFuelItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGoo extends ItemFood implements IFuelItem {
	public boolean edible;
	public boolean fireImmunity;
	public boolean explosionImmunity;
	public boolean waterSensitvity;
	public boolean sunlightSensitvity;
	
	public ItemGoo(String name,String texture){
		super(0,0,false);
		this.setHasSubtypes(false)
		.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+name)
		.setTextureName(ConvenientAdditionsMod.MODID+":"+"goo/"+texture)
		.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.edible=false;
	}
	
	public ItemGoo(String name,String texture,int foodLevel,float saturation){
		super(foodLevel,saturation,false);
		this.setHasSubtypes(false)
		.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+name)
		.setTextureName(ConvenientAdditionsMod.MODID+":"+"goo/"+texture)
		.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.edible=true;
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(edible)
        	return super.onItemRightClick(stack, world, player);
        else if(stack.getItem()==boom){
        	if(!world.isRemote){
        		stack.stackSize--;
        		world.createExplosion(null, player.posX, player.posY+player.eyeHeight-.01d, player.posZ, 1.0f, false);
        		player.swingItem();
        	}
        }
        return stack;
    }
	
	public static final ItemGoo royal=new ItemGoo(Reference.royalGooItemName,Reference.royalGooItemName);
	public static final ItemGoo kitty=new ItemGoo(Reference.kittyGooItemName,Reference.kittyGooItemName);
	public static final ItemGoo pink=new ItemGoo(Reference.pinkGooItemName,Reference.pinkGooItemName,2,1.2f);
	public static final ItemGoo stone=new ItemGoo(Reference.stoneGooItemName,Reference.stoneGooItemName);
	public static final ItemGoo blazing=new ItemGoo(Reference.blazingGooItemName,Reference.blazingGooItemName);
	public static final ItemGoo ender=new ItemGoo(Reference.enderGooItemName,Reference.enderGooItemName);
	public static final ItemGoo undead=new ItemGoo(Reference.undeadGooItemName,Reference.undeadGooItemName,2,.4f);
	public static final ItemGoo shroom=new ItemGoo(Reference.shroomGooItemName,Reference.shroomGooItemName,2,2f);
	public static final ItemGoo honey=new ItemGoo(Reference.honeyGooItemName,Reference.honeyGooItemName,3,4f);
	public static final ItemGoo boom=new ItemGoo(Reference.boomGooItemName,Reference.boomGooItemName);
	
	static {
		blazing.fireImmunity=true;
		blazing.waterSensitvity=true;
		blazing.
		ender.waterSensitvity=true;
		undead.setPotionEffect(17, 5, 1, .18f);
		undead.sunlightSensitvity=true;
		boom.explosionImmunity=true;
	}
	
	public static void init(){
		GameRegistry.registerItem(royal,Reference.royalGooItemName);
		GameRegistry.registerItem(kitty,Reference.kittyGooItemName);
		GameRegistry.registerItem(pink,Reference.pinkGooItemName);
		GameRegistry.registerItem(stone,Reference.stoneGooItemName);
		GameRegistry.registerItem(blazing,Reference.blazingGooItemName);
		GameRegistry.registerItem(ender,Reference.enderGooItemName);
		GameRegistry.registerItem(undead,Reference.undeadGooItemName);
		GameRegistry.registerItem(shroom,Reference.shroomGooItemName);
		GameRegistry.registerItem(honey,Reference.honeyGooItemName);
		GameRegistry.registerItem(boom,Reference.boomGooItemName);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
		EntityGooItem newE=new EntityGooItem(world,location.posX,location.posY,location.posZ,itemstack,((ItemGoo)itemstack.getItem()).fireImmunity, ((ItemGoo)itemstack.getItem()).explosionImmunity, ((ItemGoo)itemstack.getItem()).waterSensitvity, ((ItemGoo)itemstack.getItem()).sunlightSensitvity);
		newE.setVelocity(location.motionX, location.motionY, location.motionZ);
        return newE;
    }

	@Override
	public boolean isFuelItem(ItemStack item) {
		return (item.getItem()==blazing);
	}

	@Override
	public int getFuelTime(ItemStack item) {
        if(item.getItem()==blazing){
        	return 400;
        }
		return 0;
	}
}
