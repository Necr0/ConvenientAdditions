package convenientadditions.item.charge.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.base.CAItemSunlightChargeable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "Baubles",striprefs = true)
public class ItemBreathAmulet extends CAItemSunlightChargeable implements IBauble {
    public static ItemStack FULLY_CHARGED;

    public ItemBreathAmulet() {
        super(ModConstants.ItemNames.breathAmulet, 10000, true, true, 5);
        FULLY_CHARGED = new ItemStack(this, 1, 0);
        chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote)
            return;
        if (getCharge(itemstack) >= 2) {
            EntityPlayer p = (EntityPlayer) player;
            int air = p.getAir();
            if (air < 20) {
                p.setAir(20);
                consumeCharge(itemstack, 2 * (20 - air));
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(StringHelper.getJoke(stack));
        super.addInformation(stack, player, list, par4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
}
