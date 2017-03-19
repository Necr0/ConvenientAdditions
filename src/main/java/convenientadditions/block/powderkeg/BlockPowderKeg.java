package convenientadditions.block.powderkeg;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.CABlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPowderKeg extends CABlockContainer {

    public BlockPowderKeg() {
        super(ModConstants.BlockNames.powderKeg,Material.WOOD);
        this.setHardness(2F).setResistance(3F);
        this.setSoundType(SoundType.WOOD);
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityPowderKeg();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }

    private void dropItems(World world, BlockPos pos) {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityPowderKeg && !world.isRemote) {
            TileEntityPowderKeg keg = (TileEntityPowderKeg) world.getTileEntity(pos);
            ItemStack item = keg.inventory.extractItem(0,64,false);
            if(item.isEmpty())
                return;
            float rx = world.rand.nextFloat() * 0.8F + 0.1F;
            float ry = world.rand.nextFloat() * 0.8F + 0.1F;
            float rz = world.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, item);
            float factor = 0.05F;
            entityItem.motionX = world.rand.nextGaussian() * factor;
            entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = world.rand.nextGaussian() * factor;
            world.spawnEntity(entityItem);
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
                float strenght = (float) amount / 1.45F;
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
}
