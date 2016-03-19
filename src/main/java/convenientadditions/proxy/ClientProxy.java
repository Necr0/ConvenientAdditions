package convenientadditions.proxy;

import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import convenientadditions.init.ModBlocks;
import convenientadditions.render.RenderChargeTube;
import convenientadditions.render.RenderComposter;
import convenientadditions.render.item.RenderComposterItem;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.charge.TileEntityChargeTube;

public class ClientProxy extends CommonProxy
{
    public static int renderPass;
    
    @Override
    public void registerRenderers()
    {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComposter.class, new RenderComposter());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChargeTube.class, new RenderChargeTube());
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

