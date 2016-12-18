package convenientadditions.api.registry.transmutationTome;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ITransmutationTomeLookupProvider {
    List<Lookup> getLookups();

    final class Lookup{
        private ItemStack base, transmutator, result;
        private int level;

        public Lookup(ItemStack base,ItemStack transmutator,ItemStack result,int level){
            this.base = base;
            this.transmutator = transmutator;
            this.result = result;
            this.level = level;
        }

        public ItemStack getBase() {
            return base;
        }

        public ItemStack getTransmutator() {
            return transmutator;
        }

        public ItemStack getResult() {
            return result;
        }

        public int getLevel() {
            return level;
        }
    }
}
