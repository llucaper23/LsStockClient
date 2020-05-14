package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CandleGraphView extends JPanel {

    private int padding = 25;
    private int lablePadding = 25;
    private Color gridColor = new Color(200,200,200,200);
    private static final Stroke GRAPH_STOKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivision = 10;
    private String[] accionsX;
    private Color[] color;
    private double[][] accionsValor;
    private int indexList;
    private int heightm;
    private int heigt;
    private int valueQ;
    private double maxAccions = 0;
    private double minAccions = 0;


    /*
    * Aquest metode separa els valors que prenen la companyia i l'imformació del eix de les X
    *
    * */

    public CandleGraphView(ArrayList<String> accions, Color[] color){

        this.color = color;
        indexList = 0;
        this.accionsValor = new double[accions.size()/5][4];
        this.accionsX = new String[accions.size()/5];


        //Guardem per separat el valor de les accions i el valor que anira al eix X
        for (int i = 0; i <accions.size()/5 ; i++) {
            this.accionsX[i] = accions.get(indexList);
            for (int j = 0; j < 4; j++) {
                this.accionsValor[i][j] = Double.parseDouble(accions.get(indexList+1));
                indexList++;
            }
            indexList++;
        }

    }

    /*
    * pinta les dades corresponents al grafic per a la visualitzacio de l'usuari
    * */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        maxAccions = getMaxAccions();
        minAccions = getMinAccions();
        if (minAccions == maxAccions && minAccions !=0 && maxAccions != 10){

            maxAccions += 5;
            minAccions -= 5;

        }

        // Apliquem una escala al grafic per tal d'adaptar-lo al espai que s'utilitzarà per pintar-lo.

        double xScale = ((double) getWidth() - (2 * padding) - lablePadding) / ((accionsValor.length) -1 );
        double yScale = ((double) getHeight() - 2 * padding - lablePadding) / (maxAccions - minAccions);

        java.util.List<Integer> graphPointsX = new ArrayList<>();
        java.util.List<Integer> graphPointsY = new ArrayList<>();


        //Guardem les posicions del punts de cada una de les barreres per tal de pintarles posteriormetn amb l'escalatge corresponent

        for (int i = 0; i < (accionsValor.length) ; i++) {
            int x1 = (int) ((i) * xScale + padding + lablePadding);
            int y1 = (int) ((maxAccions - Double.parseDouble(String.valueOf(accionsValor[i][1]))) * yScale + padding);
            int y2 = (int) ((Double.parseDouble(String.valueOf(accionsValor[i][1])) - Double.parseDouble(String.valueOf(accionsValor[i][0]))) * yScale);

            graphPointsX.add(x1);
            graphPointsX.add(y1);
            graphPointsX.add(y2);

            if (accionsValor[i][2] <= accionsValor[i][3]){
                int y3 = (int) ((maxAccions -Double.parseDouble(String.valueOf(accionsValor[i][3]))) * yScale + padding);
                graphPointsY.add(y3);
                int y4 = (int) ((Double.parseDouble(String.valueOf(accionsValor[i][3])) - Double.parseDouble(String.valueOf(accionsValor[i][2]))) * yScale);
                graphPointsY.add(y4);

            }else{
                int y3 = (int) ((maxAccions -Double.parseDouble(String.valueOf(accionsValor[i][2]))) * yScale + padding);
                graphPointsY.add(y3);
                int y4 = (int) ((Double.parseDouble(String.valueOf(accionsValor[i][2])) - Double.parseDouble(String.valueOf(accionsValor[i][3]))) * yScale);
                graphPointsY.add(y4);



            }


        }


    }





}
