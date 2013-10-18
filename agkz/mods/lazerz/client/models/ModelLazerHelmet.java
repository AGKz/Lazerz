// Date: 6/10/2013 11:55:35 AM
// Template version 1.1

// Model by HoopAWolf
// THANKS SO MUCH!






package agkz.mods.lazerz.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLazerHelmet extends ModelBase
{
 	//fields
    ModelRenderer b1;
    ModelRenderer b2;
    ModelRenderer b3;
    ModelRenderer b4;
    ModelRenderer b5;
    ModelRenderer b6;
    ModelRenderer b7;
    ModelRenderer b8;
    ModelRenderer b9;
    ModelRenderer b10;
    ModelRenderer n11;
    ModelRenderer b12;
    ModelRenderer b13;
    ModelRenderer b14;
    ModelRenderer b15;
    ModelRenderer b16;
    ModelRenderer b17;
    ModelRenderer b18;
    ModelRenderer b19;
    ModelRenderer b20;
    ModelRenderer b21;
    ModelRenderer b22;
    ModelRenderer b23;
    ModelRenderer b24;
    ModelRenderer b25;
    ModelRenderer b26;
    ModelRenderer b27;
  
  public ModelLazerHelmet()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      b1 = new ModelRenderer(this, 0, 0);
      b1.addBox(0F, 0F, 0F, 7, 1, 1);
      b1.setRotationPoint(-3F, 23F, 3F);
      b1.setTextureSize(64, 64);
      b1.mirror = true;
      setRotation(b1, 0F, 0F, 0F);
      b2 = new ModelRenderer(this, 0, 0);
      b2.addBox(0F, 0F, 0F, 7, 1, 1);
      b2.setRotationPoint(-3F, 23F, -5F);
      b2.setTextureSize(64, 64);
      b2.mirror = true;
      setRotation(b2, 0F, 0F, 0F);
      b3 = new ModelRenderer(this, 0, 0);
      b3.addBox(0F, 0F, 0F, 1, 1, 7);
      b3.setRotationPoint(-4F, 23F, -4F);
      b3.setTextureSize(64, 64);
      b3.mirror = true;
      setRotation(b3, 0F, 0F, 0F);
      b4 = new ModelRenderer(this, 0, 0);
      b4.addBox(0F, 0F, 0F, 1, 1, 7);
      b4.setRotationPoint(4F, 23F, -4F);
      b4.setTextureSize(64, 64);
      b4.mirror = true;
      setRotation(b4, 0F, 0F, 0F);
      b5 = new ModelRenderer(this, 0, 0);
      b5.addBox(0F, 0F, 0F, 7, 3, 1);
      b5.setRotationPoint(-3F, 20.3F, 2.3F);
      b5.setTextureSize(64, 64);
      b5.mirror = true;
      setRotation(b5, 0.1797689F, 0F, 0F);
      b6 = new ModelRenderer(this, 0, 0);
      b6.addBox(0F, 0F, 0F, 1, 3, 7);
      b6.setRotationPoint(-3.3F, 20.1F, -4F);
      b6.setTextureSize(64, 64);
      b6.mirror = true;
      setRotation(b6, 0F, 0F, 0.1745329F);
      b7 = new ModelRenderer(this, 0, 0);
      b7.addBox(0F, 0F, 0F, 1, 3, 7);
      b7.setRotationPoint(3.3F, 20.2F, -4F);
      b7.setTextureSize(64, 64);
      b7.mirror = true;
      setRotation(b7, 0F, 0F, -0.1745329F);
      b8 = new ModelRenderer(this, 0, 0);
      b8.addBox(0F, 0F, 0F, 7, 3, 1);
      b8.setRotationPoint(-3F, 20.1F, -4.3F);
      b8.setTextureSize(64, 64);
      b8.mirror = true;
      setRotation(b8, -0.1745329F, 0F, 0F);
      b9 = new ModelRenderer(this, 0, 0);
      b9.addBox(0F, 0F, 0F, 7, 1, 7);
      b9.setRotationPoint(-3F, 20F, -4F);
      b9.setTextureSize(64, 64);
      b9.mirror = true;
      setRotation(b9, 0F, 0F, 0F);
      b10 = new ModelRenderer(this, 0, 21);
      b10.addBox(-2F, 0F, -2F, 4, 1, 4);
      b10.setRotationPoint(0.5F, 19.5F, -0.5F);
      b10.setTextureSize(64, 64);
      b10.mirror = true;
      setRotation(b10, 0F, 0F, 0F);
      n11 = new ModelRenderer(this, 0, 21);
      n11.addBox(-2F, 0F, -2F, 4, 1, 4);
      n11.setRotationPoint(0.5F, 19.5F, -0.5F);
      n11.setTextureSize(64, 64);
      n11.mirror = true;
      setRotation(n11, 0F, -0.7853982F, 0F);
      b12 = new ModelRenderer(this, 0, 14);
      b12.addBox(0F, 0F, 0F, 1, 1, 1);
      b12.setRotationPoint(2.8F, 19.8F, 1.8F);
      b12.setTextureSize(64, 64);
      b12.mirror = true;
      setRotation(b12, 0F, 0F, 0F);
      b13 = new ModelRenderer(this, 0, 14);
      b13.addBox(0F, 0F, 0F, 1, 1, 1);
      b13.setRotationPoint(-2.8F, 19.8F, 1.8F);
      b13.setTextureSize(64, 64);
      b13.mirror = true;
      setRotation(b13, 0F, 0F, 0F);
      b14 = new ModelRenderer(this, 0, 14);
      b14.addBox(0F, 0F, 0F, 1, 1, 1);
      b14.setRotationPoint(2.8F, 19.8F, -3.8F);
      b14.setTextureSize(64, 64);
      b14.mirror = true;
      setRotation(b14, 0F, 0F, 0F);
      b15 = new ModelRenderer(this, 0, 14);
      b15.addBox(0F, 0F, 0F, 1, 1, 1);
      b15.setRotationPoint(-2.8F, 19.8F, -3.8F);
      b15.setTextureSize(64, 64);
      b15.mirror = true;
      setRotation(b15, 0F, 0F, 0F);
      b16 = new ModelRenderer(this, 0, 39);
      b16.addBox(0F, 0F, 0F, 2, 2, 2);
      b16.setRotationPoint(-0.5F, 18F, -1.5F);
      b16.setTextureSize(64, 64);
      b16.mirror = true;
      setRotation(b16, 0F, 0F, 0F);
      b17 = new ModelRenderer(this, 0, 39);
      b17.addBox(0F, 0F, 0F, 3, 2, 2);
      b17.setRotationPoint(-1F, 16.5F, -1F);
      b17.setTextureSize(64, 64);
      b17.mirror = true;
      setRotation(b17, 0F, 0F, 0F);
      b18 = new ModelRenderer(this, 0, 39);
      b18.addBox(0F, 0F, 0F, 2, 1, 6);
      b18.setRotationPoint(-0.5F, 17F, -7F);
      b18.setTextureSize(64, 64);
      b18.mirror = true;
      setRotation(b18, 0F, 0F, 0F);
      b19 = new ModelRenderer(this, 0, 39);
      b19.addBox(0F, 0F, 0F, 2, 1, 1);
      b19.setRotationPoint(-0.5F, 17.4F, -1F);
      b19.setTextureSize(64, 64);
      b19.mirror = true;
      setRotation(b19, -0.9948377F, 0F, 0F);
      b20 = new ModelRenderer(this, 0, 23);
      b20.addBox(-1.8F, -2.3F, -1.5F, 1, 1, 1);
      b20.setRotationPoint(0.5F, 19.5F, -0.5F);
      b20.setTextureSize(64, 64);
      b20.mirror = true;
      setRotation(b20, -0.7504916F, 0F, 0F);
      b21 = new ModelRenderer(this, 0, 23);
      b21.addBox(0.8F, -2.5F, 0F, 1, 1, 1);
      b21.setRotationPoint(0.5F, 19.5F, -0.5F);
      b21.setTextureSize(64, 64);
      b21.mirror = true;
      setRotation(b21, 0F, 0F, 0F);
      b22 = new ModelRenderer(this, 0, 39);
      b22.addBox(0F, 0F, 0F, 1, 1, 2);
      b22.setRotationPoint(0F, 15.7F, -2.5F);
      b22.setTextureSize(64, 64);
      b22.mirror = true;
      setRotation(b22, 0F, 0F, 0F);
      b23 = new ModelRenderer(this, 0, 39);
      b23.addBox(0F, 0F, 0F, 2, 1, 1);
      b23.setRotationPoint(-0.5F, 16.6F, -1F);
      b23.setTextureSize(64, 64);
      b23.mirror = true;
      setRotation(b23, -0.9948377F, 0F, 0F);
      b24 = new ModelRenderer(this, 0, 23);
      b24.addBox(-1.8F, -2.5F, 0F, 1, 1, 1);
      b24.setRotationPoint(0.5F, 19.5F, -0.5F);
      b24.setTextureSize(64, 64);
      b24.mirror = true;
      setRotation(b24, 0F, 0F, 0F);
      b25 = new ModelRenderer(this, 0, 23);
      b25.addBox(0.8F, -2.3F, -1.5F, 1, 1, 1);
      b25.setRotationPoint(0.5F, 19.5F, -0.5F);
      b25.setTextureSize(64, 64);
      b25.mirror = true;
      setRotation(b25, -0.7504916F, 0F, 0F);
      b26 = new ModelRenderer(this, 0, 39);
      b26.addBox(0F, 0F, 0F, 1, 1, 1);
      b26.setRotationPoint(0F, 16F, -0.6F);
      b26.setTextureSize(64, 64);
      b26.mirror = true;
      setRotation(b26, -0.7679449F, 0F, 0F);
      b27 = new ModelRenderer(this, 0, 39);
      b27.addBox(0F, 0F, 0F, 1, 2, 1);
      b27.setRotationPoint(0F, 15.7F, -2.5F);
      b27.setTextureSize(64, 64);
      b27.mirror = true;
      setRotation(b27, -0.5759587F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    b1.render(f5);
    b2.render(f5);
    b3.render(f5);
    b4.render(f5);
    b5.render(f5);
    b6.render(f5);
    b7.render(f5);
    b8.render(f5);
    b9.render(f5);
    b10.render(f5);
    n11.render(f5);
    b12.render(f5);
    b13.render(f5);
    b14.render(f5);
    b15.render(f5);
    b16.render(f5);
    b17.render(f5);
    b18.render(f5);
    b19.render(f5);
    b20.render(f5);
    b21.render(f5);
    b22.render(f5);
    b23.render(f5);
    b24.render(f5);
    b25.render(f5);
    b26.render(f5);
    b27.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
