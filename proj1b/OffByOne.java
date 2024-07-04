public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        if(x == 'a' && y == 'z'){
            return false;
        }
        if(x == 'z' && y == 'a'){
            return false;
        }
        int a = x - y;
        if(a == 1 || a == -1) {
            return true;
        }else{
            return false;
        }
    }
}