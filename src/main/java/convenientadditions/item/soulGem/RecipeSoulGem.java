package convenientadditions.item.soulGem;

import convenientadditions.compat.jei.crafting.ICustomCraftingRecipe;
import convenientadditions.init.ModItems;
import convenientadditions.item.tools.mobCatcher.ItemMobCatcher;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Necro on 5/5/2017.
 */
public class RecipeSoulGem implements IRecipe, ICustomCraftingRecipe {
    List<ItemStack> inputs;
    List<ItemStack> results;

    public RecipeSoulGem(){
        ItemStack mcdR=new ItemStack(ModItems.itemMobCatcherRegular);
        ItemStack mcdS=new ItemStack(ModItems.itemMobCatcherSuper);
        ItemStack mcdH=new ItemStack(ModItems.itemMobCatcherHyper);
        ItemStack mcdM=new ItemStack(ModItems.itemMobCatcherHyper);
        inputs= Arrays.asList(ModItems.itemMobCatcherRegular.setMob(mcdR.copy(),"minecraft:sheep"),
                ModItems.itemMobCatcherRegular.setMob(mcdS.copy(),"minecraft:pig"),
                ModItems.itemMobCatcherRegular.setMob(mcdH.copy(),"minecraft:creeper"),
                ModItems.itemMobCatcherRegular.setMob(mcdM.copy(),"minecraft:villager"),
                ModItems.itemMobCatcherRegular.setMob(mcdR.copy(),"minecraft:bat"),
                ModItems.itemMobCatcherRegular.setMob(mcdS.copy(),"minecraft:villager_golem"),
                ModItems.itemMobCatcherRegular.setMob(mcdH.copy(),"minecraft:spider"),
                ModItems.itemMobCatcherRegular.setMob(mcdH.copy(),"minecraft:enderman"));
        ItemStack gem=new ItemStack(ModItems.itemSoulGem);
        results=Arrays.asList(ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:sheep"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:pig"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:creeper"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:villager"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:bat"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:villager_golem"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:spider"),
                ModItems.itemSoulGem.setEntityId(gem.copy(),"minecraft:enderman"));
    }

    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public List<ItemStack> getStacks(InventoryCrafting inv) {
        List<ItemStack> list = new ArrayList<>();

        for (int i = 0; i < inv.getHeight(); ++i) {
            for (int j = 0; j < inv.getWidth(); ++j) {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (!itemstack.isEmpty()) {
                    list.add(itemstack);
                }
            }
        }
        return list;
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> aitemstack = NonNullList.withSize(9,ItemStack.EMPTY);

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack.set(i,net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
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

        ItemStack catcher = null;

        for (ItemStack i : list) {
            if (i.getItem() instanceof ItemMobCatcher) {
                if (((ItemMobCatcher)i.getItem()).getEntityId(i)!=null) {
                    catcher = i.copy();
                }
            }
        }
        if (catcher == null)
            return false;

        for (ItemStack i : list) {
            for (int id : OreDictionary.getOreIDs(i)) {
                String name=OreDictionary.getOreName(id);
                if (name.equals("gemDiamond"))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        List<ItemStack> list = getStacks(inv);

        String catcher_id = null;

        for (ItemStack i : list) {
            if (i.getItem() instanceof ItemMobCatcher) {
                String tmp_id=((ItemMobCatcher)i.getItem()).getEntityId(i);
                if (tmp_id!=null) {
                    catcher_id = tmp_id;
                }
            }
        }
        if (catcher_id == null) {
            return ItemStack.EMPTY;
        } else {
            return ModItems.itemSoulGem.setEntityId(new ItemStack(ModItems.itemSoulGem),catcher_id);
        }
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public List<Object> getInputs() {
        List<Object> l=new ArrayList<>();
        l.add(inputs);
        l.add("gemDiamond");
        return l;
    }

    @Override
    public List<ItemStack> getResult() {
        return results;
    }

    @Override
    public boolean isShapeless() {
        return true;
    }
}
