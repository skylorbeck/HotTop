package website.skylorbeck.minecraft.hottop.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InGameHud.class)
public class HotBarMixin {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderInGuiWithOverrides(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;III)V"),
            method = "renderHotbarItem",
            index = 3)
    private int renderItems(int y) {
        return 3;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;II)V"),
            method = "renderHotbarItem",
            index = 3)
    private int renderItemsText(int y) {
        return 3;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            method = "renderHotbar",
            index = 2,
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;setZOffset(I)V", ordinal = 0),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 0)
            ))
    private int renderBar(int y) {
        return 0;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            method = "renderHotbar",
            index = 2,
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 0),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;setZOffset(I)V", ordinal = 1)
            ))
    private int renderOffBar(int y) {
        return -1;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 0),
            method = "renderHotbar",
            index = 2,
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", ordinal = 1)
            ))
    private int moveCooldown(int y) {
        return (int) (0);
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1),
            method = "renderHotbar",
            index = 2,
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", ordinal = 1)
            ))
    private int moveCooldown2(int y) {
        return (int) (19-MinecraftClient.getInstance().player.getAttackCooldownProgress(0.0f)*19);
    }

    @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHealthBar(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V"),
            method = "renderStatusBars")
    private void moveHearts(Args args) {
        args.set(3,27);
        args.set(4,-1*(int)args.get(4));
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            method = "renderStatusBars",
    index = 2,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getProfiler()Lnet/minecraft/util/profiler/Profiler;", ordinal = 2),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getProfiler()Lnet/minecraft/util/profiler/Profiler;", ordinal = 3)
    ))
    private int moveFood(int y) {
        return 27;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            method = "renderStatusBars",
    index = 2,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getProfiler()Lnet/minecraft/util/profiler/Profiler;", ordinal = 3)
    ))
    private int moveAir(int y) {
        return 37;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            method = "renderStatusBars",
    index = 2,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getProfiler()Lnet/minecraft/util/profiler/Profiler;", ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getProfiler()Lnet/minecraft/util/profiler/Profiler;", ordinal = 1)
    ))
    private int moveArmor(int y) {
        return 37;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
            method = "renderExperienceBar",
    index = 3,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 3)
    ))
    private float moveExperience(float y) {
        return 30;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
            method = "renderExperienceBar",
    index = 3,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 2),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 4)
    ))
    private float moveExperience2(float y) {
        return 31;
    }
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
            method = "renderExperienceBar",
    index = 3,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 3),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 5)
    ))
    private float moveExperience3(float y) {
        return 29;
    }
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
            method = "renderExperienceBar",
    index = 3,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 4)
    ))
    private float moveExperience4(float y) {
        return 30;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            method = "renderExperienceBar",
    index = 2,
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getNextLevelExperience()I"),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V")
    ))
    private int moveExperience5(int y) {
        return 22;
    }
    @ModifyVariable(method = "renderMountHealth", at = @At("STORE"), ordinal = 2)
    private int moveMountHealth(int y) {
        return 37;
    }
    //this is supposed to flip the selector box but it doesn't.
    /*@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1),
            method = "renderHotbar")
    private void flipSelector(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        matrices.scale(1, -1, 1);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "renderHotbar")
    private void flipSelectorBack(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        matrices.scale(1, -1, 1);
    }*/
}