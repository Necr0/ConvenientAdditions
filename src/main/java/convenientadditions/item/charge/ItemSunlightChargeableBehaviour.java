package convenientadditions.item.charge;

import java.util.ArrayList;

import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.api.item.charge.ItemSunlightChargeable;
import convenientadditions.entity.specialitem.CAEntitySpecialItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemSunlightChargeableBehaviour extends ItemSunlightChargeable implements IModelResourceLocationProvider {
	public ItemSunlightChargeableBehaviour(int capacity,
			boolean showDurabilityBar, boolean showTooltips,
			int sunlightChargeRate) {
		super(capacity, showDurabilityBar, showTooltips, sunlightChargeRate);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
		CAEntitySpecialItem newE=new CAEntitySpecialItem(world, location.posX, location.posY, location.posZ, itemstack);
		newE.setVelocity(location.motionX, location.motionY, location.motionZ);
		ArrayList<Long> cont=new ArrayList<Long>();
		this.getBehaviours(itemstack,cont);
		newE.addBehaviour(cont);
		//newE.delayBeforeCanPickup=20;
		newE.scheduleBehaviourUpdate();
        return newE;
    }
}
