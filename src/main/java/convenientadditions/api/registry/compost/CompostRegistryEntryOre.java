package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompostRegistryEntryOre implements ICompostRegistryEntry{
	public String name;
	public int mass;
	public boolean startsWith;
	
	public CompostRegistryEntryOre(String name,int mass, boolean startsWith) {
		this.name=name;
		this.mass=mass;
		this.startsWith=startsWith;
	}
	
	@Override
	public boolean doesMatch(ItemStack stack) {
		for(int i:OreDictionary.getOreIDs(stack)){
			if(OreDictionary.getOreName(i).equals(name))
				return true;
			else if(OreDictionary.getOreName(i).startsWith(name)&&startsWith)
				return true;
		}
		return false;
	}

	@Override
	public int getCompostingMass(ItemStack stack) {
		return mass;
	}
}
