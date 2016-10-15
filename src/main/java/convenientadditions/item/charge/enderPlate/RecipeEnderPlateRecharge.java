package convenientadditions.item.charge.enderPlate;

import java.util.ArrayList;
import java.util.List;

import convenientadditions.init.ModConfig;
import convenientadditions.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeEnderPlateRecharge implements IRecipe {

    public ItemStack getRecipeOutput()
    {
        return null;
    }

    public List<ItemStack> getStacks(InventoryCrafting inv)
    {
        List<ItemStack> list = new ArrayList<ItemStack>();

        for (int i = 0; i < inv.getHeight(); ++i)
        {
            for (int j = 0; j < inv.getWidth(); ++j)
            {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (itemstack != null)
                {
                    list.add(itemstack);
                }
            }
        }
        return list;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting inv)
    {
        ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
        }

        return aitemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        List<ItemStack> list = getStacks(inv);
        
        if(list.size()!=2)
        	return false;
        
        boolean slate=false;
        boolean eye=false;
        
        for(ItemStack i:list){
        	if(i.getItem()==ModItems.itemEnderPlate&&!ModItems.itemEnderPlate.isActive(i)&&ModItems.itemEnderPlate.getCharge(i)<ModItems.itemEnderPlate.getChargeCapacity(i))
        		slate=true;
        	else if(i.getItem()==Items.ENDER_EYE)
        		eye=true;
        }
        
        return eye&&slate;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        List<ItemStack> list = getStacks(inv);
        
        for(ItemStack i:list){
        	if(i.getItem()==ModItems.itemEnderPlate&&!ModItems.itemEnderPlate.isActive(i)&&ModItems.itemEnderPlate.getCharge(i)<ModItems.itemEnderPlate.getChargeCapacity(i)){
        		ItemStack o=i.copy();
        		ModItems.itemEnderPlate.chargeItem(o,
        				(int)(ModItems.itemEnderPlate.getChargeCapacity(o)*ModConfig.enderPlate_enderEyeRechargePercentage));
        		return o;
        	}
        }
        return null;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return 2;
    }
}
