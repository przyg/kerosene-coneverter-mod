package net.flywheel.kerosene_converter.block;

import net.flywheel.kerosene_converter.block.custom.KeroConvBlock;
import net.flywheel.kerosene_converter.kerosene_converter;
import net.flywheel.kerosene_converter.Item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

//rejestruje funkcje BLOCKS
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, kerosene_converter.MOD_ID);

    public static final RegistryObject<Block> KEROCONVBLOCK = registerBlock("keroconvblock",
            () -> new KeroConvBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    //łączy z klasą GraveBane
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
