package convenientadditions.proxy;

import convenientadditions.init.ModBlocks;
import convenientadditions.render.RenderComposter;
import convenientadditions.render.RenderCub3dFrame;
import convenientadditions.render.item.RenderComposterItem;
import convenientadditions.render.item.RenderCub3dFrameItem;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.TileEntityCub3dFrame;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    public static int renderPass;
    
    @Override
    public void registerRenderers()
    {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCub3dFrame.class, new RenderCub3dFrame());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComposter.class, new RenderComposter());
        MinecraftForgeClient.registerItemRenderer(ItemBlock.getItemFromBlock(ModBlocks.frameBlock), new RenderCub3dFrameItem());
        MinecraftForgeClient.registerItemRenderer(ItemBlock.getItemFromBlock(ModBlocks.composterBlock), new RenderComposterItem());
    }

    @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }
    
    @Override
    public void InitRendering()
    {
    }
}

