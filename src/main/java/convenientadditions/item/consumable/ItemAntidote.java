package convenientadditions.item.consumable;

import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemAntidote extends CAItem {
    @GameRegistry.ObjectHolder("minecraft:nausea")
    public static Potion NAUSEA;
    @GameRegistry.ObjectHolder("minecraft:hunger")
    public static Potion HUNGER;
    @GameRegistry.ObjectHolder("minecraft:poison")
    public static Potion POISON;
    @GameRegistry.ObjectHolder("minecraft:wither")
    public static Potion WITHER;

    public ItemAntidote() {
        super(ModConstants.ItemNames.antidote);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.CONSUMABLE);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        playerIn.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer) entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
            stack.shrink(1);
        }

        if (!worldIn.isRemote) {
            entityLiving.removePotionEffect(NAUSEA);
            entityLiving.removePotionEffect(HUNGER);
            entityLiving.removePotionEffect(POISON);
            entityLiving.removePotionEffect(WITHER);
        }

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
            if (stack.getCount() <= 0) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (entityplayer != null) {
                entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
}
