package convenientadditions.item;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.CAItem;
import convenientadditions.init.ModConfig;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMobCatcher extends CAItem {
    public ItemMobCatcher() {
        super(ModConstants.ItemNames.mobCatcher);
        this.setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(world.isRemote)
            return EnumActionResult.PASS;
        ItemStack stack = player.getHeldItem(hand);
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound nbt=stack.getTagCompound();
        if(nbt.hasKey("CONTAINED_ENTITY")){
            AnvilChunkLoader.readWorldEntityPos(nbt.getCompoundTag("CONTAINED_ENTITY"),world,pos.getX()+.5, pos.getY()+1, pos.getZ()+.5, true);
            nbt.removeTag("CONTAINED_ENTITY");
            stack.setItemDamage(0);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        super.addInformation(stack,player,tooltip,advanced);
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("CONTAINED_ENTITY"))
            tooltip.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":mobCatcherHoldingEntity"));
        else
            tooltip.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":mobCatcherNotHoldingEntity"));
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if(!playerIn.getEntityWorld().isRemote && stack.getItem()==this && target instanceof EntityCreature && !target.isDead){
            if(!stack.hasTagCompound())
                stack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt=stack.getTagCompound();
            if(!nbt.hasKey("CONTAINED_ENTITY")){
                if(ModConfig.mobCatcher_blacklist.contains(EntityRegistry.getEntry(target.getClass()).getRegistryName().toString()))
                    return false;
                nbt.setTag("CONTAINED_ENTITY",target.serializeNBT());
                target.setDead();
                stack.setItemDamage(1);
                playerIn.setHeldItem(hand, stack);
                return true;
            }
        }
        return false;
    }
}
