package dev.cha0s_f4me.cuu.command.weather

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.TranslatableText

object ThunderCommand {
	fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
		dispatcher.register(
			CommandManager.literal("thunder")
				.executes(::run)
		)
	}

	private fun run(ctx: CommandContext<ServerCommandSource>): Int {
		val source = ctx.source

		source.world.setWeather(0, 6000, true, true)
		source.sendFeedback(TranslatableText("commands.cuu.weather.success", "thunder"), false)

		return Command.SINGLE_SUCCESS
	}
}