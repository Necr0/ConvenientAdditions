package convenientadditions.api.util;

import conveniencecore.api.item.IChargeable;
import convenientadditions.api.item.charge.ISunlightChargeable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnchantmentUtil {
	public static boolean registered=false;
    public static final String enchantmentChargeEfficiencyName="chargeEfficiency";
    public static final String enchantmentCapacityName="capacity";
    public static final String enchantmentDrainName="drain";
	
	public static final EnumEnchantmentType sunlightChargable=EnumHelper.addEnchantmentType("sunlightChargable");
	public static final EnumEnchantmentType chargable=EnumHelper.addEnchantmentType("chargable");
	
	public static final EnchantmentCapacity capacity=new EnchantmentCapacity();
	public static final EnchantmentChargeEfficiency chargeEfficiency=new EnchantmentChargeEfficiency();
	public static final EnchantmentDrain drain=new EnchantmentDrain();
	
	public static final double[] enchantmentScaleFactor=new double[]{1,1.4d,1.75d,2.05d,2.3d,2.55d,2.75d,2.9d,3.0d,3.5d};
	
	public static abstract class EnchantmentBase extends Enchantment{
		//Enchantment(Enchantment.Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots)
		public int enchantmentId;
		public ResourceLocation nameLocation;
		
		public EnchantmentBase(String name,Enchantment.Rarity rarity,EnumEnchantmentType enchantmentType) {
			super(rarity,enchantmentType,EntityEquipmentSlot.values());
			this.setRegistryName(name);
			this.nameLocation=new ResourceLocation(name);
		}
		
		public EnchantmentBase(String name,Enchantment.Rarity rarity,EntityEquipmentSlot[] slots,EnumEnchantmentType enchantmentType) {
			super(rarity,enchantmentType,slots);
			this.setRegistryName(name);
			this.nameLocation=new ResourceLocation(name);
		}

		@Override
	    public boolean canApply(ItemStack stack)
	    {
	        return ((this.type==chargable)&&(stack.getItem() instanceof IChargeable))||
	        		((this.type==sunlightChargable)&&(stack.getItem() instanceof ISunlightChargeable));
	    }

	    @Override
		public boolean canApplyAtEnchantingTable(ItemStack stack)
		{
			return ((this.type==chargable)&&(stack.getItem() instanceof IChargeable))||
					((this.type==sunlightChargable)&&(stack.getItem() instanceof ISunlightChargeable));
		}

		@Override
	    public int getMinEnchantability(int p_77321_1_)
	    {
	        return 1 + 10 * (p_77321_1_ - 1);
	    }

		@Override
	    public int getMaxEnchantability(int p_77317_1_)
	    {
	        return super.getMinEnchantability(p_77317_1_) + 50;
	    }

		@Override
	    public int getMaxLevel()
	    {
	        return 3;
	    }
	}
	
	public static class EnchantmentCapacity extends EnchantmentBase{
		public EnchantmentCapacity() {
			super(enchantmentCapacityName, Enchantment.Rarity.COMMON, chargable);
			this.setName("capacity");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((IChargeable)stack.getItem()).canApplyCapacity(stack);
	    }
	    
	    public int getMaxLevel()
	    {
	        return 5;
	    }
	}
	
	public static class EnchantmentChargeEfficiency extends EnchantmentBase{
		public EnchantmentChargeEfficiency() {
			super(enchantmentChargeEfficiencyName, Enchantment.Rarity.COMMON, chargable);
			this.setName("chargeEfficiency");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((IChargeable)stack.getItem()).canApplyChargeEfficiency(stack);
	    }
	}
	
	public static class EnchantmentDrain extends EnchantmentBase{
		public EnchantmentDrain() {
			super(enchantmentDrainName, Enchantment.Rarity.COMMON, sunlightChargable);
			this.setName("drain");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((ISunlightChargeable)stack.getItem()).canApplyDrain(stack);
	    }
	}
	
	public static void init(){
		if(!registered){
			addToEnchantmentList(capacity);
			addToEnchantmentList(chargeEfficiency);
			addToEnchantmentList(drain);
			registered=true;
		}
	}
	

    public static void addToEnchantmentList(Enchantment enchantment)
    {
        try
        {
        	GameRegistry.register(enchantment);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
