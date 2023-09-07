package ch.supsi.sweng2.ex1;

public class Worker {
    private final int startValue;

    public Worker(int startValue) {
        this.startValue = startValue;
    }

    public static double conversion(final int v){
        if(v%2==0){
            return v/2;

        }else{
            return v*3+1;
        }

    }
    public int sequence() throws Exception {

        int cnt=0;
        if(startValue<=2){
            throw new Exception();
        }
        double tmp=startValue;
        while (tmp>1){
            tmp=conversion((int)tmp);
            cnt++;

        }
        return cnt;
   }
}
