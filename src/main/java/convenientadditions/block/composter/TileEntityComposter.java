package convenientadditions.block.composter;

import convenientadditions.api.util.Helper;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.init.ModConfig;
import convenientadditions.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.List;
import java.util.Random;

public class TileEntityComposter extends TileEntity implements ITickable {

    public boolean processing = false;
    public int content = 0;
    public int progress = 0;
    public boolean spores = true;
    ComposterItemStackHandler stackHandler;
    public TileEntityComposter() {
        super();
        this.stackHandler = new ComposterItemStackHandler(this);
    }

    public ItemStack insertStack(ItemStack stackIn) {
        ItemStack stack = stackHandler.insertItem(0, stackIn, false);
        if (stackIn.getCount() == 1 && stack.isEmpty())
            if (stackIn.getItem().hasContainerItem(stackIn))
                return stackIn.getItem().getContainerItem(stackIn);
        return stack;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.processing = nbt.getBoolean("processing");
        this.content = nbt.getInteger("content");
        this.progress = nbt.getInteger("progress");
        this.spores = nbt.getBoolean("spores");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("processing", processing);
        nbt.setInteger("content", content);
        nbt.setInteger("progress", progress);
        nbt.setBoolean("progress", spores);
        return nbt;
    }

    public void readSyncNBT(NBTTagCompound nbt) {
        this.processing = nbt.getBoolean("processing");
        this.content = nbt.getInteger("content");
    }

    public void writeSyncNBT(NBTTagCompound nbt) {
        nbt.setBoolean("processing", processing);
        nbt.setInteger("content", content);
    }

    public int getContentCapacityPercentage() {
        return content * 100 / ModConfig.composter_capacity;
    }

    @Override
    public void update() {
        IBlockState state = getWorld().getBlockState(pos);
        Random rnd = new Random();
        if (!getWorld().isRemote) {
            if (content >= ModConfig.composter_progressContent) {
                progress++;
                if (progress >= ModConfig.composter_progressPeriod) {
                    progress = 0;
                    content -= ModConfig.composter_progressContent;
                    if (getWorld().rand.nextFloat() < ModConfig.composter_compostChance)
                        Helper.spawnItemInPlace(getWorld(), (double) pos.getX() + .5, (double) pos.getY() + 1.2, (double) pos.getZ() + .5, new ItemStack(ModItems.itemCompost, 1, this.spores ? 1 : 0));
                    if (getWorld().rand.nextFloat() < ModConfig.composter_extraCompostChance)
                        Helper.spawnItemInPlace(getWorld(), (double) pos.getX() + .5, (double) pos.getY() + 1.2, (double) pos.getZ() + .5, new ItemStack(ModItems.itemCompost, 1, this.spores ? 1 : 0));
                    if (getWorld().rand.nextFloat() < ModConfig.composter_dirtChunkChance)
                        Helper.spawnItemInPlace(getWorld(), (double) pos.getX() + .5, (double) pos.getY() + 1.2, (double) pos.getZ() + .5, new ItemStack(ModItems.itemDirtChunk));
                    if (getWorld().rand.nextFloat() < ModConfig.composter_fertilizerChance)
                        Helper.spawnItemInPlace(getWorld(), (double) pos.getX() + .5, (double) pos.getY() + 1.2, (double) pos.getZ() + .5, new ItemStack(ModItems.itemFertilizer));
                }
                if (content >= ModConfig.composter_capacity && ModConfig.composter_overflowSmell) {
                    List<EntityPlayer> players = getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3));
                    for (EntityPlayer p : players) {
                        switch (rnd.nextInt(120)) {
                            case 0:
                                p.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 200, 0));
                                break;
                            default:
                                break;
                        }
                    }
                }
                this.markDirty();
                this.getWorld().notifyBlockUpdate(pos, state, state, 3);
            } else {
                this.progress = 0;
                if (this.content == 0)
                    this.spores = false;
                if (processing) {
                    this.processing = false;
                    this.markDirty();
                    this.getWorld().notifyBlockUpdate(pos, state, state, 3);
                }
            }
        } else if (processing) {
            if (rnd.nextInt(10) == 0)
                getWorld().spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX() + .5 - ((double) (rnd.nextInt(9) - 4) / 10D), pos.getY() + .2 + (double) content / (double) ModConfig.composter_capacity * .75, pos.getZ() + .5 + ((double) (rnd.nextInt(9) - 4) / 10D), 0, 0.6, 0);
            if (content >= ModConfig.composter_capacity)
                getWorld().spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX() + .5 - ((double) (rnd.nextInt(9) - 4) / 10D), pos.getY() + .2 + (double) content / (double) ModConfig.composter_capacity * .75, pos.getZ() + .5 + ((double) (rnd.nextInt(9) - 4) / 10D), 0, 0.6, 0);
        }
    }

    public int getContentValue(ItemStack itemStack) {
        return CompostRegistry.getCompostingMass(itemStack);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeSyncNBT(nbt);
        return new SPacketUpdateTileEntity(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readSyncNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? true : super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) stackHandler : super.getCapability(capability, facing);
    }
}
