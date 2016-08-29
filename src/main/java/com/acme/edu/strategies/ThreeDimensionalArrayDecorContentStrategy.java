package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

public class ThreeDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + processTwoDimensionalArray((int[][][])message.getValue()) + "}";
    }

    private String processTwoDimensionalArray(int[][][] array3D) {
        TwoDimensionalArrayDecorContentStrategy array2DecorContentStrategy = new TwoDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[][] array2D : array3D) {
            result += array2DecorContentStrategy.decorateContent(new Message(array2D));
            result += System.lineSeparator();
        }
        return result;
    }
}
