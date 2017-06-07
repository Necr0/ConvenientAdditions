package convenientadditions.api.capabilities;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Necro on 6/5/2017.
 */
public interface IAutoSavable{
    void loadFromTagCompound(NBTTagCompound nbt);
    void saveToTagCompound(NBTTagCompound nbt);
}
