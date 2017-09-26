package GUI.lab1;

import javafx.scene.control.TextField;

class ZeydelCheckerDiagonal {

    boolean getDiagonal(int lengthMatrixX, TextField[][] matrixTextField) {
        double[][] dataClone = transformTheInputData(matrixTextField, lengthMatrixX);
        for (int i = 0; i < lengthMatrixX; i++) {
            for (int j = 0; j < lengthMatrixX; j++) {
                if (j != i) {
                    if (Math.abs(dataClone[i][j]) >= Math.abs(dataClone[i][i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private double[][] transformTheInputData(TextField[][] matrixTextField, int lengthMatrixX) {
        double[][] dataClone = new double[25][25];
        for (int i = 1; i < lengthMatrixX + 2; i++) {
            for (int j = 1; j < lengthMatrixX + 1; j++) {
                dataClone[j - 1][i - 1] = Double.parseDouble(matrixTextField[i][j].getText());
            }
        }
        return dataClone;
    }

}
