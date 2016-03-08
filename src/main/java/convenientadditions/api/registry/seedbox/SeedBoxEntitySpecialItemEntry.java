package convenientadditions.api.registry.seedbox;

import java.util.List;

import net.minecraft.item.ItemStack;

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
	
	public void getDiscriminators(ItemStack stack,List<Long> behaviours){
		behaviours.add(this.discriminator);
	}
}
