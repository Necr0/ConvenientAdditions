package convenientadditions.item.transmutationTome;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.button.ButtonIRL;
import convenientadditions.api.util.Helper;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.init.ModSounds;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;

public class GuiTransmutationTome extends CAGuiContainer {

    private static final ImageResourceLocation tomeGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png",0,0,175,178);
    private static final ImageResourceLocation[] particleIRLs = new ImageResourceLocation[]{
            new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png", 190, 70, 2, 2),
            new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png", 192, 70, 2, 2),
            new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png", 190, 72, 1, 1),
            new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png", 191, 72, 1, 1),
            new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png", 192, 72, 1, 1),
            new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png", 193, 72, 1, 1)
    };
    public long time;
    public EntityPlayer player;
    public ContainerTransmutationTome tcontainer;
    public ArrayList<Particle> particles;
    public boolean playSound;

    public GuiTransmutationTome(ContainerTransmutationTome container,boolean playSound) {
        super(container,tomeGuiTextures);
        this.player = container.player;
        this.tcontainer = container;
        this.particles = new ArrayList<>();
        this.time = System.currentTimeMillis();
        this.playSound = playSound;
    }

    @Override
    public void initGui() {
        super.initGui();
        addButton(
                new ButtonIRL(0,leftX+20,topY-8,new ImageResourceLocation(
                        ModConstants.Mod.MODID + ":textures/gui/container/transmutationtome.png",227,0,10,16),null,Helper.localize(
                                "tooltip." + ModConstants.Mod.MODID + ":showRecipes")));

        if(playSound){
            Helper.getClientPlayer().playSound(ModSounds.bookTurn, 1F, 1F);
            playSound=false;
        }
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        if (tcontainer.isRecipeValid()) {
            //xp bar
            float percentage = Math.min((float) player.experienceLevel / (float) tcontainer.getLevelRequired(), 1F);
            if (tcontainer.isWorking())
                percentage = tcontainer.getXPOverride();
            int xp_bar = (int) (percentage * 68);
            this.drawTexturedModalRect(leftX + 153, topY + 9 + (68 - xp_bar), 176 + ((int) ((time / 600) % 2) * 13), 1 + (68 - xp_bar), 11, xp_bar);
            //xp string
            this.drawCenteredString(this.fontRendererObj, "" + player.experienceLevel, leftX + 159, topY + 78, (player.experienceLevel >= tcontainer.getLevelRequired()) ? 0x009900 : 0xFF1111);
            this.drawCenteredString(this.fontRendererObj, "" + tcontainer.getLevelRequired(), leftX + 159, topY, 0x009900);

            //particles
            if (tcontainer.isWorking()) {
                if (this.mc.world.rand.nextInt(4) == 0) {
                    this.particles.add(
                            new Particle(
                                    154 + this.mc.world.rand.nextInt(8),
                                    9 + (68 - xp_bar),
                                    109 + this.mc.world.rand.nextInt(12),
                                    38 + this.mc.world.rand.nextInt(12),
                                    particleIRLs[this.mc.world.rand.nextInt(particleIRLs.length)],
                                    1000
                            )
                    );
                }
            }
        } else {
            this.drawCenteredString(this.fontRendererObj, "" + player.experienceLevel, leftX + 159, topY + 78, 0x009900);
        }
    }


    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        particles.removeIf(Particle::isExpired);
        for (Particle p : particles) {
            p.draw(this, System.currentTimeMillis() - time);
        }
        time = System.currentTimeMillis();
    }

    public void onOperationFinished() {
        particles.clear();
        Helper.getClientPlayer().playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, .75F, 1F);
    }

    @Override
    protected void actionPerformed(GuiButton btn) {
        if (btn.id == 0) {
            GuiTransmutationTomeRecipeLookup.previous=this;
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_TRANSMUTATION_TOME_LOOKUP_ID, Helper.getClientWorld(), 0, 0, 0);
        }
    }
}
