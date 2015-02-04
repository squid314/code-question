import java.util.ArrayList;
import java.util.HashMap;

public class Numbers
{
    public static void main( String[] args )
    {
        String data = "1,2,3,4,t,6,7,8,NEIN!,10,20,30,forty,50,60,70,a0,90,100,200,300,400,500,big-number,700,800,900,1000,";
        String[] split = data.split( ",", 1000 );
        // have to save the original Strings since String.join() only accepts Strings
        ArrayList<String> numberStrings = new ArrayList<>(  );
        String notNumbers = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()";
        HashMap<String, Long> realNumbers = new HashMap<>(  );
        for ( String s : split )
            if ( !containsAny( s, notNumbers ) )
                try {
                    numberStrings.add( s );
                    realNumbers.put( s, Long.valueOf( s ) );
                } catch ( NumberFormatException e ){ /* i already tested if it wasn't a number; this can't happen */ }

        long sum = 0;
        long productSum = 0;
        for ( int i = 0; i < numberStrings.size() ; ++i )
        {
            String currentNumberString = numberStrings.get( i );
            sum += realNumbers.get( currentNumberString );
            String nextNumberString = numberStrings.get( i + 1 );
            productSum += realNumbers.get( currentNumberString ) * realNumbers.get( nextNumberString );
        }
        System.out.println("Actual numbers in input: " + String.join( ",", numberStrings ));
        System.out.println("   Sum:  " + sum);
        System.out.println("   Mean: " + sum / (double) numberStrings.size());
        System.out.println("   Sum of product of neighbors:  " + productSum );
        System.out.println("   mean of product of neighbors: " + productSum / (double) numberStrings.size() );
    }

    public static boolean containsAny(String test, String search){
        for ( int i=0; i<test.length(); ++i)
            if ( search.indexOf( test.charAt( i ) ) != -1 )
                return true;
        return false;
    }
}
