import net.runelite.mapping.ObfuscatedName;

@ObfuscatedName("bs")
public class class67 {
   @ObfuscatedName("d")
   static int[][] field1121 = new int[2][8];
   @ObfuscatedName("i")
   int[][][] field1123 = new int[2][2][4];
   @ObfuscatedName("a")
   int[][][] field1124 = new int[2][2][4];
   @ObfuscatedName("f")
   int[] field1125 = new int[2];
   @ObfuscatedName("u")
   int[] field1126 = new int[2];
   @ObfuscatedName("l")
   static float field1127;
   @ObfuscatedName("g")
   static int field1129;
   @ObfuscatedName("c")
   static float[][] field1132 = new float[2][8];

   @ObfuscatedName("i")
   float method1279(int var1, int var2, float var3) {
      float var4 = (float)this.field1123[var1][0][var2] + var3 * (float)(this.field1123[var1][1][var2] - this.field1123[var1][0][var2]);
      var4 *= 1.2207031E-4F;
      return method1292(var4);
   }

   @ObfuscatedName("u")
   float method1280(int var1, int var2, float var3) {
      float var4 = (float)this.field1124[var1][0][var2] + var3 * (float)(this.field1124[var1][1][var2] - this.field1124[var1][0][var2]);
      var4 *= 0.0015258789F;
      return 1.0F - (float)Math.pow(10.0D, (double)(-var4 / 20.0F));
   }

   @ObfuscatedName("a")
   int method1283(int var1, float var2) {
      float var3;
      if(var1 == 0) {
         var3 = (float)this.field1125[0] + (float)(this.field1125[1] - this.field1125[0]) * var2;
         var3 *= 0.0030517578F;
         field1127 = (float)Math.pow(0.1D, (double)(var3 / 20.0F));
         field1129 = (int)(field1127 * 65536.0F);
      }

      if(this.field1126[var1] == 0) {
         return 0;
      } else {
         var3 = this.method1280(var1, 0, var2);
         field1132[var1][0] = -2.0F * var3 * (float)Math.cos((double)this.method1279(var1, 0, var2));
         field1132[var1][1] = var3 * var3;

         int var4;
         for(var4 = 1; var4 < this.field1126[var1]; ++var4) {
            var3 = this.method1280(var1, var4, var2);
            float var5 = -2.0F * var3 * (float)Math.cos((double)this.method1279(var1, var4, var2));
            float var6 = var3 * var3;
            field1132[var1][var4 * 2 + 1] = field1132[var1][var4 * 2 - 1] * var6;
            field1132[var1][var4 * 2] = field1132[var1][var4 * 2 - 1] * var5 + field1132[var1][var4 * 2 - 2] * var6;

            for(int var7 = var4 * 2 - 1; var7 >= 2; --var7) {
               field1132[var1][var7] += field1132[var1][var7 - 1] * var5 + field1132[var1][var7 - 2] * var6;
            }

            field1132[var1][1] += field1132[var1][0] * var5 + var6;
            field1132[var1][0] += var5;
         }

         if(var1 == 0) {
            for(var4 = 0; var4 < this.field1126[0] * 2; ++var4) {
               field1132[0][var4] *= field1127;
            }
         }

         for(var4 = 0; var4 < this.field1126[var1] * 2; ++var4) {
            field1121[var1][var4] = (int)(field1132[var1][var4] * 65536.0F);
         }

         return this.field1126[var1] * 2;
      }
   }

   @ObfuscatedName("c")
   final void method1284(Buffer var1, class58 var2) {
      int var3 = var1.readUnsignedByte();
      this.field1126[0] = var3 >> 4;
      this.field1126[1] = var3 & 15;
      if(var3 != 0) {
         this.field1125[0] = var1.readUnsignedShort();
         this.field1125[1] = var1.readUnsignedShort();
         int var4 = var1.readUnsignedByte();

         int var5;
         int var6;
         for(var5 = 0; var5 < 2; ++var5) {
            for(var6 = 0; var6 < this.field1126[var5]; ++var6) {
               this.field1123[var5][0][var6] = var1.readUnsignedShort();
               this.field1124[var5][0][var6] = var1.readUnsignedShort();
            }
         }

         for(var5 = 0; var5 < 2; ++var5) {
            for(var6 = 0; var6 < this.field1126[var5]; ++var6) {
               if((var4 & 1 << var5 * 4 << var6) != 0) {
                  this.field1123[var5][1][var6] = var1.readUnsignedShort();
                  this.field1124[var5][1][var6] = var1.readUnsignedShort();
               } else {
                  this.field1123[var5][1][var6] = this.field1123[var5][0][var6];
                  this.field1124[var5][1][var6] = this.field1124[var5][0][var6];
               }
            }
         }

         if(var4 != 0 || this.field1125[1] != this.field1125[0]) {
            var2.method1060(var1);
         }
      } else {
         int[] var7 = this.field1125;
         this.field1125[1] = 0;
         var7[0] = 0;
      }

   }

   @ObfuscatedName("x")
   static float method1292(float var0) {
      float var1 = 32.703197F * (float)Math.pow(2.0D, (double)var0);
      return var1 * 3.1415927F / 11025.0F;
   }
}
