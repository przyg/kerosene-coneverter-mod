package net.flywheel.kerosene_converter.block.entity;

import net.flywheel.kerosene_converter.block.ModBlockEntities;
import net.flywheel.kerosene_converter.sounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KeroConvBlockEntity extends BlockEntity {

    private final FluidTank inputTank = new FluidTank(4000, stack -> stack.getFluid().getFluidType().toString().equals("tfmg:kerosene"));
    private final FluidTank outputTank = new FluidTank(4000, stack -> stack.getFluid().getFluidType().toString().equals("crusty_chunks:kerosene"));

    private final LazyOptional<IFluidHandler> inputHandler = LazyOptional.of(() -> inputTank);
    private final LazyOptional<IFluidHandler> outputHandler = LazyOptional.of(() -> outputTank);

    public KeroConvBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEROCONV_BLOCK_ENTITY.get(), pos, state);
    }

    // tick logic
    public static void tick(BlockPos pos, BlockState state, KeroConvBlockEntity be) {
        if (be.inputTank.getFluidAmount() >= 50 && be.outputTank.getSpace() >= 50) {
            // zabierz 50mb z inputu
            be.inputTank.drain(50, IFluidHandler.FluidAction.EXECUTE);

            // dodaj 50mb outputu
            var fluid = net.minecraftforge.registries.ForgeRegistries.FLUIDS.getValue(
                    new net.minecraft.resources.ResourceLocation("crusty_chunks", "kerosene")
            );
            if (fluid != null) {
                be.outputTank.fill(new FluidStack(fluid, 50), IFluidHandler.FluidAction.EXECUTE);
            }

            // === ODTPRAWIANIE DŹWIĘKU ===
            Level level = be.getLevel();
            if (level != null) {
                level.playLocalSound(be.getBlockPos().getX() + 0.5,
                        be.getBlockPos().getY() + 0.5,
                        be.getBlockPos().getZ() + 0.5,
                        ModSounds.CONVERTER_LOOP.get(),
                        SoundSource.BLOCKS,
                        1.0F, 1.0F,
                        false);
            }

            be.setChanged();
        }
    }

    // capability handling

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            if (side != null && side == getBlockState().getValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING)) {
                return outputHandler.cast(); // front = output
            } else {
                return inputHandler.cast(); // reszta = input
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("InputTank", inputTank.writeToNBT(new CompoundTag()));
        tag.put("OutputTank", outputTank.writeToNBT(new CompoundTag()));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inputTank.readFromNBT(tag.getCompound("InputTank"));
        outputTank.readFromNBT(tag.getCompound("OutputTank"));
    }
}
