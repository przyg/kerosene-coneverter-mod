package net.flywheel.kerosene_converter.block;

import net.flywheel.kerosene_converter.block.entity.KeroConvBlockEntity;
import net.flywheel.kerosene_converter.kerosene_converter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, kerosene_converter.MOD_ID);

    public static final RegistryObject<BlockEntityType<KeroConvBlockEntity>> KEROCONV_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("keroconv_block_entity",
                    () -> BlockEntityType.Builder.of(KeroConvBlockEntity::new, ModBlocks.KEROCONVBLOCK.get()).build(null));

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
