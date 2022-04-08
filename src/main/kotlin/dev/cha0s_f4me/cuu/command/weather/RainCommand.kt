package dev.cha0s_f4me.cuu.command.weather

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.TranslatableText

object RainCommand {
	fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
		dispatcher.register(
			CommandManager.literal("rain")
				.executes(::run)
		)
	}

	private fun run(ctx: CommandContext<ServerCommandSource>): Int {
		val source = ctx.source

		source.world.setWeather(0, 6000, true, false)
		source.sendFeedback(TranslatableText("commands.cuu.weather.success", "rain"), false)

		return Command.SINGLE_SUCCESS
	}
}