package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.common.SpookyItems;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {

    @Inject(at = @At("HEAD"), method = "isPlayerStaring", cancellable = true)
    private void isPlayerStaring(final PlayerEntity playerEntity, final CallbackInfoReturnable<Boolean> info) {
        TrinketComponent trinketPlayer = TrinketsApi.getTrinketComponent(playerEntity);
        if(trinketPlayer.getStack("head:mask").getItem().equals(SpookyItems.PAPER_BAG)) {
            info.setReturnValue(false);
        }
    }
}
