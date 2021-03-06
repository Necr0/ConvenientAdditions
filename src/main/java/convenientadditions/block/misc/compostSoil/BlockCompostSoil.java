package convenientadditions.block.misc.compostSoil;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.block.CABlock;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockCompostSoil extends CABlock {
    public static final PropertyInteger DEGRADATION = PropertyInteger.create("degradation", 0, 10);

    public BlockCompostSoil() {
        super(ModConstants.BlockNames.compostSoil,Material.GROUND);
        this.setTickRandomly(true).setHardness(0.5F);
        this.setSoundType(SoundType.GROUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(DEGRADATION, Integer.valueOf(0)));
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plantable) {
        BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        EnumPlantType plantType = plantable.getPlantType(world, plantPos);

        if (plantable instanceof BlockBush)
            return true;

        switch (plantType) {
            case Desert:
                return false;
            case Nether:
                return false;
            case Crop:
                return false;
            case Cave:
                return true;
            case Plains:
                return true;
            case Water:
                return false;
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
        }

        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack current = player.inventory.getStackInSlot(player.inventory.currentItem);
        if (current == null || !(current.getItem() instanceof ItemHoe))
            return false;
        BlockPos posU = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        IBlockState stateU = world.getBlockState(posU);
        if (stateU.getBlock().isAir(stateU, world, posU)) {
            if (!world.isRemote) {
                current.damageItem(1, player);
                world.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getStateFromMeta(this.getMetaFromState(state)), 3);
            }
            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }

    @Override
    public boolean isFertile(World world, BlockPos pos) {
        return true;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random r) {
        if (!world.isRemote) {
            BlockPos posU = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            IBlockState s = world.getBlockState(posU);
            Block b = s.getBlock();
            int deg = state.getValue(DEGRADATION);
            boolean flag=false;
            if (b != null && (b instanceof IPlantable || b instanceof IGrowable)) {
                if(b instanceof IGrowable)
                    flag=((IGrowable)b).canGrow(world, posU, s, false);
                b.updateTick(world, posU, world.getBlockState(posU), r); //trigger growth tick
                int i = deg; //degradation: 0-10
                if (r.nextInt(24) > i) //if random number(0-23) is bigger than degradation
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(24) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(24) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(24) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
            }
            if (r.nextInt(5+(flag?3:0)) == 0) {
                if (deg < 10)
                    world.setBlockState(pos, state.withProperty(DEGRADATION, deg + 1));
                else
                    world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    //
    // BLOCKSTATE STUFF
    //
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(DEGRADATION, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(DEGRADATION);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, DEGRADATION);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":"+ModConstants.BlockNames.compostSoil+".degradation", stack.getItemDamage()));
    }
}
