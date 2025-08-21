package net.flywheel.kerosene_converter.sounds;

import net.flywheel.kerosene_converter.kerosene_converter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, kerosene_converter.MOD_ID);

    public static final RegistryObject<SoundEvent> CONVERTER_LOOP =
            SOUND_EVENTS.register("converter_loop", () -> SoundEvent.createVariableRangeEvent(
                    new ResourceLocation(kerosene_converter.MOD_ID, "converter_loop")
            ));

    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
