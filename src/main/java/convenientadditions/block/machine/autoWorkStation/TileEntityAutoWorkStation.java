package convenientadditions.block.machine.autoWorkStation;

import convenientadditions.api.block.tileentity.IConfigurable;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.api.inventory.InventoryStackHandlerCrafting;
import convenientadditions.api.util.Helper;
import convenientadditions.api.util.ItemHelper;
import convenientadditions.base.block.CATileEntity;
import convenientadditions.block.machine.setProvider.TileEntitySetProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashMap;

/**
 * Created by Necro on 5/7/2017.
 */
public class TileEntityAutoWorkStation extends CATileEntity implements ITickable, IConfigurable {
    public HashMap<EnumFacing, TileEntitySetProvider.EnumOutletMode> outletSides = new HashMap<>();

    public ItemStackHandlerAutoSave inv=addAutoSavable(new ItemStackHandlerAutoSave(this,18));
    public ItemStackHandlerAutoSave grid=addAutoSavable(new ItemStackHandlerAWSFiltered(this,9).setName("GRID"));
    public InventoryStackHandlerCrafting craftMatrix;
    public boolean keepItem=false;
    public boolean filteredInput=false;
    public byte craftMode=0;//0=pulse,1=high,2=low,3=always,4=never
    public boolean powered=false;
    public int ticks=0;

    public TileEntityAutoWorkStation(){
        craftMatrix=new InventoryStackHandlerCrafting(null,this.grid,3,3);
        for (EnumFacing f : EnumFacing.VALUES) {
            outletSides.put(f, TileEntitySetProvider.EnumOutletMode.disabled);
        }
        outletSides.put(EnumFacing.DOWN, TileEntitySetProvider.EnumOutletMode.output);
        outletSides.put(EnumFacing.UP, TileEntitySetProvider.EnumOutletMode.input);
    }

    @Override
    public void update() {
        if (getWorld().isRemote)
            return;

        if(++ticks<10)
            return;
        ticks=0;

        if (craftMode == 1 && getWorld().isBlockIndirectlyGettingPowered(getPos()) > 0) {
            tryOperate();
        } else if (craftMode == 2 && getWorld().isBlockIndirectlyGettingPowered(getPos()) == 0) {
            tryOperate();
        } else if (craftMode == 3) {
            tryOperate();
        }
    }

    public void tryOperate(){
        ItemStack out=CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world);
        if(!out.isEmpty()&&canOperate(out))
            operate(out);
    }

    public boolean canOperate(ItemStack out){
        if(keepItem){
            for (ItemStack i:grid.getStacks()){
                if(i.getCount()==1)
                    return false;
            }
        }
        if(!ItemHandlerHelper.insertItem(inv,out,true).isEmpty())
            return false;
        NonNullList<ItemStack> rem=CraftingManager.getInstance().getRemainingItems(this.craftMatrix,this.world);
        for (int i = 0; i < rem.size(); i++)
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = rem.get(i);
            if (!itemstack.isEmpty())
            {
                itemstack = grid.getStackInSlot(i).copy();
                itemstack.shrink(1);
            }
            if (!itemstack1.isEmpty())
            {
                if (!itemstack.isEmpty() && !ItemHelper.match(itemstack,itemstack1,false,false) && !ItemHandlerHelper.insertItem(inv,itemstack1,true).isEmpty())
                    return false;
            }
        }
        return true;
    }

    public void operate(ItemStack out){
        ItemStack s1=ItemHandlerHelper.insertItem(inv,out,false);
        if(!s1.isEmpty())
            Helper.spawnItem(this.world,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5,s1);
        NonNullList<ItemStack> rem=CraftingManager.getInstance().getRemainingItems(this.craftMatrix,this.world);
        for (int i = 0; i < rem.size(); i++)
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = rem.get(i);

            if (!itemstack.isEmpty())
            {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }

            if (!itemstack1.isEmpty())
            {
                if (itemstack.isEmpty())
                {
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (ItemHelper.match(itemstack,itemstack1,false,false))
                {
                    itemstack1.grow(itemstack.getCount());
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }else{
                    ItemStack s2=ItemHandlerHelper.insertItem(inv,itemstack1,true);
                    if(!s2.isEmpty())
                        Helper.spawnItem(this.world,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5,s2);
                }
            }
        }
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        for (EnumFacing f : EnumFacing.VALUES) {
            if (nbt.hasKey("OUTLET_" + f.ordinal())) {
                outletSides.put(f, TileEntitySetProvider.EnumOutletMode.fromByte(nbt.getByte("OUTLET_" + f.ordinal())));
            }
        }
        if (nbt.hasKey("FILTERINPUT"))
            filteredInput = nbt.getBoolean("FILTERINPUT");
        if (nbt.hasKey("CRAFTMODE"))
            craftMode = nbt.getByte("CRAFTMODE");
        if (nbt.hasKey("KEEPITEM"))
            keepItem = nbt.getBoolean("KEEPITEM");
        if (nbt.hasKey("POWERED"))
            powered = nbt.getBoolean("POWERED");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        for (EnumFacing f : EnumFacing.VALUES) {
            nbt.setByte("OUTLET_" + f.ordinal(), (byte) outletSides.get(f).ordinal());
        }
        nbt.setBoolean("FILTERINPUT", filteredInput);
        nbt.setByte("CRAFTMODE", craftMode);
        nbt.setBoolean("KEEPITEM", keepItem);
        nbt.setBoolean("POWERED", powered);
        return nbt;
    }

    public void updateRS(boolean power) {
        if (powered != power) {
            if (power && craftMode == 0)
                tryOperate();
            powered = power;
        }
    }

    public void setFilterInput(boolean filterInput) {
        this.filteredInput = filterInput;
        causeUpdate(0);
    }

    public void setCraftMode(byte resetMode) {
        this.craftMode = resetMode;
        causeUpdate(0);
    }

    public void setKeepItem(boolean ignoreOutput) {
        this.keepItem=ignoreOutput;
        causeUpdate(0);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return (capability == ITEM_HANDLER_CAPABILITY && outletSides.get(facing) != TileEntitySetProvider.EnumOutletMode.disabled) || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return (capability == ITEM_HANDLER_CAPABILITY && outletSides.get(facing) != TileEntitySetProvider.EnumOutletMode.disabled) ?
                (outletSides.get(facing) == TileEntitySetProvider.EnumOutletMode.input ? (T) grid : (T) inv)
                : super.getCapability(capability, facing);
    }

    @Override
    public boolean configureSide(EnumFacing f) {
        outletSides.put(f, TileEntitySetProvider.EnumOutletMode.fromByte((byte) (outletSides.get(f).ordinal() + 1)));
        causeUpdate(0);
        return true;
    }

    public enum EnumOutletMode implements IStringSerializable {
        disabled,
        output,
        input;

        public static TileEntitySetProvider.EnumOutletMode fromByte(byte bt) {
            return TileEntitySetProvider.EnumOutletMode.values()[bt % values().length];
        }

        @Override
        public String getName() {
            return this.name();
        }
    }
}
