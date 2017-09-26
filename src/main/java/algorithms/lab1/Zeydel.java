package algorithms.lab1;

import GUI.lab1.InterfaceMatrixStrategy;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class Zeydel implements InterfaceMatrixStrategy {

    private final int lengthMatrixX;
    private final double epsilon;
    private double[][] dataClone = new double[25][25];
    private int numberOfIteration;

    public Zeydel(TextField[][] matrixTextField, int lengthMatrixX, double epsilon) {
        this.lengthMatrixX = lengthMatrixX;
        this.epsilon = epsilon;
        transformTheInputData(matrixTextField);
    }

    private void transformTheInputData(TextField[][] matrixTextField) {
        for (int i = 1; i < lengthMatrixX + 2; i++) {
            for (int j = 1; j < lengthMatrixX + 1; j++) {
                this.dataClone[j - 1][i - 1] = Double.parseDouble(matrixTextField[i][j].getText());
            }
        }
    }

    @Override
    public double[] findSolution() {
        double error;
        int size = lengthMatrixX;
        double[] previousVariableValues = new double[size];
        for (int i = 0; i < size; i++) {
            previousVariableValues[i] = 0.0;
        }

        // Будем выполнять итерационный процесс до тех пор,
        // пока не будет достигнута необходимая точность
        do {
            // Введем вектор значений неизвестных на текущем шаге
            double[] currentVariableValues = new double[size];

            // Посчитаем значения неизвестных на текущей итерации
            // в соответствии с теоретическими формулами
            for (int i = 0; i < size; i++) {
                // Инициализируем i-ую неизвестную значением
                // свободного члена i-ой строки матрицы
                currentVariableValues[i] = dataClone[i][size];

                // Вычитаем сумму по всем отличным от i-ой неизвестным
                for (int j = 0; j < size; j++) {
                    // При j < i можем использовать уже посчитанные
                    // на этой итерации значения неизвестных
                    if (j < i) {
                        currentVariableValues[i] -= dataClone[i][j] * currentVariableValues[j];
                    }

                    // При j > i используем значения с прошлой итерации
                    if (j > i) {
                        currentVariableValues[i] -= dataClone[i][j] * previousVariableValues[j];
                    }
                }

                // Делим на коэффициент при i-ой неизвестной
                currentVariableValues[i] /= dataClone[i][i];
            }

            // Посчитаем текущую погрешность относительно предыдущей итерации
            error = 0.0;

            for (int i = 0; i < size; i++) {
                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }

            numberOfIteration++;
            // Переходим к следующей итерации, так
            // что текущие значения неизвестных
            // становятся значениями на предыдущей итерации
            previousVariableValues = currentVariableValues;
            // Если необходимая точность достигнута, то завершаем процесс
        } while (error > epsilon);
        return previousVariableValues;
    }

    public int getNumberOfIteration() {
        return numberOfIteration;
    }

    public HashMap<Integer, Double> getErrors(double[] unknowns, TextField[][] matrixTextField) {
        HashMap<Integer, Double> dataAnswers = new HashMap<>();
        for (int i = 1; i < lengthMatrixX + 1; i++) {
            dataAnswers.put(i - 1, Double.parseDouble(matrixTextField[lengthMatrixX + 1][i].getText()));
        }

        HashMap<Integer, Double> errors = new HashMap<>();
        for (int i = 0; i < lengthMatrixX; i++) {
            double sum = 0;
            for (int j = 0; j < lengthMatrixX; j++) {
                sum += dataClone[i][j] * unknowns[j];
            }
            errors.put(i, sum - dataAnswers.get(i));
        }
        return errors;
    }

    @Override
    public double getDeterminant() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }


}