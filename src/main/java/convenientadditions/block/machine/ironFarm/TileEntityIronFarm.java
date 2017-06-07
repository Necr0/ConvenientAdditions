package convenientadditions.block.machine.ironFarm;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSaveOutputOnly;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public class TileEntityIronFarm extends CATileEntity implements ITickable {
    public int tier=0;
    public int progress=0;
    public ItemStackHandlerAutoSaveOutputOnly inv=addCapability(addAutoSavable(new ItemStackHandlerAutoSaveOutputOnly(this)));
    public ItemStack iron=new ItemStack(Items.IRON_INGOT);

    public TileEntityIronFarm(){}

    public TileEntityIronFarm(int tier){this.tier=tier;}

    @Override
    public void update() {
        if (world.isRemote)
            return;
        int timeNeeded=3000/(int)Math.pow(4,tier);
        int count=inv.getStackInSlot(0).getCount();

        if(count<iron.getMaxStackSize() && ++progress >= timeNeeded){
            progress=0;
            inv.setStackInSlot(0,new ItemStack(Items.IRON_INGOT,count+1));
        }
    }
}
