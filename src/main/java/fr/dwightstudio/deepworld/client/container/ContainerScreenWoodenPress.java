package fr.dwightstudio.deepworld.client.container;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachine;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class ContainerScreenWoodenPress extends ContainerScreen<ContainerWoodenMachine<TileEntityWoodenPress>> {

    private ContainerWoodenMachine<TileEntityWoodenPress> containerWoodenPress;

    public ContainerScreenWoodenPress(ContainerWoodenMachine<TileEntityWoodenPress> containerWoodenPress, PlayerInventory playerInventory, ITextComponent title) {
        super(containerWoodenPress, playerInventory, title);
        this.containerWoodenPress = containerWoodenPress;

        // Set the width and height of the gui.  Should match the size of the texture!
        xSize = 176;
        ySize = 166;
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

        addButton(new ImageButton(guiLeft + BUTTON_XPOS, guiTop + BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_ICON_U, BUTTON_ICON_V, BUTTON_HEIGHT, TEXTURE, (pressable) -> {
            minecraft.playerController.sendEnchantPacket(containerWoodenPress.windowId, 0);
        }));
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    // Draw the Tool tip text if hovering over something of interest on the screen
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        if (!this.minecraft.player.inventory.getItemStack().isEmpty()) return;  // no tooltip if the player is dragging something

        super.renderHoveredToolTip(mouseX, mouseY);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        // width and height are the size provided to the window when initialised after creation.
        // xSize, ySize are the expected size of the texture-? usually seems to be left as a default.
        // The code below is typical for vanilla containers, so I've just copied that- it appears to centre the texture within
        //  the available window
        int edgeSpacingX = (this.width - this.xSize) / 2;
        int edgeSpacingY = (this.height - this.ySize) / 2;
        this.blit(edgeSpacingX, edgeSpacingY, 0, 0, this.xSize, this.ySize);

        // draw the cook progress bar
        double progressFraction = containerWoodenPress.fractionOfProcessTimeComplete();
        blit(guiLeft + PROCESS_BAR_XPOS, guiTop + PROCESS_BAR_YPOS, PROCESS_BAR_ICON_U, PROCESS_BAR_ICON_V,
                (int)(progressFraction * PROCESS_BAR_WIDTH), PROCESS_BAR_HEIGHT);

        // draw the inertia remaining bar
        double burnRemaining = containerWoodenPress.getInertiaFraction();
        int yOffset = (int)((1.0 - burnRemaining) * GEAR_HEIGHT);
        blit(guiLeft + GEAR_XPOS, guiTop + GEAR_YPOS + yOffset,
                GEAR_ICON_U, GEAR_ICON_V + yOffset, GEAR_WIDTH, GEAR_HEIGHT - yOffset);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        this.font.drawString(title.getFormattedText(), (float)(this.xSize / 2 - this.font.getStringWidth(title.getString()) / 2), 6.0F, Color.darkGray.getRGB());
        font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), Color.darkGray.getRGB());
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY){
        return ((mouseX >= x && mouseX <= x+xSize) && (mouseY >= y && mouseY <= y+ySize));
    }

    // This is the resource location for the background image
    private static final ResourceLocation TEXTURE = new ResourceLocation(Deepworld.MOD_ID, "textures/gui/wooden_press.png");
}
