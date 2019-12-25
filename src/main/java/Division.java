public class Division {
    public Integer divid(Integer a,Integer b){
        if( 0 == b ){
            return null;
        }else if(a > 1000){
            return null;
        }else if( b > a ){
            return 0;
        }
        else if( 0 ==a ){
            return 0;
        }else {
            return a/b;
        }
    }
}
