

//David Grossman


public class Main {
    String array1[] = {"s1", "s2", "s3", "s4", "s5", "s6", "s7"};
    
    public void main(String[] args) {
		//loops forwards
        for(int i=0; i<array1.length; i++){
            System.out.println(array1[i]);
        }
		//loops backwards
        for(int i=array1.length-1; i>=0; i--){
            System.out.println(array1[i]);
        }
    }
}

