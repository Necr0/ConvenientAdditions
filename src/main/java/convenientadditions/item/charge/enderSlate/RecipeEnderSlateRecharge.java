package convenientadditions.item.charge.enderSlate;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import convenientadditions.init.ModConfig;
import convenientadditions.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.World;

public class RecipeEnderSlateRecharge implements IRecipe {

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
        
        if(!(1<list.size()&&list.size()<3))
        	return false;
        
        boolean slate=false;
        boolean eye=false;
        
        for(ItemStack i:list){
        	if(i.getItem()==ModItems.itemEnderSlate&&!ModItems.itemEnderSlate.isActive(i)&&ModItems.itemEnderSlate.getCharge(i)<ModItems.itemEnderSlate.getChargeCapacity(i))
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
        	if(i.getItem()==ModItems.itemEnderSlate&&!ModItems.itemEnderSlate.isActive(i)&&ModItems.itemEnderSlate.getCharge(i)<ModItems.itemEnderSlate.getChargeCapacity(i)){
        		ItemStack o=i.copy();
        		ModItems.itemEnderSlate.chargeItem(o,
        				(int)(ModItems.itemEnderSlate.getChargeCapacity(o)*ModConfig.enderSlate_enderEyeRechargePercentage));
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
