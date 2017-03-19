package convenientadditions.item.tools.mobCatcher;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.entity.mobCatcher.EntityMobCatcher;
import convenientadditions.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMobCatcher extends CAItem {
    public EnumMobCatcherType type;

    public ItemMobCatcher(String name,EnumMobCatcherType type) {
        super(ModConstants.ItemNames.mobCatcher+"."+name);
        this.setMaxStackSize(1);
        this.type=type;
        this.setCategory(EnumItemCategory.TOOL);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
        ItemStack stack=playerIn.getHeldItem(handIn);
        if(!isHoldingMob(stack)){
            if(worldIn.isRemote)
                return new ActionResult<>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
            EntityMobCatcher e=new EntityMobCatcher(worldIn,playerIn,stack);
            worldIn.spawnEntity(e);
            e.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, .4F);
            return new ActionResult<>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
        }
        return new ActionResult<>(EnumActionResult.FAIL, stack);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if(isHoldingMob(stack)){
            if(world.isRemote)
                return EnumActionResult.SUCCESS;
            NBTTagCompound nbt=stack.getTagCompound();
            pos=pos.offset(facing);
            AnvilChunkLoader.readWorldEntityPos(nbt.getCompoundTag("CONTAINED_ENTITY"),world,pos.getX()+.5, pos.getY(), pos.getZ()+.5, true);
            nbt.removeTag("CONTAINED_ENTITY");
            stack.setItemDamage(0);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    public boolean isHoldingMob(ItemStack stack){
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound nbt=stack.getTagCompound();
        return nbt.hasKey("CONTAINED_ENTITY");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        super.addInformation(stack,player,tooltip,advanced);
        tooltip.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.mobCatcher+".strength",type.captureStrength));
        tooltip.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.mobCatcher+".hostile",type.captureHostile?Helper.localize(ModConstants.Mod.MODID+":yes"):Helper.localize(ModConstants.Mod.MODID+":no")));
        tooltip.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.mobCatcher+".boss",type.captureBoss?Helper.localize(ModConstants.Mod.MODID+":yes"):Helper.localize(ModConstants.Mod.MODID+":no")));
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("CONTAINED_ENTITY"))
            tooltip.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":mobCatcherHoldingEntity"));
        else
            tooltip.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":mobCatcherNotHoldingEntity"));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        CustomModelMeshMobCatcher.initVariants();
        ModelLoader.setCustomMeshDefinition(ModItems.itemMobCatcherRegular, new CustomModelMeshMobCatcher());
        ModelLoader.setCustomMeshDefinition(ModItems.itemMobCatcherSuper, new CustomModelMeshMobCatcher());
        ModelLoader.setCustomMeshDefinition(ModItems.itemMobCatcherHyper, new CustomModelMeshMobCatcher());
        ModelLoader.setCustomMeshDefinition(ModItems.itemMobCatcherMega, new CustomModelMeshMobCatcher());
        ModelLoader.setCustomMeshDefinition(ModItems.itemMobCatcherMaster, new CustomModelMeshMobCatcher());
    }
}
