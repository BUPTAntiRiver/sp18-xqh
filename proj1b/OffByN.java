public class OffByN implements CharacterComparator{
    private final int n;
    OffByN(int N){
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y){
        int a = x - y;
        if(a == n || a == -n) {
            return true;
        }else{
            return false;
        }
    }
	
}