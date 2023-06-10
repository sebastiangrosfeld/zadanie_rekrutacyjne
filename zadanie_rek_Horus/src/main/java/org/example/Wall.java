package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure{
    private List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        Block resultBlock = null;
        for(Block block : blocks) {
            if (block instanceof CompositeBlock) {
              resultBlock = findBlockByColorFromCompositeBlock(resultBlock,color,(CompositeBlock) block);
                if(resultBlock != null)
                    return Optional.of(resultBlock);
            } else {
                if(block.getColor().equals(color)) {
                   return Optional.of(block);
                }
            }
        }
        return Optional.empty();
    }

    private Block findBlockByColorFromCompositeBlock(Block resultBlock, String color, CompositeBlock compositeBlock) {
        for(Block loopBlock : compositeBlock.getBlocks()) {
            if (loopBlock instanceof CompositeBlock) {
                resultBlock = findBlockByColorFromCompositeBlock(resultBlock,color,(CompositeBlock) loopBlock);
                if(resultBlock != null)
                    return resultBlock;
            } else {
                if(loopBlock.getColor().equals(color)) {
                    return loopBlock;
                }
            }
        }
        return resultBlock;
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> addedBlocks = new ArrayList<>();
        for(Block block : blocks) {
            if (block instanceof CompositeBlock) {
                 doActionInCompositeBlock("findBlocksByMaterial",0,addedBlocks,material,(CompositeBlock) block );
            } else {
                if(block.getMaterial().equals(material)) {
                    addedBlocks.add(block);
                }
            }
        }
        return addedBlocks;
    }

    @Override
    public int count() {
        int preCounter = 0;
        for(Block block : blocks) {
            if(block instanceof CompositeBlock){
                preCounter = doActionInCompositeBlock("count",preCounter,null,null,(CompositeBlock) block);
            } else {
                preCounter++;
            }
        }
        return preCounter;
    }

    private int doActionInCompositeBlock(String mode,int preCounter, List<Block> findingBlocks,String material,CompositeBlock compositeBlock) {
        for(Block block : compositeBlock.getBlocks()) {
            if (block instanceof CompositeBlock) {
                switch (mode) {
                    case "count" ->
                            preCounter = doActionInCompositeBlock("count", preCounter, findingBlocks, material, (CompositeBlock) block);
                    case "findBlocksByMaterial" ->
                            doActionInCompositeBlock("findBlocksByMaterial", preCounter, findingBlocks, material, (CompositeBlock) block);
                    default -> throw new IllegalArgumentException("Mode is uncorrect");
                }
            } else {
                switch (mode) {
                    case "count" -> preCounter++;
                    case "findBlocksByMaterial" -> {
                        if (block.getMaterial().equals(material)) {
                            findingBlocks.add(block);
                        }
                    }
                    default -> throw new IllegalArgumentException("Mode is uncorrect");
                }
            }
        }
        return preCounter;
    }

    public void addBlockToStructure(Block block) {
        this.blocks.add(block);
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }
}
