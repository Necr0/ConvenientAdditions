package convenientadditions.item.charge;

import java.util.ArrayList;
import java.util.List;

import conveniencecore.api.item.IBehaviourProvider;
import conveniencecore.entity.behaviour.BehaviourRegistry;
import conveniencecore.entity.behaviour.EntitySpecialItem;
import convenientadditions.api.item.charge.ItemSunlightChargeable;
import convenientadditions.entity.behaviour.BehaviourSunlightChargeable;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemSunlightChargeableBehaviour extends ItemSunlightChargeable implements IBehaviourProvider {
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
		newE.addBehaviours(cont);
		newE.setPickupDelay(20);
        return newE;
    }

	@Override
	public void getBehaviours(ItemStack stack,World world,List<Long> behaviours){
		behaviours.add(BehaviourRegistry.API_DISCRIMINATORS.get("sunlightChargeable"));
	}

	@Override
	public void getBehaviours(ItemStack stack,List<Long> behaviours){
		behaviours.add(BehaviourSunlightChargeable.DISCRIMINATOR);
	}
}
