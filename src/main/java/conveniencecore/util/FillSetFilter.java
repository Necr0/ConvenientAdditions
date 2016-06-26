package conveniencecore.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FillSetFilter {
	boolean ignoreNBT;
	boolean ignoreDamage;
	
	ItemStack[] input;
	ItemStack[] filter;
	ItemStack[] output;
	
	public FillSetFilter(ItemStack[] input,ItemStack[] filter,boolean ignoreDamage,boolean ignoreNBT){
		ItemStack[] new_in=new ItemStack[input.length];
		for(int i=0;i<new_in.length;i++){
			if(input[i]!=null)
				new_in[i]=input[i].copy();
		}
		this.input=new_in;
		ItemStack[] new_f=new ItemStack[filter.length];
		for(int i=0;i<new_f.length;i++){
			if(filter[i]!=null)
				new_f[i]=filter[i].copy();
		}
		this.filter=new_f;
		
		this.ignoreDamage=ignoreDamage;
		this.ignoreNBT=ignoreNBT;
	}
	
	public boolean isReadyForOutput(){
		if(output==null)
			goThrough();
		return filtersEmpty();
	}
	
	public ItemStack[] getOutput(){
		if(output==null)
			goThrough();
		return output;
	}
	
	public ItemStack[] getInput(){
		if(output==null)
			goThrough();
		for(int i=0;i<input.length;i++){
			if(input[i]!=null&&input[i].stackSize==0)
				input[i]=null;
		}
		return input;
	}
	
	public void goThrough(){
		ArrayList<ItemStack> out=new ArrayList<ItemStack>();
		for(ItemStack stack_in:input){
			if(stack_in!=null){
				for(ItemStack stack_f:filter){
					if(stack_f!=null&&ItemHelper.match(stack_in,stack_f,this.ignoreDamage,this.ignoreNBT)){
						while(stack_in.stackSize>0&&stack_f.stackSize>0){
							boolean flag=false;
							for(ItemStack stack_o:out){
								if(stack_o!=null&&ItemHelper.match(stack_in, stack_o,false,false)&&stack_o.stackSize<stack_o.getItem().getItemStackLimit(stack_o)){
									stack_o.stackSize++;
									stack_in.stackSize--;
									stack_f.stackSize--;
									flag=true;
									break;
								}
							}
							if(!flag){
								ItemStack stack_new=new ItemStack(stack_in.getItem(), 0, stack_in.getItemDamage());
								if(stack_in.hasTagCompound())
									stack_new.setTagCompound((NBTTagCompound) stack_in.getTagCompound().copy());
								out.add(stack_new);
							}
						}
					}
				}
			}
		}
		this.output=out.toArray(new ItemStack[input.length]);
	}
	
	public boolean filtersEmpty(){
		for(ItemStack f:filter){
			if(f!=null&&f.stackSize!=0)
				return false;
		}
		return true;
	}
}
