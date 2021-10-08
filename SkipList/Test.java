/**
 * @author AJWuu
 */

package skip;

public class Test {

	public static void main(String[] args) {
		SkipList list = new SkipList();
		list.add(11);
		list.add(22);
		list.add(33);
		System.out.println("Search 0: " + list.search(0));
		list.add(44);
		list.add(22);
		System.out.println("Search 22: " + list.search(22));
		System.out.println("Erase 0: " + list.erase(0));
		System.out.println("Erase 11: " + list.erase(11));
		System.out.println("Erase 22: " + list.erase(22));
		System.out.println("Search 11: " + list.search(11));
		System.out.println("Search 22: " + list.search(22));
		System.out.println("Erase 22: " + list.erase(22));
		System.out.println("Search 22: " + list.search(22));
	}

}
