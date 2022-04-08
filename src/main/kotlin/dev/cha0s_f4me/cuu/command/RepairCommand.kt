package dev.cha0s_f4me.cuu.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.TranslatableText

object RepairCommand {
	fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {

		dispatcher.register(
			CommandManager.literal("repair")
				.executes { run(it.source, it.source.player) }
				.then(
					CommandManager.argument("target", EntityArgumentType.players())
					.executes { run(it.source, EntityArgumentType.getPlayers(it, "target")) }
				)
		)
	}

	private fun run(source: ServerCommandSource, player: PlayerEntity): Int {
		if (player.mainHandStack == ItemStack.EMPTY)
			throw SimpleCommandExceptionType(TranslatableText("commands.cuu.repair.empty_hand")).create()
		if (!player.mainHandStack.isDamageable)
			throw SimpleCommandExceptionType(TranslatableText("commands.cuu.repair.isnt_damagable")).create()
		if (!player.mainHandStack.isDamaged)
			throw SimpleCommandExceptionType(TranslatableText("commands.cuu.repair.isnt_damaged")).create()

		player.mainHandStack.damage = 0
		source.sendFeedback(TranslatableText("commands.cuu.repair.success"), false)

		return Command.SINGLE_SUCCESS
	}

	private fun run(source: ServerCommandSource, entities: MutableCollection<out PlayerEntity>): Int {
		entities.forEach { entity ->
			if (entity.mainHandStack == ItemStack.EMPTY)
				throw SimpleCommandExceptionType(TranslatableText("commands.cuu.repair.empty_hand")).create()
			if (!entity.mainHandStack.isDamageable)
				throw SimpleCommandExceptionType(TranslatableText("commands.cuu.repair.isnt_damagable")).create()
			if (!entity.mainHandStack.isDamaged)
				throw SimpleCommandExceptionType(TranslatableText("commands.cuu.repair.isnt_damaged")).create()

			entity.mainHandStack.damage = 0
		}

		if (entities.size == 1)
			source.sendFeedback(TranslatableText("commands.cuu.repair.success.other", entities.first().name), false)
		else
			source.sendFeedback(TranslatableText("commands.cuu.repair.success.others"), false)

		return Command.SINGLE_SUCCESS
	}
}