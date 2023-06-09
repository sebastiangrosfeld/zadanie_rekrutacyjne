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
            } else {
                if(block.getColor().equals(color)) {
                    resultBlock = block;
                    break;
                }
            }
        }
        return Optional.ofNullable(resultBlock);
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
                findBlocksByMaterialFromCompositeBlock(addedBlocks,material,(CompositeBlock) block);
            } else {
                if(block.getMaterial().equals(material)) {
                    addedBlocks.add(block);
                }
            }
        }
        return addedBlocks;
    }

    private void findBlocksByMaterialFromCompositeBlock(List<Block> addedBlocks, String material, CompositeBlock compositeBlock) {
        for(Block block : compositeBlock.getBlocks()) {
            if (block instanceof CompositeBlock) {
                findBlocksByMaterialFromCompositeBlock(addedBlocks, material,(CompositeBlock) block);
            } else {
                if(block.getMaterial().equals(material)) {
                    addedBlocks.add(block);
                }
            }
        }
    }

    @Override
    public int count() {
        int preCounter = 0;
        for(Block block : blocks) {
            if(block instanceof CompositeBlock){
               preCounter = countBlocksFromCompositeBlock(preCounter,(CompositeBlock) block);
            } else {
                preCounter++;
            }
        }
        return preCounter;
    }

    private int countBlocksFromCompositeBlock(int preCounter, CompositeBlock compositeBlock) {
        for(Block block : compositeBlock.getBlocks()) {
            if (block instanceof CompositeBlock) {
                preCounter = countBlocksFromCompositeBlock(preCounter,(CompositeBlock) block);
            } else {
                preCounter++;
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
