package convenientadditions.block.machine.proximitySensor;

import convenientadditions.base.block.tileentity.CATileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.ITickable;

public class TileEntityProximitySensor extends CATileEntity implements ITickable {
    public double range=15d;
    public double[] strenghts;

    public int power=0;

    public TileEntityProximitySensor() {
        recalculateStrenghts();
    }

    @Override
    public void update() {
        if(world.isRemote)
            return;
        EntityPlayer p=world.getClosestPlayer(pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5,range,false);
        if(p!=null){
            int strenght=getStrenght(p.getDistanceSq(pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5));
            if(strenght!=power){
                power=strenght;
                world.notifyNeighborsOfStateChange(pos, getBlockType(), false);
            }
        }else if(power!=0){
            power=0;
            world.notifyNeighborsOfStateChange(pos, getBlockType(), false);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("RANGE") && nbt.getTag("RANGE") instanceof NBTTagDouble)
            range=nbt.getDouble("RANGE");
        recalculateStrenghts();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setDouble("RANGE", range);
        return nbt;
    }

    public void recalculateStrenghts(){
        strenghts=new double[15];
        double segment=range/15;
        for(int i=0;i<15;i++){
            strenghts[i]=(range-(segment*i))*(range-(segment*i));
        }
    }

    public int getStrenght(double distance){
        int i;
        for(i=0;i<15;i++){
            if(strenghts[i]<distance)
                break;
        }
        return i;
    }
}
