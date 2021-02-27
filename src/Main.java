public class Main {

    public static Thread mainThread;
    public static MainMenu mainMenu;
    public static Window window;
    public static FinalScreen finalScreen;
    public static int state;

    public static void main(String[] args) {
        mainMenu = new MainMenu();
        mainThread = new Thread(mainMenu);
        mainThread.start();
    }

    public static void changeState(int newState) {
        if(newState == 1) {
            if (mainMenu.isRunning)
                mainMenu.stop();
            if (finalScreen != null && finalScreen.isRunning)
                finalScreen.stop();

            window = new Window();
            mainThread = new Thread(window);
            mainThread.start();
        }

        if (newState == 2) {
            if(window.isRunning)
                window.stop();
            if(mainMenu.isRunning)
                mainMenu.stop();

            finalScreen = new FinalScreen();
            mainThread = new Thread(finalScreen);
            mainThread.start();
        }

    }
}
