package conveniencecore;

import conveniencecore.behaviours.BehaviourRegistry;
import conveniencecore.entity.EntitySpecialItem;
import conveniencecore.item.FuelItemFuelHandler;
import conveniencecore.item.PlayerInventoryTickHandler;
import conveniencecore.network.ModNetworking;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid=ConvenienceCore.MOD_ID,version=ConvenienceCore.VERSION)
public class ConvenienceCore {
	public static final String MOD_ID="conveniencecore";
	public static final String VERSION="1.0";
	
    @Instance(MOD_ID)
    public static ConvenienceCore INSTANCE;
	
	public static final String SPECIALITEMENTITYNAME="specialItem";
    public static final int SPECIALITEMENTITYID = 1;
	
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	ModNetworking.init();
    	FuelItemFuelHandler.init();
    	PlayerInventoryTickHandler.init();
    	BehaviourRegistry.init();
    	EntityRegistry.registerModEntity(EntitySpecialItem.class, SPECIALITEMENTITYNAME, SPECIALITEMENTITYID, INSTANCE, 128, 5, true);
    }
}
