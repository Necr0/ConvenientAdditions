package convenientadditions.potion;

import convenientadditions.ModConstants;
import convenientadditions.base.CAPotion;
import convenientadditions.init.ModImageResourceLocations;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 5/14/2017.
 */
public class PotionThorns extends CAPotion {
    public static final PotionThorns INSTANCE=new PotionThorns();

    public PotionThorns() {
        super(ModConstants.PotionNames.thorns, false, 0x800000, ModImageResourceLocations.THORNS);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDamageTaken(LivingAttackEvent event) {
        EntityLivingBase attacked=event.getEntityLiving();
        PotionEffect effect=attacked.getActivePotionEffect(this);
        DamageSource source=event.getSource();
        if(effect==null ||
                source.getEntity()==null ||
                !(source.getEntity() instanceof EntityLivingBase) ||
                source.getDamageType().equals("thorns") ||
                source.isDamageAbsolute() ||
                source.isMagicDamage() ||
                source.isFireDamage() ||
                source.isExplosion() ||
                source.isProjectile() ||
                source.isUnblockable())
            return;
        EntityLivingBase attacker=(EntityLivingBase) source.getEntity();
        attacker.attackEntityFrom(DamageSource.causeThornsDamage(attacked),event.getAmount()*0.2f*(effect.getAmplifier()+1));
    }
}
