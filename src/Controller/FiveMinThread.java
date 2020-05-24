package Controller;

public class FiveMinThread extends Thread{
    private PrincipalController principalController;

    public FiveMinThread(PrincipalController principalController) {
        this.principalController = principalController;
    }

    /**
     * Procedimetn run del thread que mira el preu de fa 5min d'un companyia
     */
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
