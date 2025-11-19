public class Main {
    public static void main(String[] args) {
        ControllerStrategy controller = new ControllerStrategy();
        controller.setStrategy(new GoFishStrategy());
        controller.startGame();
    }
}