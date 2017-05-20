package convenientadditions.block.misc.seedbox.entries;

import convenientadditions.api.registry.seedbox.ISeedBoxItemBehaviourRegistryEntry;
import convenientadditions.config.ModConfigMisc;
import convenientadditions.init.ModCAAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeedBoxFeedEntry implements ISeedBoxItemBehaviourRegistryEntry {
    public final String[] INTERNAL_ITEMS;

    public SeedBoxFeedEntry(){
        INTERNAL_ITEMS=new String[]{
                Items.WHEAT_SEEDS.getRegistryName().toString(),
                Items.PUMPKIN_SEEDS.getRegistryName().toString(),
                Items.BEETROOT_SEEDS.getRegistryName().toString(),
                Items.MELON_SEEDS.getRegistryName().toString(),
                Items.WHEAT.getRegistryName().toString(),
                Items.GOLDEN_CARROT.getRegistryName().toString(),
                Items.GOLDEN_APPLE.getRegistryName().toString(),
                Items.CARROT.getRegistryName().toString(),
                Items.BEETROOT.getRegistryName().toString(),
                Items.POTATO.getRegistryName().toString(),
                Items.FISH.getRegistryName().toString(),
                Items.PORKCHOP.getRegistryName().toString(),
                Items.COOKED_PORKCHOP.getRegistryName().toString(),
                Items.BEEF.getRegistryName().toString(),
                Items.COOKED_BEEF.getRegistryName().toString(),
                Items.CHICKEN.getRegistryName().toString(),
                Items.COOKED_CHICKEN.getRegistryName().toString(),
                Items.MUTTON.getRegistryName().toString(),
                Items.COOKED_MUTTON.getRegistryName().toString(),
                Items.RABBIT.getRegistryName().toString(),
                Items.COOKED_RABBIT.getRegistryName().toString(),
                Blocks.YELLOW_FLOWER.getRegistryName().toString(),
                Blocks.HAY_BLOCK.getRegistryName().toString()
        };
    }

    @Override
    public boolean hasSpecialBehaviour(ItemStack stack) {
        ArrayList<String> items=new ArrayList<>();
        items.addAll(Arrays.asList(INTERNAL_ITEMS));
        items.addAll(Arrays.asList(ModConfigMisc.seedBox_autoFeedItems));
        return items.contains(stack.getItem().getRegistryName().toString());
    }

    @Override
    public void getDiscriminators(ItemStack stack, List<String> behaviours) {
        behaviours.add(ModCAAPI.BEHAVIOUR_AUTO_FEED);
    }

}
