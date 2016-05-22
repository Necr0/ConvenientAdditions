package convenientadditions.item.charge;

import java.util.ArrayList;

import conveniencecore.entity.EntitySpecialItem;
import conveniencecore.item.IModelResourceLocationProvider;
import convenientadditions.api.item.charge.ItemSunlightChargeable;
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
		EntitySpecialItem newE=new EntitySpecialItem(world, location.posX, location.posY, location.posZ, itemstack);
		newE.setVelocity(location.motionX, location.motionY, location.motionZ);
		ArrayList<Long> cont=new ArrayList<Long>();
		this.getBehaviours(itemstack,cont);
		newE.addBehaviour(cont);
		newE.setPickupDelay(20);
		newE.scheduleBehaviourUpdate();
        return newE;
    }
}
