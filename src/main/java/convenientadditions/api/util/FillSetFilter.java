package convenientadditions.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FillSetFilter {
	boolean ignoreNBT;
	boolean ignoreDamage;
	
	NonNullList<ItemStack> input;
	NonNullList<ItemStack> filter;
	NonNullList<ItemStack> output;
	
	public FillSetFilter(NonNullList<ItemStack> input,NonNullList<ItemStack> filter,boolean ignoreDamage,boolean ignoreNBT){
		this.input=ItemHelper.deepCopyItemList(input);
		this.filter=ItemHelper.deepCopyItemList(filter);
		this.ignoreDamage=ignoreDamage;
		this.ignoreNBT=ignoreNBT;
	}
	
	public boolean isReadyForOutput(){
		if(output==null)
			goThrough();
		return filtersEmpty();
	}
	
	public NonNullList<ItemStack> getOutput(){
		if(output==null)
			goThrough();
		return output;
	}
	
	public NonNullList<ItemStack> getInput(){
		if(output==null)
			goThrough();
		for(int i=0;i<input.size();i++){
			if(input.get(i).isEmpty())
				input.set(i,ItemStack.EMPTY);
		}
		return input;
	}
	
	public void goThrough(){
		NonNullList<ItemStack> out=NonNullList.create();
		for(ItemStack stack_in:input){
			if(stack_in!=null){
				for(ItemStack stack_f:filter){
					if(stack_f!=null&&ItemHelper.match(stack_in,stack_f,this.ignoreDamage,this.ignoreNBT)){
						while(!stack_in.isEmpty()&&!stack_f.isEmpty()){
							boolean flag=false;
							for(ItemStack stack_o:out){
								if(!stack_o.isEmpty()&&ItemHelper.match(stack_in, stack_o,false,false)&&stack_o.getCount()<stack_o.getItem().getItemStackLimit(stack_o)){
									stack_o.grow(1);
									stack_in.shrink(1);
									stack_f.shrink(1);
									flag=true;
									break;
								}
							}
							if(!flag){
								ItemStack stack_new=new ItemStack(stack_in.getItem(), 0, stack_in.getItemDamage());
								if(stack_in.hasTagCompound())
									stack_new.setTagCompound(stack_in.getTagCompound().copy());
								out.add(stack_new);
							}
						}
					}
				}
			}
		}
		for(int i=1;i<18-out.size();i++)
			out.add(ItemStack.EMPTY);
		this.output=out;
	}
	
	public boolean filtersEmpty(){
		for(ItemStack f:filter){
			if(f!=null&&f.getCount()!=0)
				return false;
		}
		return true;
	}
}
