package convenientadditions.proxy;

import convenientadditions.api.util.GuiHelper;
import convenientadditions.block.misc.composter.TileEntityComposter;
import convenientadditions.block.misc.composter.render.RenderComposter;
import convenientadditions.block.misc.displayCase.TESRDisplayCase;
import convenientadditions.block.misc.displayCase.TileEntityDisplayCase;
import convenientadditions.compat.waila.ConvAddWailaPlugin;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import convenientadditions.entity.launchingArrow.RenderLaunchingArrow;
import convenientadditions.entity.mobCatcher.EntityMobCatcher;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;


public class ClientProxy extends CommonProxy {
    //public static int renderPass;

    public Side getSide() {
        return Side.CLIENT;
    }

    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComposter.class, new RenderComposter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCase.class, new TESRDisplayCase());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaunchingArrow.class, manager -> new RenderLaunchingArrow(manager));
        RenderingRegistry.registerEntityRenderingHandler(EntityMobCatcher.class, manager -> new RenderSnowball<>(manager,ModItems.itemMobCatcherRegular,GuiHelper.getRenderItem()));
    }

    @Override
    public void initWaila(){
        ConvAddWailaPlugin.init();
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().world;
    }

    @Override
    public void InitModels() {
        ModItems.initModelLoader();
        ModBlocks.initModelLoader();
    }
}

