package convenientadditions.block.misc.powderkeg;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.CABlockContainer;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPowderKeg extends CABlockContainer {

    public BlockPowderKeg() {
        super(ModConstants.BlockNames.powderKeg,Material.WOOD);
        this.setHardness(2F).setResistance(3F);
        this.setSoundType(SoundType.WOOD).setCategory(EnumItemCategory.MISC);
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityPowderKeg();
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityPowderKeg) {
            dropItemHandler(world,pos,((TileEntityPowderKeg) te).inventory,true);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack current = player.getHeldItem(hand);
        TileEntity tileEntity=world.getTileEntity(pos);
        if (!world.isRemote && tileEntity!=null && tileEntity instanceof TileEntityPowderKeg) {
            TileEntityPowderKeg keg= (TileEntityPowderKeg) tileEntity;
            if (current.getItem() == Items.GUNPOWDER) {
                player.setHeldItem(hand, keg.inventory.insertItem(0,current,false));
            } else if (current.getItem() == Items.FLINT_AND_STEEL) {
                current.damageItem(1,player);
                if (explode(world, pos)) {
                    current.damageItem(1, player);
                }
            }else{
                player.sendMessage(new TextComponentString(keg.inventory.getStackInSlot(0).getCount() + Helper.localize("message." + ModConstants.Mod.MODID + ":gunpowderStored")));
            }
        }
        return true;
    }


    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player){
        TileEntity tileEntity=world.getTileEntity(pos);
        if (!world.isRemote && tileEntity!=null && tileEntity instanceof TileEntityPowderKeg) {
            Helper.insertOrDrop(player,((TileEntityPowderKeg)tileEntity).inventory.extractItem(0, player.isSneaking()?64:1,false));
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos from) {
        if (!worldIn.isRemote)
            if (Helper.checkForFire(worldIn, pos) || worldIn.isBlockIndirectlyGettingPowered(pos) > 0)
                this.explode(worldIn, pos);
    }

    @Override
    public void onEntityCollidedWithBlock(World w, BlockPos pos, IBlockState s, Entity e) {
        if (e instanceof EntityArrow && !w.isRemote) {
            EntityArrow entityarrow = (EntityArrow) e;

            if (entityarrow.isBurning())
                this.explode(w, pos);
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion p_5) {
        explode(world, pos);
    }

    public boolean explode(World w, BlockPos pos) {
        if (w.getTileEntity(pos) != null && w.getTileEntity(pos) instanceof TileEntityPowderKeg) {
            TileEntityPowderKeg k = (TileEntityPowderKeg) w.getTileEntity(pos);
            int amount=k.inventory.getStackInSlot(0).getCount();
            if (amount > 0 && !w.isRemote) {
                float strenght = (float) amount / 1.215F;
                k.inventory.extractItem(0,64,false);
                w.setBlockToAir(pos);
                w.createExplosion(null, (double) pos.getX() + .5, (double) pos.getY() + .5, (double) pos.getZ() + .5, strenght, true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canDropFromExplosion(Explosion e) {
        return false;
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te=world.getTileEntity(pos);
        if(te!=null&&te instanceof TileEntityPowderKeg && stack.hasTagCompound()){
            NBTTagCompound nbt=stack.getTagCompound();
            if(nbt.hasKey("CONTAINED_ITEMS") && nbt.getTag("CONTAINED_ITEMS") instanceof NBTTagCompound)
                ((TileEntityPowderKeg) te).inventory.deserializeNBT(nbt.getCompoundTag("CONTAINED_ITEMS"));
        }
    }

    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<>();
        ItemStack stack = new ItemStack(this);
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        TileEntity te=world.getTileEntity(pos);
        if(te!=null&&te instanceof TileEntityPowderKeg){
            int count=((TileEntityPowderKeg) te).inventory.getStackInSlot(0).getCount();
            if(count>0){
                stack.getTagCompound().setTag("CONTAINED_ITEMS", ((TileEntityPowderKeg) te).inventory.serializeNBT());
                stack.getTagCompound().setInteger("CONTAINED_ITEMS_COUNT", ((TileEntityPowderKeg) te).inventory.getStackInSlot(0).getCount());
            }else
                stack.setTagCompound(null);
        }
        ret.add(stack);
        return ret;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        super.addInformation(stack,player,tooltip,advanced);
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("CONTAINED_ITEMS_COUNT")){
            tooltip.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.powderKeg + ".containedGunpowder",stack.getTagCompound().getInteger("CONTAINED_ITEMS_COUNT")));
        }
    }
}
