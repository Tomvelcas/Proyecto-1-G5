package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VisualizadorVentas extends JPanel {
    private static final int DAYS_IN_WEEK = 7;
    private static final int WEEKS_IN_YEAR = 52;
    private static final int TOTAL_DAYS = DAYS_IN_WEEK * WEEKS_IN_YEAR;
    private static final int CELL_SIZE = 20; // Tamaño de cada celda
    private static final int PADDING = 2; // Espacio entre celdas
    private static final int TEXT_PADDING = 30; // Espacio para los textos
    private Map<String, Integer> salesData;

    public VisualizadorVentas(Map<String, Integer> salesMap) {
        // Utilizar el mapa de ventas proporcionado
        salesData = salesMap;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar título
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
        String title = "VENTAS DIARIAS 2023";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (getWidth() - titleWidth) / 2, 15);

        // Dibujar etiquetas de los días de la semana
        String[] daysOfWeek = {"Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"};
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            int y = i * (CELL_SIZE + PADDING) + TEXT_PADDING + CELL_SIZE / 2;
            g2d.drawString(daysOfWeek[i], 4, y);
        }

        // Dibujar etiquetas de los meses
        String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        int daysInMonth = TOTAL_DAYS / months.length;
        for (int i = 0; i < months.length; i++) {
            int x = i * daysInMonth * (CELL_SIZE + PADDING) / DAYS_IN_WEEK + TEXT_PADDING;
            g2d.drawString(months[i], x, TEXT_PADDING - 1);
        }

        // Dibujar las celdas de ventas
        for (int i = 0; i < TOTAL_DAYS; i++) {
            int row = i % DAYS_IN_WEEK;
            int col = i / DAYS_IN_WEEK;
            int x = col * (CELL_SIZE + PADDING) + TEXT_PADDING;
            int y = row * (CELL_SIZE + PADDING) + TEXT_PADDING;

            String fecha=diaYMesDesdeDiaDelAnio(i+1);

            // Obtener las ventas para la fecha actual del mapa
            Integer sales = salesData.get(fecha);
            if (sales == null) {
                sales = 0; // Si no hay datos para esta fecha, establecer las ventas en 0
            }

    
            // Convertir la intensidad a un valor entre 0 y 255
            int colorValue = (int) (255 * (1 - (float) sales / getMaxSales()));
            // Utilizar un color con el mismo valor para los componentes RGB para obtener una escala de grises
            Color color = new Color(colorValue, colorValue, colorValue);

            g2d.setColor(color);
            g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
    }

    private int getMaxSales() {
        int max = 0;
        for (Integer sales : salesData.values()) {
            max = Math.max(max, sales);
        }
        return max;
    }
    public static String diaYMesDesdeDiaDelAnio(int diaDelAnio) {
        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        int mes = 1;
        
        // Si el día del año es mayor que 334, es diciembre o el último día del año
        if (diaDelAnio > 334) {
            return String.format("%02d/12", diaDelAnio - 334);
        }
        
        while (diaDelAnio > diasPorMes[mes]) {
            diaDelAnio -= diasPorMes[mes];
            mes++;
        }
        
        String dia = String.format("%02d", diaDelAnio);
        String mesStr = String.format("%02d", mes);

        return (dia +"/" + mesStr+ "/2023") ;
    }
    

         
    }

         
    