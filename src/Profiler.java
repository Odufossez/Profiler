import java.util.function.Function;

public class Profiler {

    @FunctionalInterface
    interface ArgDoubleRetBool{
        Boolean apply(Double d);
    }

    static long globalTime ;
    static long globalCalls ;

    public static Boolean analyse(ArgDoubleRetBool oneMethod , double p) {
        long start = timestamp();
        Boolean res = oneMethod.apply(p);
        globalTime+= timestamp() - start;
        globalCalls++;
        return res;
    }

    public static long getGlobalTime(){
        return globalTime;
    }

    public static long getGlobalCalls(){
        return globalCalls;
    }

    public static void init(){
        globalTime = 0;
        globalCalls = 0;
    }

    public static void afficheRes(){
        System.out.println(getGlobalCalls() + " appels en " + getGlobalTime()/1000.0 + " µs");
        System.out.println("Soit en moyenne " + getGlobalTime()/getGlobalCalls() + " µs");
    }

    public static String timestamp(long clock0){
        String result = null ;

        if (clock0 > 0){
            double elapsed = (System.nanoTime() - clock0) / 1e9;
            String unit = "s";
            if (elapsed < 1.0) {
                elapsed *= 1000.0;
                unit = "ms";
            }
            result = String.format("%.4g%s elapsed", elapsed, unit);
        }
        return result;
    }

    public static long timestamp() {
        return System.nanoTime();
    }
}
