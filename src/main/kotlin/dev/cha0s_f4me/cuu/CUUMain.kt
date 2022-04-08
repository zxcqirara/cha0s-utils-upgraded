package dev.cha0s_f4me.cuu

import dev.cha0s_f4me.cuu.command.RepairCommand
import dev.cha0s_f4me.cuu.command.time.DayCommand
import dev.cha0s_f4me.cuu.command.time.NightCommand
import dev.cha0s_f4me.cuu.command.weather.RainCommand
import dev.cha0s_f4me.cuu.command.weather.SunCommand
import dev.cha0s_f4me.cuu.command.weather.ThunderCommand
import dev.cha0s_f4me.cuu.item.DeathStickItem
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.block.Blocks
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class CUUMain : ModInitializer {
	companion object {
		val CUU_CTAB: ItemGroup = FabricItemGroupBuilder.build(
			Identifier("cuu", "cuu_ctab")
		) { ItemStack(Blocks.COMMAND_BLOCK) }
	}

	override fun onInitialize() {
		Registry.register(Registry.ITEM, Identifier("cuu", "death_stick"), DeathStickItem())

		CommandRegistrationCallback.EVENT.register { dispatcher, _ -> DayCommand.register(dispatcher) }
		CommandRegistrationCallback.EVENT.register { dispatcher, _ -> NightCommand.register(dispatcher) }

		CommandRegistrationCallback.EVENT.register { dispatcher, _ -> RainCommand.register(dispatcher) }
		CommandRegistrationCallback.EVENT.register { dispatcher, _ -> SunCommand.register(dispatcher) }
		CommandRegistrationCallback.EVENT.register { dispatcher, _ -> ThunderCommand.register(dispatcher) }

		CommandRegistrationCallback.EVENT.register { dispatcher, _ -> RepairCommand.register(dispatcher) }
	}
}
