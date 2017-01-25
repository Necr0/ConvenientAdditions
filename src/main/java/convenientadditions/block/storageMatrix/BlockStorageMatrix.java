package convenientadditions.block.storageMatrix;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.CABlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockStorageMatrix extends CABlockMachine{

    public BlockStorageMatrix() {
        super(ModConstants.BlockNames.storageMatrixBlockName,Material.IRON);
        this.setHardness(4F).setResistance(8F);
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityStorageMatrix();
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        ItemStack stack = new ItemStack(this);
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        TileEntity te=world.getTileEntity(pos);
        if(te!=null&&te instanceof TileEntityStorageMatrix){
            stack.getTagCompound().setTag("CONTAINED_ITEMS", ((TileEntityStorageMatrix) te).inventory.serializeNBT());
        }

        world.spawnEntity(new EntityItem(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, stack));
        NonNullList<ItemStack> arr = NonNullList.create();
        arr.add(stack);
        world.setBlockToAir(pos);
        return arr;
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te=world.getTileEntity(pos);
        if(te!=null&&te instanceof TileEntityStorageMatrix && stack.hasTagCompound()){
            NBTTagCompound nbt=stack.getTagCompound();
            if(nbt.hasKey("CONTAINED_ITEMS") && nbt.getTag("CONTAINED_ITEMS") instanceof NBTTagCompound)
                ((TileEntityStorageMatrix) te).inventory.deserializeNBT(nbt.getCompoundTag("CONTAINED_ITEMS"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        super.addInformation(stack,player,tooltip,advanced);
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("CONTAINED_ITEMS")){
            tooltip.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.storageMatrixBlockName + "HoldingInventory"));
        }
    }
}
