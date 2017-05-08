package convenientadditions.item.soulGem;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Necro on 5/5/2017.
 */
public class ItemSoulGem extends CAItem {

    public ItemSoulGem() {
        super(ModConstants.ItemNames.soulGem);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    }

    @Nullable
    public String getEntityId(ItemStack stack){
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound nbt=stack.getTagCompound();
        return nbt.hasKey("CONTAINED_ENTITY_ID")?nbt.getString("CONTAINED_ENTITY_ID"):null;
    }

    public ItemStack setEntityId(ItemStack stack,String id){
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound nbt=stack.getTagCompound();
        nbt.setString("CONTAINED_ENTITY_ID",id);
        stack.setTagCompound(nbt);
        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        super.addInformation(stack,player,tooltip,advanced);
        if(getEntityId(stack)!=null) {
            EntityEntry entry= ForgeRegistries.ENTITIES.getValue(new ResourceLocation(getEntityId(stack)));
            if(entry!=null)
                tooltip.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":soulGemHoldingEntity", Helper.localize("entity."+entry.getName()+".name")));
            else
                tooltip.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":soulGemHoldingEntity", "?"));
        }else
            tooltip.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":soulGemHoldingEntity",Helper.localize(ModConstants.Mod.MODID+":none")));
    }
}
