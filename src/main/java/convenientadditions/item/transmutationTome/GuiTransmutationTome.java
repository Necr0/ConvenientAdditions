package convenientadditions.item.transmutationTome;

import java.util.ArrayList;
import java.util.Iterator;

import conveniencecore.gui.CCGuiContainerBase;
import conveniencecore.gui.widget.ImageResourceLocation;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.init.ModSounds;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class GuiTransmutationTome extends CCGuiContainerBase {
	
	public long time;
	
	public EntityPlayer player;
	public ContainerTransmutationTome tcontainer;
	public ArrayList<Particle> particles;
	
    private static final ResourceLocation tomeGuiTextures = new ResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png");
    private static final ImageResourceLocation[] particleIRLs = new ImageResourceLocation[]{
    		new ImageResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png", 190, 70, 2, 2),
    		new ImageResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png", 192, 70, 2, 2),
    		new ImageResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png", 190, 72, 1, 1),
    		new ImageResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png", 191, 72, 1, 1),
    		new ImageResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png", 192, 72, 1, 1),
    		new ImageResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/transmutationTome.png", 193, 72, 1, 1)
    };
    
	public GuiTransmutationTome(ContainerTransmutationTome container) {
		super(container);
		this.player=container.player;
		this.tcontainer=container;
		this.particles=new ArrayList<Particle>();
		this.time=System.currentTimeMillis();
		this.xSize=175;
		this.ySize=178;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		Helper.getClientPlayer().playSound(ModSounds.bookTurn, 1F, 1F);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(tomeGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        if(tcontainer.isRecipeValid()){
        	//xp bar
    		float percentage=Math.min((float)player.experienceLevel/(float)tcontainer.getLevelRequired(),1F);
        	if(tcontainer.isWorking())
        		percentage=tcontainer.getXPOverride();
        	int xp_bar=(int)(percentage*68);
            this.drawTexturedModalRect(i+153, j+9+(68-xp_bar), 176+((int)((time/600)%2)*13), 1+(68-xp_bar), 11, xp_bar);
            //xp string
        	this.drawCenteredString(this.fontRendererObj, ""+player.experienceLevel, i+159, j+78, (player.experienceLevel>=tcontainer.getLevelRequired())?0x009900:0xFF1111);
        	this.drawCenteredString(this.fontRendererObj, ""+tcontainer.getLevelRequired(), i+159, j, 0x009900);
        	
            //particles
        	if(tcontainer.isWorking()){
	            if(this.mc.theWorld.rand.nextInt(4)==0){
	            	this.particles.add(
	            			new Particle(
	            					154+this.mc.theWorld.rand.nextInt(8),
	            					9+(68-xp_bar),
	            					109+this.mc.theWorld.rand.nextInt(12),
	            					38+this.mc.theWorld.rand.nextInt(12),
	            					particleIRLs[this.mc.theWorld.rand.nextInt(particleIRLs.length)],
	            					1000
	            			)
	            	);
	            }
        	}
        }else{
        	this.drawCenteredString(this.fontRendererObj, ""+player.experienceLevel, i+159, j+78, 0x009900);
        }
	}


	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		for (Iterator<Particle> iter = particles.iterator(); iter.hasNext(); ) {
		    Particle p = iter.next();
		    if(p.isExpired())
		    	iter.remove();
		}
		for(Particle p:particles){
			p.draw(this,System.currentTimeMillis()-time);
		}
		time=System.currentTimeMillis();
	}
	
	@Override
	public void onGuiClosed(){
		super.onGuiClosed();
		Helper.getClientPlayer().playSound(ModSounds.bookSlam, 1F, 1F);
	}
	
	public void onOperationFinished(){
		particles.clear();
		Helper.getClientPlayer().playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.player.levelup")), .75F, 1F);
	}
}
