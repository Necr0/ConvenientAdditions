package convenientadditions.api.item;

import convenientadditions.api.IMatcher;
import net.minecraft.item.ItemStack;

/**
 * Created by Necro on 3/18/2017.
 */
public interface IMatcherProvider {

    boolean hasMatcher(ItemStack stack);

    IMatcher getMatcher(ItemStack stack);

    default boolean doesMatch(ItemStack stack, IMatcher matcher) {
        return IMatcher.matches(((IMatcherProvider) stack.getItem()).getMatcher(stack), matcher);
    }
}
