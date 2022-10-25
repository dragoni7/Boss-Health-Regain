package com.github.dragoni7;


import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BossHealthRegain.MODID)
public class PlayerDeathEvent {
	
	@SubscribeEvent
	public static void bossHealthRegain(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof ServerPlayer) {
			int radius = Config.RADIUS.get();
			BlockPos lastPosition = entity.blockPosition();
			List<LivingEntity> bosses = entity.getLevel().getEntitiesOfClass(LivingEntity.class, new AABB(lastPosition).inflate(radius, radius, radius));
			for(LivingEntity boss : bosses) {
				String key = boss.getType().builtInRegistryHolder().key().location().toString();
				if (Config.BOSSES.get().contains(key)) {
					boss.heal((float) (boss.getMaxHealth() * Config.HEALTH_FACTOR.get()));
				}
			}
		}
	}
}
