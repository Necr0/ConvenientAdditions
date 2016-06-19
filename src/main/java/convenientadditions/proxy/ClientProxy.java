package convenientadditions.proxy;

import convenientadditions.block.charge.chargeTube.TileEntityChargeTube;
import convenientadditions.block.composter.TileEntityComposter;
import convenientadditions.entity.EntityLaunchingArrow;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.render.RenderChargeTube;
import convenientadditions.render.RenderComposter;
import convenientadditions.render.RenderLaunchingArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
    	RenderingRegistry.registerEntityRenderingHandler(EntityLaunchingArrow.class, new IRenderFactory() {
			@Override
			public Render createRenderFor(RenderManager manager) {
				return new RenderLaunchingArrow(manager);
			}
		});
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

