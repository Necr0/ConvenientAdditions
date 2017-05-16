package convenientadditions.item.trinket;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemSaturationRing extends CAItem implements IBauble {

    public ItemSaturationRing() {
        super(ModConstants.ItemNames.saturationRing);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote)
            return;
        EntityPlayer p = (EntityPlayer) player;
        FoodStats s = p.getFoodStats();
        if (s.needFood()&&p.ticksExisted%1100==0){
            int foodLevel=Math.min((20-s.getFoodLevel()),(1+(p.world.rand.nextInt(8)==0?1:0)));
            s.addStats(foodLevel,2f+(p.world.rand.nextFloat()*2));
            p.world.playSound(null,p.posX,p.posY,p.posZ,SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS,.05f,.95f);
        }
    }
}
