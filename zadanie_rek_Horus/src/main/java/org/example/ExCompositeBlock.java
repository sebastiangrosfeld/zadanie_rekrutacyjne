package org.example;

import java.util.List;

public class ExCompositeBlock extends ExBlock implements CompositeBlock{

    List<Block> blocks;

    public ExCompositeBlock(String color,String material,List<Block> list){
        super(color, material);
        this.blocks = list;
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getMaterial() {
        return super.getMaterial();
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }
}
