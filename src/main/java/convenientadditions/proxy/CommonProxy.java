package convenientadditions.proxy;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	public Side getSide(){
		return Side.SERVER;
	}
	
	public boolean isRemote(){
		return getSide()!=Side.SERVER;
	}
	
    public void registerRenderers()
    {
    }

    public void registerEntities()
    {
    	EntityRegistry.registerModEntity(EntityLaunchingArrow.class, Reference.launchingArrowEntityName, Reference.lauchingArrowEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
    }

    public World getClientWorld()
    {
        return null;
    }

    public void InitRendering(){}
    public void InitModels(){}
}
