package convenientadditions.compat.gbook;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.config.ModConfigCompat;
import gigaherz.guidebook.client.BookRegistryEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Necro on 5/8/2017.
 */
@Mod.EventBusSubscriber(modid = ModConstants.Mod.MODID)
public class CAGbookBookRegistry {
    public static final String TAG_BOOK_RECEIVED="CONVADD_BOOK_RECEIVED";

    @Optional.Method(modid="gbook")
    @SubscribeEvent
    public static void registerBook(BookRegistryEvent event) {
        event.register(new ResourceLocation(ModConstants.Mod.MODID+":xml/book.xml"));
    }

    @Optional.Method(modid="gbook")
    @SubscribeEvent
    public static void onFirstSpawn(EntityJoinWorldEvent event){
        if(!ModConfigCompat.gbook_onFirstSpawn)
            return;
        if(!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer){
            EntityPlayer p = (EntityPlayer)event.getEntity();
            NBTTagCompound tag = Helper.getPersistentTag(p,ModConstants.Mod.MODID);
            if(!tag.getBoolean(TAG_BOOK_RECEIVED)){
                Helper.insertOrDrop(p,GameRegistry.makeItemStack("gbook:guidebook",0,1,"{Book:\"convenientadditions:xml/book.xml\"}"));
                tag.setBoolean(TAG_BOOK_RECEIVED,true);
            }
        }
    }
}
