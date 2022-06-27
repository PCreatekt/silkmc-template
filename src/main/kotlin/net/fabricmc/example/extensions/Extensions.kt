package net.fabricmc.example.extensions

import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.text.sendText
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level

fun world(world_name: String): ServerLevel {
    val resourceKey: ResourceKey<Level> = ResourceKey.create(Registry.DIMENSION_REGISTRY, ResourceLocation(world_name))
    val world: ServerLevel? = Fabrik.currentServer!!.getLevel(resourceKey)
    return world!!
}

fun broadcast(text: String, color: Int = 0xffffff, italic: Boolean = false, bold: Boolean = false) {
    Silk.currentServer?.playerList?.players?.forEach {
        it.sendText {
            text(text) {
                this.color = color
                this.italic = italic
                this.bold = bold
            }
        }
    }
}
