package fr.dwightstudio.deepworld.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.menus.WoodenMachineMenu;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class WoodenMachineScreen extends AbstractContainerScreen<WoodenMachineMenu> implements RecipeUpdateListener {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Deepworld.MOD_ID, "textures/gui/wooden_machine_default.png");

    // Coordinates of graphical elements [x,y]
    static final int PROCESS_BAR_XPOS = 79;
    static final int PROCESS_BAR_YPOS = 35;

    static final int GEAR_XPOS = 57;
    static final int GEAR_YPOS = 36;

    static final int BUTTON_XPOS = 53;
    static final int BUTTON_YPOS = 52;

    // Texture position of graphical elements [u,v]
    static final int PROCESS_BAR_ICON_U = 176;
    static final int PROCESS_BAR_ICON_V = 14;

    static final int GEAR_ICON_U = 176;
    static final int GEAR_ICON_V = 0;

    static final int BUTTON_ICON_U = 176;
    static final int BUTTON_ICON_V = 31;

    // With & Height
    static final int PROCESS_BAR_WIDTH = 24;
    static final int PROCESS_BAR_HEIGHT = 17;

    static final int GEAR_WIDTH = 14;
    static final int GEAR_HEIGHT = 14;

    static final int BUTTON_WIDTH = 22;
    static final int BUTTON_HEIGHT = 22;

    private final WoodenMachineMenu woodenMachineMenu;

    public WoodenMachineScreen(WoodenMachineMenu woodenMachineMenu, Inventory inventory, Component component) {
        super(woodenMachineMenu, inventory, component);

        this.imageWidth = 176;
        this.imageHeight = 166;
        this.woodenMachineMenu = woodenMachineMenu;
    }

    @Override
    protected void init() {
        super.init();

        addRenderableWidget(new ImageButton(this.getGuiLeft() + BUTTON_XPOS, this.getGuiTop() + BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_ICON_U, BUTTON_ICON_V, BUTTON_HEIGHT, GUI_TEXTURE, (pressable) -> {
            assert this.minecraft != null;
            assert this.minecraft.gameMode != null;
            this.minecraft.gameMode.handleInventoryButtonClick(menu.containerId, 0);
        }));
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        this.minecraft.getTextureManager().bindForSetup(GUI_TEXTURE);

        int edgeSpacingX = (this.width - this.imageWidth) / 2;
        int edgeSpacingY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, edgeSpacingX, edgeSpacingY, 0, 0, this.imageWidth, this.imageHeight);

        // Draw process progress bar
        double progressFraction = woodenMachineMenu.getProgress();
        blit(poseStack, this.getGuiLeft() + PROCESS_BAR_XPOS, this.getGuiTop() + PROCESS_BAR_YPOS, PROCESS_BAR_ICON_U, PROCESS_BAR_ICON_V,
                (int)(progressFraction * PROCESS_BAR_WIDTH), PROCESS_BAR_HEIGHT);

        // Draw inertia progress gear
        double progress = woodenMachineMenu.getInertiaProgress();
        int yOffset = (int)((1.0 - progress) * GEAR_HEIGHT);
        blit(poseStack, this.getGuiLeft() + GEAR_XPOS, this.getGuiTop() + GEAR_YPOS + yOffset,
                GEAR_ICON_U, GEAR_ICON_V + yOffset, GEAR_WIDTH, GEAR_HEIGHT - yOffset);

        this.font.draw(poseStack, this.woodenMachineMenu.getItems().get(0).getDisplayName().getString(), this.leftPos + 118, this.topPos + 75, 0x404040);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int p_97796_, int p_97797_, float p_97798_) {
        this.renderBackground(poseStack);
        this.renderBg(poseStack, p_97798_, p_97796_, p_97797_);
        super.render(poseStack, p_97796_, p_97797_, p_97798_);
        this.renderTooltip(poseStack, p_97796_, p_97797_);
    }

    @Override
    public void recipesUpdated() {

    }

    @Override
    public @NotNull RecipeBookComponent getRecipeBookComponent() {
        return null;
    }
}
