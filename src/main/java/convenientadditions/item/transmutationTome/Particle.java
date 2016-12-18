package convenientadditions.item.transmutationTome;

import convenientadditions.api.gui.ImageResourceLocation;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class Particle {

    public int x, y, destx, desty;
    public ImageResourceLocation texture;
    public long duration;

    public long elapsed;

    public Particle(int startx, int starty, int destx, int desty, ImageResourceLocation texture, long duration) {
        this.texture = texture;
        this.x = startx;
        this.y = starty;
        this.destx = destx;
        this.desty = desty;
        this.duration = duration;
        this.elapsed = 0;
    }

    public void draw(GuiScreen screen, long timeElapsed) {
        elapsed = Math.min(elapsed + timeElapsed, duration);

        double percentage = (double) elapsed / (double) duration;

        int dirX = destx - x;
        int dirY = desty - y;

        int posX = x + (int) (dirX * percentage);
        int posY = y + (int) (dirY * percentage);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        screen.mc.getTextureManager().bindTexture(this.texture);
        screen.drawTexturedModalRect(posX, posY, this.texture.startX, this.texture.startY, this.texture.sizeX, this.texture.sizeY);
    }

    public boolean isExpired() {
        return this.elapsed >= duration;
    }
}
