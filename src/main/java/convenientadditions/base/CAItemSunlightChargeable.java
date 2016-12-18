package convenientadditions.base;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.api.entity.specialitem.BehaviourRegistry;
import convenientadditions.api.entity.specialitem.EntitySpecialItem;
import convenientadditions.api.entity.specialitem.behaviours.BehaviourSunlightChargeable;
import convenientadditions.api.item.IBehaviourProvider;
import convenientadditions.api.item.charge.ItemSunlightChargeable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public abstract class CAItemSunlightChargeable extends ItemSunlightChargeable implements IBehaviourProvider {
    public CAItemSunlightChargeable(String name,int capacity, boolean showDurabilityBar, boolean showTooltips, int sunlightChargeRate) {
        super(capacity, showDurabilityBar, showTooltips, sunlightChargeRate);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(name).setMaxStackSize(1).setHasSubtypes(true);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        EntitySpecialItem newE = new EntitySpecialItem(world, location.posX, location.posY, location.posZ, itemstack);
        newE.setVelocity(location.motionX, location.motionY, location.motionZ);
        ArrayList<Long> cont = new ArrayList<Long>();
        this.getBehaviours(itemstack, cont);
        newE.addBehaviours(cont);
        newE.setPickupDelay(20);
        return newE;
    }

    @Override
    public void getBehaviours(ItemStack stack, World world, List<Long> behaviours) {
        behaviours.add(BehaviourRegistry.API_DISCRIMINATORS.get("sunlightChargeable"));
    }

    @Override
    public void getBehaviours(ItemStack stack, List<Long> behaviours) {
        behaviours.add(BehaviourSunlightChargeable.DISCRIMINATOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.add(StringHelper.getInfo(stack));
        super.addInformation(stack, player, tooltip, advanced);
    }
}
