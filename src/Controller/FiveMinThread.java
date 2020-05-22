package Controller;

public class FiveMinThread extends Thread{
    private PrincipalController principalController;

    public FiveMinThread(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void run(){
        while (isAlive()){
            principalController.update5minPrice();
            try {
                sleep(60000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
