package convenientadditions.item.channelModule.color;

import java.util.List;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.api.item.ItemChannelModule;
import convenientadditions.api.provider.itemnetwork.IMatcher;
import convenientadditions.init.ModGuiHandler;
import convenientadditions.item.channelModule.matchers.MatcherEnumDyeColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorChannelModule extends ItemChannelModule implements IModelResourceLocationProvider {
	
	public static ItemStack DEFAULT_STACK;
	
	public ItemColorChannelModule() {
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.moduleColorItemName);
		ItemStack tmp_stack=new ItemStack(this);
		NBTTagCompound tmp_nbt=new NBTTagCompound();
		tmp_nbt.setInteger("MATCHER_DYE_0", 15);
		tmp_nbt.setInteger("MATCHER_DYE_1", 15);
		tmp_nbt.setInteger("MATCHER_DYE_2", 15);
		tmp_stack.setTagCompound(tmp_nbt);
		ItemColorChannelModule.DEFAULT_STACK=tmp_stack;
	}

	@Override
	public boolean hasMatcher(ItemStack stack) {
		return stack.hasTagCompound()&&stack.getTagCompound().hasKey("MATCHER_DYE_0")&&stack.getTagCompound().hasKey("MATCHER_DYE_1")&&stack.getTagCompound().hasKey("MATCHER_DYE_2");
	}

	@Override
	public IMatcher getMatcher(ItemStack stack) {
		return new MatcherEnumDyeColor(getDyeColors(stack)[0], getDyeColors(stack)[1], getDyeColors(stack)[2]);
	}
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		if(hasMatcher(stack)){
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":moduleColorsPrefix",
					"%c0",Helper.localize("color."+ConvenientAdditions.MODID+":"+getDyeColors(stack)[0].getUnlocalizedName()),
					"%c1",Helper.localize("color."+ConvenientAdditions.MODID+":"+getDyeColors(stack)[1].getUnlocalizedName()),
					"%c2",Helper.localize("color."+ConvenientAdditions.MODID+":"+getDyeColors(stack)[2].getUnlocalizedName())));
		}
		super.addInformation(stack,player,list,par4);
	}
	
	public EnumDyeColor[] getDyeColors(ItemStack stack) {
		return new EnumDyeColor[]{
				EnumDyeColor.byDyeDamage(stack.getTagCompound().getInteger("MATCHER_DYE_0")),
				EnumDyeColor.byDyeDamage(stack.getTagCompound().getInteger("MATCHER_DYE_1")),
				EnumDyeColor.byDyeDamage(stack.getTagCompound().getInteger("MATCHER_DYE_2"))};
	}
	
	public int[] getColorInts(ItemStack stack) {
		return new int[]{
				stack.getTagCompound().getInteger("MATCHER_DYE_0"),
				stack.getTagCompound().getInteger("MATCHER_DYE_1"),
				stack.getTagCompound().getInteger("MATCHER_DYE_2")};
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List<ItemStack> l)
    {
        l.add(ItemColorChannelModule.DEFAULT_STACK.copy());
    }

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand)
	{
		if(world.isRemote){
    		player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_COLOR_MODULE_ID, world, hand.ordinal(), 0, 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,item);
	}

}
