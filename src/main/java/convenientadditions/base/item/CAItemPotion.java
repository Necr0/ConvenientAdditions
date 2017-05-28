package convenientadditions.base.item;

import convenientadditions.api.util.Helper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Necro on 5/15/2017.
 */
public class CAItemPotion extends CAItem {

    public final PotionEffect effect;

    public CAItemPotion(String name, PotionEffect effect) {
        super(name);
        this.effect=effect;
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.CONSUMABLE).setDefaultInfo(false);
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
            entityLiving.addPotionEffect(new PotionEffect(effect));
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

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        String effectName = Helper.localize(effect.getEffectName()).trim();
        if (effect.getAmplifier() > 0)
            effectName = effectName + " " + Helper.localize("potion.potency." + effect.getAmplifier()).trim();
        if (effect.getDuration() > 20)
            effectName = effectName + " (" + Potion.getPotionDurationString(effect, 1F) + ")";
        if (effect.getPotion().isBadEffect())
            tooltip.add(TextFormatting.RED + effectName);
        else
            tooltip.add(TextFormatting.BLUE + effectName);

        super.addInformation(stack,playerIn,tooltip,advanced);
    }
}
