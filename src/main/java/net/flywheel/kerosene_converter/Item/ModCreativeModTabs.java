package net.flywheel.kerosene_converter.Item;

import net.flywheel.kerosene_converter.kerosene_converter;
import net.flywheel.kerosene_converter.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, kerosene_converter.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GB1 = CREATIVE_MODE_TABS.register("gb1",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST.get()))
                    .title(Component.translatable("creativetab.gb1"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // dodaj itemy tutaj eg. pOutput.accept(ModItems.*nazwa*.get());
                        output.accept(ModBlocks.KEROCONVBLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}