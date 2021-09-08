import Interface.UserInterface;

public class Main {
    public static void main(String[] args) {
        try {
            new UserInterface();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}