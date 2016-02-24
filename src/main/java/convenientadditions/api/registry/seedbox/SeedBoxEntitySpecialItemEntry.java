package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;

public class SeedBoxEntitySpecialItemEntry implements ISeedBoxItemBehaviourRegistryEntry{
	public ItemStack stack;
	public IEntitySpecialItemBehaviour behaviour;
	public boolean ignoreDamage=true,ignoreNBT=true;
	
	public SeedBoxEntitySpecialItemEntry(ItemStack stack,IEntitySpecialItemBehaviour behaviour){
		this.stack=stack;
		this.behaviour=behaviour;
	}
	
	public SeedBoxEntitySpecialItemEntry(ItemStack stack,IEntitySpecialItemBehaviour behaviour,boolean ignoreDamage,boolean ignoreNBT){
		this.stack=stack;
		this.behaviour=behaviour;
		this.ignoreDamage=ignoreDamage;
		this.ignoreNBT=ignoreNBT;
	}
	
	public boolean hasSpecialBehaviour(ItemStack stack){
		return (stack.getItem()==this.stack.getItem())&&
				(ignoreDamage
						?true
						:stack.getItemDamage()==stack.getItemDamage()
					)&&
				(ignoreNBT
						?true
						:((stack.hasTagCompound()!=this.stack.hasTagCompound())
								?false
								:(!stack.hasTagCompound()
										?true
										:stack.getTagCompound().equals(this.stack.getTagCompound())
									)
							)
					);
	}
	
	public IEntitySpecialItemBehaviour getSpecialBehaviour(ItemStack stack){
		return this.behaviour;
	}
}
