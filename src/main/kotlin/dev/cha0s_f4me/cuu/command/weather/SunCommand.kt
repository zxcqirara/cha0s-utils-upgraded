package dev.cha0s_f4me.cuu.command.weather

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.TranslatableText

object SunCommand {
	fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
		dispatcher.register(
			CommandManager.literal("sun")
				.executes(::run)
		)
	}

	private fun run(ctx: CommandContext<ServerCommandSource>): Int {
		val source = ctx.source

		source.world.setWeather(6000, 0, false, false)
		source.sendFeedback(TranslatableText("commands.cuu.weather.success", "sun"), false)

		return Command.SINGLE_SUCCESS
	}
}