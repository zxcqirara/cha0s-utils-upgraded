package dev.cha0s_f4me.cuu.item

import dev.cha0s_f4me.cuu.CUUMain
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class DeathStickItem : Item(
	FabricItemSettings()
		.group(CUUMain.CUU_CTAB)
		.maxCount(1)
) {
	override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
		target?.damage(DamageSource.player(attacker as PlayerEntity), 999999f)
			?: return false

		return true
	}

	override fun useOnEntity(stack: ItemStack?, user: PlayerEntity?, entity: LivingEntity?, hand: Hand?): ActionResult {
		entity?.remove(Entity.RemovalReason.KILLED)
			?: return ActionResult.FAIL

		return ActionResult.SUCCESS
	}
}