import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main( String[] args )
    {
        String srcData = "abcaabbccaacababaabaac";
        Pattern pat = Pattern.compile( "(a(b)?)+" );
        Matcher matcher = pat.matcher( srcData );

        ArrayList<CollectedInfo> results = new ArrayList<>();

        while ( matcher.find() )
        {
            CollectedInfo info = new CollectedInfo( matcher.start(), matcher.group().length(), matcher.group( 0 ), matcher.group( 1 ) );
            results.add( info );
        }

        ArrayList<RefinedData> refinedData = processExtractedInfo( results );
        for ( RefinedData data : refinedData )
        {
            System.out.println( "Data point: " + data.largeNumber );
        }
    }

    public static ArrayList<RefinedData> processExtractedInfo( ArrayList<CollectedInfo> extracted )
    {
        ArrayList<RefinedData> processed = new ArrayList<>();
        for ( CollectedInfo info : extracted )
        {
            processed.add( new RefinedData( info.start * info.count * ( info.foundB.length() < info.foundA.length() ? 2 : 5 ) ) );
        }

        return processed;
    }
}

class CollectedInfo
{
    public final int start;
    public final int count;
    public final String foundA;
    public final String foundB;

    public CollectedInfo( int start, int count, String foundA, String foundB )
    {
        this.start = start;
        this.count = count;
        this.foundA = foundA;
        this.foundB = foundB;
    }
}

class RefinedData
{
    public final int largeNumber;

    public RefinedData( int largeNumber )
    {
        this.largeNumber = largeNumber;
    }
}
