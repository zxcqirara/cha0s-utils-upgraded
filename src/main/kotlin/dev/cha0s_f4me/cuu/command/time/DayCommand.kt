package dev.cha0s_f4me.cuu.command.time

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.TranslatableText

object DayCommand {
	fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
		dispatcher.register(
			CommandManager.literal("day")
				.executes(::run)
		)
	}

	private fun run(ctx: CommandContext<ServerCommandSource>): Int {
		val source = ctx.source

		source.world.timeOfDay = 1_000
		source.sendFeedback(TranslatableText("commands.cuu.time.success", "day"), false)

		return Command.SINGLE_SUCCESS
	}
}