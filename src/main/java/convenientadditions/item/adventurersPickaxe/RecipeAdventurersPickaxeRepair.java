package convenientadditions.item.adventurersPickaxe;

import convenientadditions.init.ModItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdventurersPickaxeRepair implements IRecipe {

    public ItemStack getRecipeOutput() {
        return null;
    }

    public List<ItemStack> getStacks(InventoryCrafting inv) {
        List<ItemStack> list = new ArrayList<ItemStack>();

        for (int i = 0; i < inv.getHeight(); ++i) {
            for (int j = 0; j < inv.getWidth(); ++j) {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (itemstack != null) {
                    list.add(itemstack);
                }
            }
        }
        return list;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
        }

        return aitemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World worldIn) {
        List<ItemStack> list = getStacks(inv);

        if (list.size() != 2)
            return false;

        ItemStack pick = null;

        for (ItemStack i : list) {
            if (i.getItem() == ModItems.itemAdventurersPickaxe) {
                if (i.getItemDamage() != 0) {
                    pick = i.copy();
                }
            }
        }
        if (pick == null)
            return false;

        for (ItemStack i : list) {
            String ore = ModItems.itemAdventurersPickaxe.getRepairMaterial(pick);
            if (OreDictionary.containsMatch(false, OreDictionary.getOres(ore), i))
                return true;
        }
        return false;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        List<ItemStack> list = getStacks(inv);

        ItemStack pick = null;

        for (ItemStack i : list) {
            if (i.getItem() == ModItems.itemAdventurersPickaxe) {
                if (i.getItemDamage() != 0) {
                    pick = i.copy();
                }
            }
        }
        if (pick == null) {
            return null;
        } else {
            int dur = (int) ModItems.itemAdventurersPickaxe.getToolProperty(pick, "durability");
            int newDmg = pick.getItemDamage() - (dur / 2);
            pick.setItemDamage(newDmg >= 0 ? newDmg : 0);
            return pick;
        }
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize() {
        return 2;
    }

}
