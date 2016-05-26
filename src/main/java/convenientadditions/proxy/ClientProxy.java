package convenientadditions.proxy;

import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.render.RenderChargeTube;
import convenientadditions.render.RenderComposter;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.charge.TileEntityChargeTube;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
    //public static int renderPass;
    
	public Side getSide(){
		return Side.CLIENT;
	}
    
    @Override
    public void registerRenderers()
    {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComposter.class, new RenderComposter());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChargeTube.class, new RenderChargeTube());
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
    
    @Override
    public void InitModels()
    {
    	ModItems.initModelLoader();
    	ModBlocks.initModelLoader();
    }
}

