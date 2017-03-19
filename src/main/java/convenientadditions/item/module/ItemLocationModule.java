package convenientadditions.item.module;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemLocationModule extends CAItem {
    public ItemLocationModule() {
        super(ModConstants.ItemNames.moduleLocation);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.MODULE);
    }

    public boolean hasLocation(ItemStack stack) {
        if(!stack.hasTagCompound())
            return false;
        NBTTagCompound t=stack.getTagCompound();
        return t.hasKey("MODULE_LOCATION") && t.getTag("MODULE_LOCATION") instanceof  NBTTagIntArray && t.getIntArray("MODULE_LOCATION").length==3 && t.hasKey("MODULE_LOCATION_DIM") && t.getTag("MODULE_LOCATION_DIM") instanceof NBTTagInt;
    }

    public BlockPos getLocation(ItemStack stack) {
        NBTTagCompound t=stack.getTagCompound();
        int[] arr=t.getIntArray("MODULE_LOCATION");
        return new BlockPos(arr[0],arr[1],arr[2]);
    }

    public int getDimension(ItemStack stack) {
        NBTTagCompound t=stack.getTagCompound();
        return t.getInteger("MODULE_LOCATION_DIM");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack item=player.getHeldItem(hand);
        if (!world.isRemote) {
            if (!item.hasTagCompound())
                item.setTagCompound(new NBTTagCompound());
            if (player.isSneaking()) {
                item.getTagCompound().setIntArray("MODULE_LOCATION", new int[]{p.getX(),p.getY(),p.getZ()});
                item.getTagCompound().setInteger("MODULE_LOCATION_DIM", world.provider.getDimension());
                player.sendMessage(new TextComponentString(Helper.localize("message."+ModConstants.Mod.MODID+":locationSetTo",p.getX(), p.getY(), p.getZ(), world.provider.getDimension())));
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack item=player.getHeldItem(hand);
        if (!world.isRemote) {
            if (!item.hasTagCompound())
                item.setTagCompound(new NBTTagCompound());
            if (player.isSneaking()) {
                item.getTagCompound().removeTag("MODULE_LOCATION");
                player.sendMessage(new TextComponentString(Helper.localize("message."+ModConstants.Mod.MODID+":locationReset")));
                return new ActionResult<>(EnumActionResult.SUCCESS,item);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, item);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        if (hasLocation(stack)) {
            BlockPos p=getLocation(stack);
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":moduleLocationLocation", p.getX(), p.getY(), p.getZ(), getDimension(stack)));
        }else
            list.add(TextFormatting.DARK_GRAY + Helper.localize("tooltip." + ModConstants.Mod.MODID + ":moduleLocationNotSet"));
    }
}
