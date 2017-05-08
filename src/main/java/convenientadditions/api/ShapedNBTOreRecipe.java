package convenientadditions.api;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Necro on 5/6/2017.
 */
public class ShapedNBTOreRecipe extends ShapedOreRecipe {
    public ShapedNBTOreRecipe(Block result, Object... recipe) {
        super(result, recipe);
    }

    public ShapedNBTOreRecipe(Item result, Object... recipe) {
        super(result, recipe);
    }

    public ShapedNBTOreRecipe(@Nonnull ItemStack result, Object... recipe) {
        super(result, recipe);
    }

    @Override
    protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror)
    {
        for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++)
        {
            for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++)
            {
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height)
                {
                    if (mirror)
                    {
                        target = input[width - subX - 1 + subY * width];
                    }
                    else
                    {
                        target = input[subX + subY * width];
                    }
                }

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if (target instanceof ItemStack)
                {
                    if (!OreDictionary.itemMatches((ItemStack)target, slot, false)||(((ItemStack) target).hasTagCompound()&&!ItemStack.areItemStackTagsEqual((ItemStack)target,slot)))
                    {
                        return false;
                    }
                }
                else if (target instanceof List)
                {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>)target).iterator();
                    while (itr.hasNext() && !matched)
                    {
                        ItemStack stack=itr.next();
                        matched = OreDictionary.itemMatches(stack, slot, false) && (!stack.hasTagCompound() || ItemStack.areItemStackTagsEqual(stack, slot));
                    }

                    if (!matched)
                    {
                        return false;
                    }
                }
                else if (target == null && !slot.isEmpty())
                {
                    return false;
                }
            }
        }

        return true;
    }
}
