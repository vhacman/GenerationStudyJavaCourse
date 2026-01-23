import java.math.BigDecimal;

public class BigDecimalUtil
{
    /**
     * Converte una stringa con separatore delle migliaia (punto) e decimale (virgola)
     * in una stringa con solo punto come separatore decimale.
     *
     * @param bigdecimal stringa da convertire (es. "10.000,50")
     * @return stringa convertita (es. "10000.50"), o stringa vuota se l'input non è valido
     */
    static String convertToString(String bigdecimal)
    {
        try
        {
            // Rimuove i punti (separatore migliaia) e sostituisce la virgola con il punto (separatore decimale)
            String		clean = bigdecimal.replace(".", "").replace(",", ".");
            BigDecimal 	value = new BigDecimal(clean);
            return value.toString();
        }
        catch (NumberFormatException e)
        {
        	e.printStackTrace();
            return "";
        }
    }
}
