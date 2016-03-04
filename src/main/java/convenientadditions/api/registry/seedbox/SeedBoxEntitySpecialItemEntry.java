package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;

public class SeedBoxEntitySpecialItemEntry implements ISeedBoxItemBehaviourRegistryEntry{
	public ItemStack stack;
	public long discriminator;
	public boolean ignoreDamage=true,ignoreNBT=true;
	
	public SeedBoxEntitySpecialItemEntry(ItemStack stack,long discriminator){
		this.stack=stack;
		this.discriminator=discriminator;
	}
	
	public SeedBoxEntitySpecialItemEntry(ItemStack stack,long discriminator,boolean ignoreDamage,boolean ignoreNBT){
		this.stack=stack;
		this.discriminator=discriminator;
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
	
	public long getDiscriminator(ItemStack stack){
		return this.discriminator;
	}
}
