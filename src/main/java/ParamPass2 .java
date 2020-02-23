import java.util.HashMap;
import java.util.Map;

class ParamPass2 {
    public static void main (String[] args)
    {
        ParamPass2 para = new ParamPass2();
        para.middle();
        HashMap<Integer, Integer> b = new HashMap<>();
        int[] a = {1,3,8,9,15,12,15,3,3,9};
        for(int i=0; i<a.length; i++){
            if(b.get(a[i])==null){
                b.put(a[i],1);
            }else {
                b.put(a[i],b.get(a[i])+1);
            }
        }

        for(Map.Entry<Integer,Integer> i:b.entrySet()){


        }
    }

    public void middle(){
        System.out.println(this.getClass().getResource("server/user/api/user.json").getPath());
    }
}

