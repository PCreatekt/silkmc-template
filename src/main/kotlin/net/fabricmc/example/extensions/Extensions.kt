package net.fabricmc.example.extensions

import net.silkmc.silk.core.Silk
import net.minecraft.core.Registry
import net.minecraft.network.chat.ChatType
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.text.literalText

@OptIn(DelicateSilkApi::class)
fun world(world_name: String): ServerLevel {
    val resourceKey: ResourceKey<Level> = ResourceKey.create(Registry.DIMENSION_REGISTRY, ResourceLocation(world_name))
    val world: ServerLevel? = Silk.currentServer!!.getLevel(resourceKey)
    return world!!
}

@OptIn(DelicateSilkApi::class)
fun broadcast(text: String, color: Int = 0xffffff, italic: Boolean = false, bold: Boolean = false) {
    Silk.currentServer?.playerList?.broadcastSystemMessage(literalText(text) {
        this.color = color
        this.italic = italic
        this.bold = bold
    }, true)
}
