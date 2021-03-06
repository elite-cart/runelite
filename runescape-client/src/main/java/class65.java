import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("be")
public class class65 {
   @ObfuscatedName("r")
   @ObfuscatedGetter(
      intValue = 494577367
   )
   int field1131;
   @ObfuscatedName("o")
   int[][] field1135;
   @ObfuscatedName("h")
   @ObfuscatedGetter(
      intValue = -1953926715
   )
   int field1136;

   @ObfuscatedName("f")
   @ObfuscatedSignature(
      signature = "([BB)[B",
      garbageValue = "-47"
   )
   byte[] method1194(byte[] var1) {
      if(this.field1135 != null) {
         int var2 = (int)((long)var1.length * (long)this.field1131 / (long)this.field1136) + 14;
         int[] var3 = new int[var2];
         int var4 = 0;
         int var5 = 0;

         int var6;
         for(var6 = 0; var6 < var1.length; ++var6) {
            byte var7 = var1[var6];
            int[] var8 = this.field1135[var5];

            int var9;
            for(var9 = 0; var9 < 14; ++var9) {
               var3[var9 + var4] += var7 * var8[var9];
            }

            var5 += this.field1131;
            var9 = var5 / this.field1136;
            var4 += var9;
            var5 -= this.field1136 * var9;
         }

         var1 = new byte[var2];

         for(var6 = 0; var6 < var2; ++var6) {
            int var10 = var3[var6] + '耀' >> 16;
            if(var10 < -128) {
               var1[var6] = -128;
            } else if(var10 > 127) {
               var1[var6] = 127;
            } else {
               var1[var6] = (byte)var10;
            }
         }
      }

      return var1;
   }

   public class65(int var1, int var2) {
      if(var1 != var2) {
         int var3 = var1;
         int var4 = var2;
         if(var2 > var1) {
            var3 = var2;
            var4 = var1;
         }

         int var5;
         while(var4 != 0) {
            var5 = var3 % var4;
            var3 = var4;
            var4 = var5;
         }

         var1 /= var3;
         var2 /= var3;
         this.field1136 = var1;
         this.field1131 = var2;
         this.field1135 = new int[var1][14];

         for(var5 = 0; var5 < var1; ++var5) {
            int[] var6 = this.field1135[var5];
            double var7 = 6.0D + (double)var5 / (double)var1;
            int var9 = (int)Math.floor(1.0D + (var7 - 7.0D));
            if(var9 < 0) {
               var9 = 0;
            }

            int var10 = (int)Math.ceil(var7 + 7.0D);
            if(var10 > 14) {
               var10 = 14;
            }

            for(double var11 = (double)var2 / (double)var1; var9 < var10; ++var9) {
               double var13 = ((double)var9 - var7) * 3.141592653589793D;
               double var15 = var11;
               if(var13 < -1.0E-4D || var13 > 1.0E-4D) {
                  var15 = var11 * (Math.sin(var13) / var13);
               }

               var15 *= 0.54D + 0.46D * Math.cos(0.2243994752564138D * ((double)var9 - var7));
               var6[var9] = (int)Math.floor(0.5D + 65536.0D * var15);
            }
         }
      }

   }

   @ObfuscatedName("u")
   @ObfuscatedSignature(
      signature = "(II)I",
      garbageValue = "-91042756"
   )
   int method1195(int var1) {
      if(this.field1135 != null) {
         var1 = (int)((long)this.field1131 * (long)var1 / (long)this.field1136) + 6;
      }

      return var1;
   }

   @ObfuscatedName("i")
   @ObfuscatedSignature(
      signature = "(IB)I",
      garbageValue = "-67"
   )
   int method1198(int var1) {
      if(this.field1135 != null) {
         var1 = (int)((long)this.field1131 * (long)var1 / (long)this.field1136);
      }

      return var1;
   }

   @ObfuscatedName("m")
   @ObfuscatedSignature(
      signature = "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V",
      garbageValue = "221274543"
   )
   static void method1204(String var0, String var1, String var2) {
      class41.loginMessage1 = var0;
      class41.loginMessage2 = var1;
      class41.loginMessage3 = var2;
   }

   @ObfuscatedName("cc")
   @ObfuscatedSignature(
      signature = "([LWidget;IIIZI)V",
      garbageValue = "1728413861"
   )
   static void method1205(Widget[] var0, int var1, int var2, int var3, boolean var4) {
      for(int var5 = 0; var5 < var0.length; ++var5) {
         Widget var6 = var0[var5];
         if(var6 != null && var1 == var6.parentId) {
            ScriptState.method143(var6, var2, var3, var4);
            class16.method170(var6, var2, var3);
            if(var6.scrollX > var6.scrollWidth - var6.width) {
               var6.scrollX = var6.scrollWidth - var6.width;
            }

            if(var6.scrollX < 0) {
               var6.scrollX = 0;
            }

            if(var6.scrollY > var6.scrollHeight - var6.height) {
               var6.scrollY = var6.scrollHeight - var6.height;
            }

            if(var6.scrollY < 0) {
               var6.scrollY = 0;
            }

            if(var6.type == 0) {
               class2.method16(var0, var6, var4);
            }
         }
      }

   }
}
