public class Palindrome {
	public Deque<Character> wordToDeque(String word) {
		LinkedListDeque<Character> p = new LinkedListDeque<>();
		int len = word.length();
		for (int i = 0; i < len; i += 1) {
			p.addLast(word.charAt(i));
		}
		return p;
	}

	public boolean isPalindrome(String word){
		int len = word.length();
		for(int i = 0; i < len / 2; i += 1){
			if(word.charAt(i) != word.charAt(len - i - 1)){
				return false;
			}
		}
		return true;
	}

	public boolean isPalindrome(String word, CharacterComparator cc){
		int len = word.length();
		for(int i = 0; i < len / 2; i += 1){
			if(!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))){
				return false;
			}
		}
		return true;
	}
}