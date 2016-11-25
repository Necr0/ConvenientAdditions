package convenientadditions.api.gui.widget;

import net.minecraft.util.ResourceLocation;

public class ImageResourceLocation extends ResourceLocation {
	public int startX, startY, sizeX, sizeY;

	public ImageResourceLocation(String resourceName,int startX,int startY,int sizeX,int sizeY) {
		super(resourceName);
		this.startX=startX;
		this.startY=startY;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
}
