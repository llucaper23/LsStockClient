package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CandleGraphView extends JPanel {

    private int padding = 25;
    private int labelPadding = 25;
    private Color gridColor = new Color(200,200,200,200);
    private static final Stroke GRAPH_STOKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private String[] accionsX;
    private Color[] color;
    private double[][] accionsValor;
    private int indexList;
    private int heightm;
    private int height;
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
        for (int i = 0; i < accions.size()/5 ; i++) {
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //maxAccions = getMaxAccions();
        //minAccions = getMinAccions();
        if (minAccions == maxAccions && minAccions !=0 && maxAccions != 10){

            maxAccions += 5;
            minAccions -= 5;

        }

        // Apliquem una escala al grafic per tal d'adaptar-lo al espai que s'utilitzarà per pintar-lo.

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / ((accionsValor.length) -1 );
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxAccions - minAccions);

        java.util.List<Integer> graphPointsX = new ArrayList<>();
        java.util.List<Integer> graphPointsY = new ArrayList<>();


        //Guardem les posicions del punts de cada una de les barreres per tal de pintarles posteriormetn amb l'escalatge corresponent

        for (int i = 0; i < (accionsValor.length) ; i++) {

            int x1 = (int) ((i) * xScale + padding + labelPadding);
            int y1 = (int) ((maxAccions - Double.parseDouble(String.valueOf(accionsValor[i][1]))) * yScale + padding);
            int y2 = (int) ((Double.parseDouble(String.valueOf(accionsValor[i][1])) - Double.parseDouble(String.valueOf(accionsValor[i][0]))) * yScale);

            graphPointsX.add(x1);
            graphPointsX.add(y1);
            graphPointsX.add(y2);

            if (accionsValor[i][2] <= accionsValor[i][3]){
                int y3 = (int) ((maxAccions - Double.parseDouble(String.valueOf(accionsValor[i][3]))) * yScale + padding);
                graphPointsY.add(y3);
                int y4 = (int) ((Double.parseDouble(String.valueOf(accionsValor[i][3])) - Double.parseDouble(String.valueOf(accionsValor[i][2]))) * yScale);
                graphPointsY.add(y4);

            }else{
                int y3 = (int) ((maxAccions - Double.parseDouble(String.valueOf(accionsValor[i][2]))) * yScale + padding);
                graphPointsY.add(y3);
                int y4 = (int) ((Double.parseDouble(String.valueOf(accionsValor[i][2])) - Double.parseDouble(String.valueOf(accionsValor[i][3]))) * yScale);
                graphPointsY.add(y4);

            }

        }

        //pintem el fons blanc del gràfic
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2*padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        //Creem el eix de les Y
        for(int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() + ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;

            if (accionsValor.length > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth - 5, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);

                String yLabel = ((int) (minAccions + (maxAccions - minAccions) * ((i * 1.0) / numberYDivisions) * 100) / 100.0) + "";

                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 10, y0 + (metrics.getHeight() / 2) - 3);

            }
        }

        //Creem l'eix de les X
        for (int i = 0; i < accionsValor.length; i++) {

            if (accionsValor.length > 1) {

                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (accionsValor.length -1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;

                if(( i % ((int)((accionsValor.length) / 20.0) + 1) + 1) == 0 ) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = accionsX[i];
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }

                g2.drawLine(x0, y0, x1, y1);

            }

        }

        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        //Pintem les barres amb els valors de les companyies
        int k = 0;

        for(int j = 0; j < accionsValor.length; j++) {

            valueQ = 0;

            height = graphPointsY.get(k+1);
            valueQ = graphPointsY.get(k);

            if(accionsValor[j][2] <= accionsValor[j][3]){

                g.setColor(color[1]);

            } else {

                g.setColor(color[0]);

            }

            g.fillRect(graphPointsX.get(j) - 1, valueQ, 2, height);
            g.setColor(Color.BLACK);

            int valueQm = 0;

            if((accionsValor[j][3] - accionsValor[j][2]) > 0){

                heightm = graphPointsY.get(k+3);
                valueQm = graphPointsY.get(k+2);

            } else {

                heightm = graphPointsY.get(k+3);
                valueQm = graphPointsY.get(k+2);

            }

            if (accionsValor[j][2] <= accionsValor[j][3]) {

                g.setColor(color[1]);

            } else {

                g.setColor(color[0]);

            }

            g.fillRect(graphPointsX.get(j) - 4, valueQm, 8, heightm);
            g.setColor(Color.BLACK);

            k = k+4;

        }

    }

}
