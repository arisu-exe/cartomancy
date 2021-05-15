package io.github.fallOut015.cartomancy.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.cartomancy.entity.effect.SigilEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class SigilModel<T extends SigilEntity> extends EntityModel<T> {
    ModelRenderer sigil;

    public SigilModel() {
        this(RenderType::entityCutoutNoCull, 64, 64);
    }
    private SigilModel(Function<ResourceLocation, RenderType> renderTypeIn, int w, int h) {
        super(renderTypeIn);

        this.sigil = new ModelRenderer(this);
        this.sigil.setPos(0, 24, 0);
        this.sigil.setTexSize(w, h);
        this.sigil.texOffs(0, 0).addBox(-w / 2f, 0, -h / 2f, w, 0.0625f, h, 0f, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.sigil.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.sigil.yRot = ageInTicks / 10f;
    }
}