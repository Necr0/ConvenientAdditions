package convenientadditions.api.registry;

import java.util.ArrayList;
import java.util.List;

import convenientadditions.api.item.ICompostable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoublePlant;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompostRegistry {
	public static final List<ICompostRegistryEntry> REGISTRY=new ArrayList<ICompostRegistryEntry>();
	private static boolean registered=false;
	
	public static void addEntry(ICompostRegistryEntry entry){
		REGISTRY.add(entry);
	}
	
	public static void addCompostingItem(ItemStack stack,int mass){
		addEntry(new CompostRegistryEntry(stack, mass));
	}
	
	public static void addCompostingItem(ItemStack stack,int mass,boolean ignoreDamage,boolean ignoreNBT){
		addEntry(new CompostRegistryEntry(stack, mass, ignoreDamage, ignoreNBT));
	}
	
	public static void addCompostingOre(String name,int mass){
		addEntry(new OreDictEntry(name, mass, false));
	}
	
	public static void addCompostingOre(String name,int mass,boolean startsWith){
		addEntry(new OreDictEntry(name, mass, startsWith));
	}
	
	public static int getCompostingMass(ItemStack stack){
		for(ICompostRegistryEntry e:REGISTRY){
			if(e.doesMatch(stack))
				return e.getCompostingMass(stack);
		}
		return 0;
	}
	
	public static interface ICompostRegistryEntry{
		public boolean doesMatch(ItemStack stack);
		public int getCompostingMass(ItemStack stack);
	}
	
	public static class CompostRegistryEntry implements ICompostRegistryEntry{
		public ItemStack stack;
		public int mass=0;
		public boolean ignoreDamage=true,ignoreNBT=true;
		
		public CompostRegistryEntry(ItemStack stack,int mass){
			this.stack=stack;
			this.mass=mass;
		}
		
		public CompostRegistryEntry(ItemStack stack,int mass,boolean ignoreDamage,boolean ignoreNBT){
			this.stack=stack;
			this.mass=mass;
			this.ignoreDamage=ignoreDamage;
			this.ignoreNBT=ignoreNBT;
		}
		
		public boolean doesMatch(ItemStack stack){
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
		
		public int getCompostingMass(ItemStack stack){
			return this.mass;
		}
	}
	
	public static class DoublePlantEntry implements ICompostRegistryEntry{

		@Override
		public boolean doesMatch(ItemStack stack) {
			return stack.getItem() instanceof ItemDoublePlant;
		}

		@Override
		public int getCompostingMass(ItemStack stack) {
			return 350;
		}
	}
	
	public static class FoodEntry implements ICompostRegistryEntry{

		@Override
		public boolean doesMatch(ItemStack stack) {
			return stack.getItem() instanceof ItemFood;
		}

		@Override
		public int getCompostingMass(ItemStack stack) {
			return ((ItemFood)stack.getItem()).func_150905_g(stack)*400;
		}
	}
	
	public static class OreDictEntry implements ICompostRegistryEntry{
		public String name;
		public int mass;
		public boolean startsWith;
		
		public OreDictEntry(String name,int mass, boolean startsWith) {
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
	public static class CompostableEntry implements ICompostRegistryEntry{
		@Override
		public boolean doesMatch(ItemStack stack) {
			return stack.getItem() instanceof ICompostable&&((ICompostable)stack.getItem()).isCompostable(stack);
		}

		@Override
		public int getCompostingMass(ItemStack stack) {
			return ((ICompostable)stack.getItem()).getCompostingMass(stack);
		}
	}
	
	public static void init(){
		if(!registered){
			initItems();
			registered=true;
		}
	}
	
	private static void initItems(){
		addEntry(new CompostableEntry());
		addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		addCompostingItem(new ItemStack(Blocks.cactus), 500);
		addCompostingItem(new ItemStack(Blocks.hay_block), 10700);
		addCompostingItem(new ItemStack(Items.reeds), 250);
		addCompostingItem(new ItemStack(Items.cake), 2400);
		addCompostingItem(new ItemStack(Blocks.vine), 300);
		addCompostingItem(new ItemStack(Blocks.red_flower), 250);
		addCompostingItem(new ItemStack(Blocks.yellow_flower), 250);
		addCompostingItem(new ItemStack(Items.feather), 110);
		addCompostingItem(new ItemStack(Blocks.pumpkin), 1400);
		addCompostingItem(new ItemStack(Items.egg), 230);
		addCompostingItem(new ItemStack(Items.slime_ball), 130);
		addCompostingItem(new ItemStack(Items.wheat_seeds), 170);
		addCompostingItem(new ItemStack(Items.melon_seeds), 100);
		addCompostingItem(new ItemStack(Items.pumpkin_seeds), 100);
		addCompostingItem(new ItemStack(Blocks.red_mushroom), 400);
		addCompostingItem(new ItemStack(Blocks.brown_mushroom), 400);
		addCompostingItem(new ItemStack(Blocks.waterlily), 400);
		addCompostingItem(new ItemStack(Items.nether_wart), 1100);
		addCompostingItem(new ItemStack(Items.dye), 227);
		addCompostingItem(new ItemStack(Items.bone), 230);
		addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		addEntry(new DoublePlantEntry());
		addEntry(new FoodEntry());
		addCompostingOre("crop", 1200, true);
		addCompostingOre("treeLeaves", 200, true);
		addCompostingOre("treeSapling", 800, true);
		addCompostingOre("seed", 130, true);
		addCompostingOre("crop", 1200, true);
	}
}
