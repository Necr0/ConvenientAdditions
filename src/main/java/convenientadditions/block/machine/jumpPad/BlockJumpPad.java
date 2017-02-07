package convenientadditions.block.machine.jumpPad;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.event.PlayerMovementEvent;
import convenientadditions.base.CABlockMachine;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.init.ModNetworking;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockJumpPad extends CABlockMachine {

    public BlockJumpPad() {
        super(ModConstants.BlockNames.jumpPad,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerMovement(PlayerMovementEvent event){
        EntityPlayer p=event.getPlayer();
        if(p.world.getBlockState(new BlockPos(p).down()).getBlock()==this){
            if(event instanceof PlayerMovementEvent.PlayerJumpEvent)
                ModNetworking.INSTANCE.sendToServer(new MessageJumpPad(true));
            else if(event instanceof PlayerMovementEvent.PlayerSneakEvent)
                ModNetworking.INSTANCE.sendToServer(new MessageJumpPad(false));
        }
    }

    @Nullable
    public BlockPos getTargetLocation(BlockPos pos, World w, boolean isSneaking){
        int range=16;
        if(isSneaking){
            for(int i=3;i<range;i++){
                BlockPos p=pos.down(i);
                IBlockState state=w.getBlockState(p);
                if(!state.isTranslucent()){
                    if(w.getBlockState(p).getBlock()==this)
                        return p;
                    else
                        return null;
                }
            }
        }else{
            TileEntity te=w.getTileEntity(pos);
            if(te!=null && te instanceof TileEntityJumpPad){
                BlockPos p=((TileEntityJumpPad) te).getCustomLocation();
                if(p!=null &&  w.getBlockState(p).getBlock()==this && validateLocation(p,w))
                    return p;
            }
            for(int i=3;i<range;i++){
                BlockPos p=pos.up(i);
                IBlockState state=w.getBlockState(p);
                if(!w.getBlockState(p).isTranslucent()){
                    if(state.getBlock()==this){
                        if(validateLocation(p,w))
                            return p;
                        else
                            return null;
                    }else
                        return null;
                }
            }
        }
        return null;
    }

    public boolean validateLocation(BlockPos p, World w){
        IBlockState state1=w.getBlockState(p.up());
        IBlockState state2=w.getBlockState(p.up(2));
        if(state1.getBlock().isAir(state1,w,p.up())&&state2.getBlock().isAir(state2,w,p.up(2)))
            return true;
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_JUMP_PAD_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityJumpPad();
    }
}
