/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfada;

/**
 *
 * @author lucho
 */
public class FinalFADA {
String lcs(String[] strings)
{
    if (strings.length == 0)
        return "";
    if (strings.length == 1)
        return strings[0];
    int max = -1;
    int cacheSize = 1;
    for (int i = 0; i < strings.length; i++)
    {
        cacheSize *= strings[i].length();
        if (strings[i].length() > max)
            max = strings[i].length();
    }
    String[] cache = new String[cacheSize];
    int[] indexes = new int[strings.length];
    for (int i = 0; i < indexes.length; i++)
        indexes[i] = strings[i].length() - 1;
    return lcsBack(strings, indexes, cache);
}
String lcsBack(String[] strings, int[] indexes, String[] cache)
{
    for (int i = 0; i < indexes.length; i++ )
        if (indexes[i] == -1)
            return "";
    boolean match = true;
    for (int i = 1; i < indexes.length; i++)
    {
         if (strings[0].charAt(indexes[0]) != strings[i].charAt(indexes[i]))
         {
             match = false;
             break;
         } else {
         }
    }
    if (match)
    {
        int[] newIndexes = new int[indexes.length];
        for (int i = 0; i < indexes.length; i++)
            newIndexes[i] = indexes[i] - 1;
        String result = lcsBack(strings, newIndexes, cache) + strings[0].charAt(indexes[0]);
        cache[calcCachePos(indexes, strings)] = result;
        return result;
    }
    else
    {
        String[] substrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            if (indexes[i] <= 0)
                substrings[i] = "";
            else
            {
                int[] newIndexes = new int[indexes.length];
                for (int j = 0; j < indexes.length; j++)
                    newIndexes[j] = indexes[j];
                newIndexes[i]--;
                int cachePos = calcCachePos(newIndexes, strings);
                if (cache[cachePos] == null)
                    substrings[i] = lcsBack(strings, newIndexes, cache);
                else
                    substrings[i] = cache[cachePos];
            }
        }
        String longeststring = "";
        int longestlength = 0;
        for (int i = 0; i < substrings.length; i++)
        {
            if (substrings[i].length() > longestlength)
            {
                longeststring = substrings[i];
                longestlength = longeststring.length();
            }
        }
        cache[calcCachePos(indexes, strings)] = longeststring;
        return longeststring;
    }
}
int calcCachePos(int[] indexes, String[] strings)
{
    int factor = 1;
    int pos = 0;
    for (int i = 0; i < indexes.length; i++)
    {
        pos += indexes[i] * factor;
        factor *= strings[i].length();
    }
    return pos;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] a = {"sfsdfareffr", "saer", "ser"};
        FinalFADA proyecto = new FinalFADA();
        System.out.print(proyecto.lcs(a));
        
    }
    
    
}
