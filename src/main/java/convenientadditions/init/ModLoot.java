package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.config.ModConfigTools;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 2/27/2017.
 */
public class ModLoot {
    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event){
        ResourceLocation n=event.getName();
        if(n.equals(LootTableList.CHESTS_END_CITY_TREASURE)){
            if(ModConfigTools.mobCatcher_loot)
                event.getTable().getPool("main").addEntry(new LootEntryItem(ModItems.itemMobCatcherMega,1, 3, new LootFunction[0], new LootCondition[0], ModConstants.Mod.MODID+":megaMCD"));
        }else if(n.equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)){
            if(ModConfigTools.mobCatcher_loot)
                event.getTable().getPool("main").addEntry(new LootEntryItem(ModItems.itemMobCatcherHyper,1, 3, new LootFunction[0], new LootCondition[0], ModConstants.Mod.MODID+":hyperMCD"));
        }else if(n.equals(LootTableList.CHESTS_SIMPLE_DUNGEON)){
            if(ModConfigTools.mobCatcher_loot){
                event.getTable().getPool("main").addEntry(new LootEntryItem(ModItems.itemMobCatcherSuper,1, 3, new LootFunction[0], new LootCondition[0], ModConstants.Mod.MODID+":superMCD"));
                event.getTable().getPool("main").addEntry(new LootEntryItem(ModItems.itemMobCatcherRegular,3, -2, new LootFunction[0], new LootCondition[0], ModConstants.Mod.MODID+":regularMCD"));
            }
        }
    }
}
