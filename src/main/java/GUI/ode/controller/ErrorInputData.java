package GUI.ode.controller;

public enum ErrorInputData {
    ERROR_X_START{
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: X начальное";
        }
    },
    ERROR_X_FINISH{
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: X конечное";
        }
    },
    ERROR_STEP{
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: шаг";
        }
    },
    ERROR_Y_ZERO {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: Y нулевое";
        }
    },
    ERROR_Y_FIRST {
        @Override
        public String getMessage() {
            return "Ошибка в поле ввода: Y первое";
        }
    },
    ALL_IS_WELL{
        @Override
        public String getMessage() {
            return "Данные введены верно";
        }
    };

    public abstract String getMessage();
}
