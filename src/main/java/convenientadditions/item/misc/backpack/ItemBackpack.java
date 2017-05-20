package convenientadditions.item.misc.backpack;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemBackpack extends CAItem {

    public ItemBackpack() {
        super(ModConstants.ItemNames.backpack);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.MISC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_BACKPACK_ID, world, 0, 0, 0);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent<ItemStack> event){
        if(event.getObject().getItem()==this)
            event.addCapability(new ResourceLocation(ModConstants.Mod.MODID,"BACKPACK_CAPABILITY"),new CapabilityProviderBackpack(event.getObject()));
    }
}
