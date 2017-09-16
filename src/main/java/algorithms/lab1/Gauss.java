package algorithms.lab1;

import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Gauss<T extends Number> {
    private final int lengthMatrixX;
    private final TextField[][] matrixTextField;
    private int[][] dataClone = new int[25][25];
    private HashMap<Integer, Double> dataAnswers = new HashMap<>();

    public Gauss(TextField[][] matrixTextField, int lengthMatrixX) {
        this.matrixTextField = matrixTextField;
        this.lengthMatrixX = lengthMatrixX;
        cloneData();
    }

    private void cloneData() {
        for (int i = 1; i < lengthMatrixX + 1; i++) {
            for (int j = 1; j < lengthMatrixX + 1; j++) {
                this.dataClone[j - 1][i - 1] = Integer.parseInt(matrixTextField[i][j].getText());
            }
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            dataAnswers.put(i - 1, Double.parseDouble(matrixTextField[lengthMatrixX + 1][i].getText()));
        }
    }

    public void calculate() {
        for (int i = 1; i < lengthMatrixX; i++) {
            for (int j = i + 1; j < lengthMatrixX + 1; j++) {
                Double coefficient = findCoefficient(i, j);
                mull(i, j, coefficient);
            }
//        soutMatr();
        }
    }


    private void mull(int i, int j, Double coefficient) {
        for (int k = i; k < lengthMatrixX + 2; k++) {
            matrixTextField[k][j].setText(String.valueOf(Math.round(Double.parseDouble(matrixTextField[k][i].getText()) * coefficient + Double.parseDouble(matrixTextField[k][j].getText()))));
        }
    }

    private Double findCoefficient(int i, int j) {
        return (Double.parseDouble(matrixTextField[i][j].getText()) / Double.parseDouble(matrixTextField[i][i].getText())) * -1;
    }

    public HashMap<Integer, Double> getUnknowns() {
        double[][] data = new double[25][25];

        HashMap<Integer, Double> answers = new HashMap<>();
        HashMap<Integer, Double> unknownsNotCalculated = new HashMap<>();

        try {

            int dataI = -1;
            int dataJ = -1;
            int countAnswer = -1;
            int countUnknownsNotCalculated = -1;
            for (int i = lengthMatrixX - 1; i >= 1; i--) {
                int k = 0;
                dataJ = -1;
                dataI++;
                for (int j = lengthMatrixX + 1; j >= i; j--) {
                    k++;
                    if (j == i) {
                        countUnknownsNotCalculated++;
                        unknownsNotCalculated.put(countUnknownsNotCalculated, Double.parseDouble(matrixTextField[j][i].getText()));
                    } else {
                        if (k == 1) {
                            countAnswer++;
                            answers.put(countAnswer, Double.parseDouble(matrixTextField[j][i].getText()));
                        } else {
                            dataJ++;
                            data[dataI][dataJ] = Double.parseDouble(matrixTextField[j][i].getText());
                        }
                    }
                }
            }

            HashMap<Integer, Double> unknowns = new HashMap<>();
            unknowns.put(0, Double.parseDouble(matrixTextField[lengthMatrixX + 1][lengthMatrixX].getText()) / Double.parseDouble(matrixTextField[lengthMatrixX][lengthMatrixX].getText()));

            for (int i = 0; i <= dataI; i++) {
                mullColumn(unknowns.get(i), i, dataJ, data);
                unknowns.put(i + 1, getNewCoefficient(data, i, answers.get(i), unknownsNotCalculated.get(i), dataJ));
            }


            return unknowns;
        } catch (Exception e) {
            return null;
        }
    }

    private Double getNewCoefficient(double[][] data, int i, Double answer, Double unknownsNotCalculated, int dataJ) {
        double sum = 0;
        for (int j = 0; j <= dataJ; j++) {
            sum += data[i][j];
        }
        answer -= sum;
        return answer / unknownsNotCalculated;
    }

    private void mullColumn(Double coefficient, int j, int dataJ, double[][] data) {
        for (int i = 0; i <= dataJ; i++) {
            data[i][j] *= coefficient;
        }
    }

    public void soutMatr() {
        for (int i = lengthMatrixX; i >= 1; i--) {
            for (int j = lengthMatrixX + 1; j >= i; j--) {
                System.out.print(matrixTextField[j][i].getText() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public HashMap<Integer, Double> getErrors(Map<Integer, Double> unknowns) {
        HashMap<Integer, Double> errors = new HashMap<>();
        for (int i = 0; i < lengthMatrixX; i++) {
            int sum = 0;
            for (int j = 0; j < lengthMatrixX; j++) {
                sum += dataClone[i][j] * unknowns.get(j);
            }
            errors.put(i, sum - dataAnswers.get(i));
        }
        return errors;
    }

    public String getDeterminant() {
        double determinant = Double.parseDouble(matrixTextField[1][1].getText());
        System.out.println(determinant);
        for (int i = 2; i < lengthMatrixX + 1; i++) {
            System.out.println(matrixTextField[i][i].getText());
            determinant *= Double.parseDouble(matrixTextField[i][i].getText());
        }
        System.out.println();
        return String.valueOf(determinant);
    }
}
