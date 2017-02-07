package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.entity.specialitem.EntitySpecialItem;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init(){
        EntityRegistry.registerModEntity(new ResourceLocation(ModConstants.Mod.MODID,ModConstants.Entities.launchingArrowEntityName), EntityLaunchingArrow.class, ModConstants.Entities.launchingArrowEntityName, ModConstants.Entities.lauchingArrowEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModConstants.Mod.MODID,ModConstants.Entities.specialItemEntityName), EntitySpecialItem.class, ModConstants.Entities.specialItemEntityName, ModConstants.Entities.specialItemEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
    }
}
