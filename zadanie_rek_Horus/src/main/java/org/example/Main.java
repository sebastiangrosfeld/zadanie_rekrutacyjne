package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Wall wall = new Wall();
        List<Block> exList = new ArrayList<>();
        exList.add(new ExBlock("zielony","stal"));
        exList.add(new ExBlock("zielony","stal"));
        exList.add(new ExBlock("zielony","stal"));
        System.out.println(exList);
        List<Block> secList = new ArrayList<>();
        CompositeBlock compositeBlock = new ExCompositeBlock("","",exList);
        for(int i=0;i<2;i++){
            secList.clear();
            secList.add(compositeBlock);
            secList.add(new ExBlock("czerwony", "drewno"));
            CompositeBlock compositeBlock1 = new ExCompositeBlock("","",secList);
            wall.addBlockToStructure(compositeBlock);
            wall.addBlockToStructure(compositeBlock1);
            wall.addBlockToStructure(new ExBlock("czarny","aluminium"));
        }
        Structure structure = wall;
        System.out.println(wall.getBlocks() + " root");
        System.out.println(structure.findBlockByColor("czerwony"));
        System.out.println(structure.findBlocksByMaterial("drewno") + " drewno");
        System.out.println(structure.count());
    }
}