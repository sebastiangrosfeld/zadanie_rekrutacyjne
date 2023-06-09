package org.example;

public class ExBlock implements Block{

    private String color;

    private String material;

    public ExBlock(String color, String material){
        this.color =color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }
}
