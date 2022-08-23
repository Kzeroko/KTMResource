package net.kzeroko.ktmresource.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class UnbreakableBlockTemplate extends Block {

    public UnbreakableBlockTemplate() {
        super(BlockBehaviour.Properties.copy(Blocks.BEDROCK));
    }
}
