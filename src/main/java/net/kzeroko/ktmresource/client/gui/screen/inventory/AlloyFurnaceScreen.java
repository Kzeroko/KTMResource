package net.kzeroko.ktmresource.client.gui.screen.inventory;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.containers.AlloyFurnaceContainer;
import net.kzeroko.ktmresource.network.KTMPRNetworkHandler;
import net.kzeroko.ktmresource.network.UpdateAlloyFurnacePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlloyFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceContainer> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(KTMResource.MOD_ID, "textures/gui/container/alloy_furnace.png");
    private boolean widthTooNarrowIn;

    public AlloyFurnaceScreen(AlloyFurnaceContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name);
    }

    // set gadgets and their positions
    public void init() {
        super.init();
        this.widthTooNarrowIn = this.width < 379;
        this.addRenderableWidget(new ConfirmButton(142, 20, this)); // 33
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.isForgeActive()) {
            int k = this.menu.getForgeTimeScaled();
            this.blit(matrixStack, i + 20, j + 60 - (int) Math.floor(k*36/20), 185,  36-(int) Math.floor(k*36/20), 3, (int) Math.floor((k*36/20)));
        }
    }

    // TBD
    // 1.18 has a mouseClicked, add or not?

    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        return mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.imageWidth) || mouseY >= (double)(guiTopIn + this.imageHeight);
    }

    @OnlyIn(Dist.CLIENT)
    class ConfirmButton extends Button {
        public ConfirmButton(int x, int y, AlloyFurnaceScreen screen) {
            super(x, y, screen);
        }

        @Override
        protected void renderIcon(PoseStack matrixStack) {
        }

        public void onPress() {
            // for test, why no effect?
//            MMNetworkHandler.sendToServer(new UpdateGemForgePacket(true));
            // here is RecipeValid => msg for SetForgeActive
            if (AlloyFurnaceScreen.this.menu.isRecipeValid()) {
                KTMPRNetworkHandler.sendToServer(new UpdateAlloyFurnacePacket(true));
            }
        }

        @Override
        public void updateNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }
    }

    @OnlyIn(Dist.CLIENT)
    abstract static class Button extends AbstractButton {
        private final AlloyFurnaceScreen screen;
        private final int xOffset;
        private final int yOffset;

        protected Button(int x, int y, AlloyFurnaceScreen screen) {
            super(x, y, 20, 20, TextComponent.EMPTY);
            this.screen = screen;
            this.xOffset = x;
            this.yOffset = y;
            this.x = this.xOffset + this.screen.leftPos;
            this.y = this. yOffset + this.screen.topPos;
        }

        public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            Minecraft.getInstance().getTextureManager().bindForSetup(AlloyFurnaceScreen.GUI_TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            int i = 165;
            int j = 0;
            if (this.screen.menu.isForgeActive()) {
                j += this.width;
            } else if (!this.screen.menu.isRecipeValid()) {
                j += this.width * 2;
            } else if (this.isHoveredOrFocused()) {
                j += this.width * 3;
            }

            this.x = this.xOffset + this.screen.leftPos;
            this.y = this. yOffset + this.screen.topPos;

            this.active = this.screen.menu.isRecipeValid() && !this.screen.menu.isForgeActive();

            this.blit(matrixStack, this.x, this.y, j, i, this.width, this.height);
            this.screen.itemRenderer.renderGuiItem(new ItemStack(Items.LAVA_BUCKET), this.x + 2, this.y + 2);
            this.renderIcon(matrixStack);
        }

        protected abstract void renderIcon(PoseStack p_230454_1_);
    }
}
