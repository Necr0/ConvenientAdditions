package convenientadditions.api.util;

import convenientadditions.Reference;
import convenientadditions.api.item.charge.IChargeable;
import convenientadditions.api.item.charge.ISunlightChargeable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentUtil {
	public static boolean registered=false;
	
	public static final EnumEnchantmentType sunlightChargable=EnumHelper.addEnchantmentType("sunlightChargable");
	public static final EnumEnchantmentType chargable=EnumHelper.addEnchantmentType("chargable");
	
	private static final java.lang.reflect.Field enchantmentSetter = Enchantment.class.getDeclaredFields()[0];
	
	public static final EnchantmentCapacity capacity=new EnchantmentCapacity();
	public static final EnchantmentChargeEfficiency chargeEfficiency=new EnchantmentChargeEfficiency();
	public static final EnchantmentDrain drain=new EnchantmentDrain();
	
	public static final double[] enchantmentScaleFactor=new double[]{1,1.4d,1.75d,2.05d,2.3d,2.55d,2.75d,2.9d,3.0d,3.5d};
	
	public static abstract class EnchantmentBase extends Enchantment{
		public EnchantmentBase(int enchantmentId,String name,int enchantmentWeight,EnumEnchantmentType enchantmentType) {
			super(enchantmentId,new ResourceLocation(name),enchantmentWeight,enchantmentType);
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return ((this.type==chargable)&&(stack.getItem() instanceof IChargeable))||
	        		((this.type==sunlightChargable)&&(stack.getItem() instanceof ISunlightChargeable));
	    }
	    
	    public int getMinEnchantability(int p_77321_1_)
	    {
	        return 1 + 10 * (p_77321_1_ - 1);
	    }
	    
	    public int getMaxEnchantability(int p_77317_1_)
	    {
	        return super.getMinEnchantability(p_77317_1_) + 50;
	    }
	    
	    public int getMaxLevel()
	    {
	        return 3;
	    }
	}
	
	public static class EnchantmentCapacity extends EnchantmentBase{
		public EnchantmentCapacity() {
			super(Reference.enchantmentIdBase+Reference.enchantmentCapacityId, Reference.enchantmentCapacityName, 10, chargable);
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
			super(Reference.enchantmentIdBase+Reference.enchantmentChargeEfficiencyId, Reference.enchantmentChargeEfficiencyName, 10, chargable);
			this.setName("chargeEfficiency");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((IChargeable)stack.getItem()).canApplyChargeEfficiency(stack);
	    }
	}
	
	public static class EnchantmentDrain extends EnchantmentBase{
		public EnchantmentDrain() {
			super(Reference.enchantmentIdBase+Reference.enchantmentDrainId, Reference.enchantmentDrainName, 10, sunlightChargable);
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
        	//Enchantment.enchantmentRegistry.register(enchantment.getI, key, value);
            //net.minecraftforge.common.util.EnumHelper.setFailsafeFieldValue(enchantmentSetter, null,
            //    com.google.common.collect.ObjectArrays.concat(Enchantment.enchantmentsList, enchantment));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e); //Rethrow see what happens
        }
    }
}
