package fr.dwightstudio.deepworld.client.container;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachine;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class ContainerScreenWoodenGearShaper extends AbstractContainerScreen<ContainerWoodenMachine<TileEntityWoodenGearShaper>> {
    private ContainerWoodenMachine<TileEntityWoodenGearShaper> containerWoodenGearShaper;

    public ContainerScreenWoodenGearShaper(ContainerWoodenMachine<TileEntityWoodenGearShaper> containerWoodenGearShaper, Inventory playerInventory, Component title) {
        super(containerWoodenGearShaper, playerInventory, title);
        this.containerWoodenGearShaper = containerWoodenGearShaper;

        // Set the width and height of the gui.  Should match the size of the texture!
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    // Coordinates of graphical elements [x,y]
    final int PROCESS_BAR_XPOS = 79;
    final int PROCESS_BAR_YPOS = 35;

    final int GEAR_XPOS = 57;
    final int GEAR_YPOS = 36;

    final int BUTTON_XPOS = 53;
    final int BUTTON_YPOS = 52;

    // Texture position of graphical elements [u,v]
    final int PROCESS_BAR_ICON_U = 176;
    final int PROCESS_BAR_ICON_V = 14;

    final int GEAR_ICON_U = 176;
    final int GEAR_ICON_V = 0;

    final int BUTTON_ICON_U = 176;
    final int BUTTON_ICON_V = 31;

    // With & Height
    final int PROCESS_BAR_WIDTH = 24;
    final int PROCESS_BAR_HEIGHT = 17;

    final int GEAR_WIDTH = 14;
    final int GEAR_HEIGHT = 14;

    final int BUTTON_WIDTH = 22;
    final int BUTTON_HEIGHT = 22;

    @Override
    protected void init() {
        super.init();
        addWidget(new ImageButton(this.getGuiLeft() + BUTTON_XPOS, this.getGuiTop() + BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_ICON_U, BUTTON_ICON_V, BUTTON_HEIGHT, TEXTURE, Button::onPress));
    }

    /*public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    // Draw the Tool tip text if hovering over something of interest on the screen
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        if (!this.minecraft.player.inventoryMenu.getItems().isEmpty()) return;  // no tooltip if the player is dragging something

        super.renderHoveredToolTip(mouseX, mouseY);
    }*/

    @Override
    protected void renderBg(PoseStack poseStack, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindForSetup(TEXTURE);

        // width and height are the size provided to the window when initialised after creation.
        // xSize, ySize are the expected size of the texture-? usually seems to be left as a default.
        // The code below is typical for vanilla containers, so I've just copied that- it appears to centre the texture within
        //  the available window
        int edgeSpacingX = (this.width - this.imageWidth) / 2;
        int edgeSpacingY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, edgeSpacingX, edgeSpacingY, 0, 0, this.imageWidth, this.imageHeight);

        // draw the cook progress bar
        double progressFraction = containerWoodenGearShaper.fractionOfProcessTimeComplete();
        blit(poseStack, this.getGuiLeft() + PROCESS_BAR_XPOS, this.getGuiTop() + PROCESS_BAR_YPOS, PROCESS_BAR_ICON_U, PROCESS_BAR_ICON_V,
                (int)(progressFraction * PROCESS_BAR_WIDTH), PROCESS_BAR_HEIGHT);

        // draw the inertia remaining bar
        double burnRemaining = containerWoodenGearShaper.getInertiaFraction();
        int yOffset = (int)((1.0 - burnRemaining) * GEAR_HEIGHT);
        blit(poseStack, this.getGuiLeft() + GEAR_XPOS, this.getGuiTop() + GEAR_YPOS + yOffset,
                GEAR_ICON_U, GEAR_ICON_V + yOffset, GEAR_WIDTH, GEAR_HEIGHT - yOffset);
    }

    @Override
    public void render(PoseStack poseStack, int x, int y, float p_97798_) {
        this.renderBackground(poseStack);
        this.renderBg(poseStack, p_97798_, x, y);
        super.render(poseStack, x, y, p_97798_);
        this.renderTooltip(poseStack, x, y);
    }

/*    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        this.font.drawString(title.getFormattedText(), (float)(this.xSize / 2 - this.font.getStringWidth(title.getString()) / 2), 6.0F, Color.darkGray.getRGB());
        font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), Color.darkGray.getRGB());
    }*/

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY){
        return ((mouseX >= x && mouseX <= x+xSize) && (mouseY >= y && mouseY <= y+ySize));
    }

    // This is the resource location for the background image
    private static final ResourceLocation TEXTURE = new ResourceLocation(Deepworld.MOD_ID, "textures/gui/wooden_gear_shaper.png");

}
