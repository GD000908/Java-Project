//SLiinkedList를 사용하기 위해 get(),insert(),remove(),search(),show() 구현

package pro1;
import java.util.Scanner;
import java.util.LinkedList;
//ET = Element Type

//LinearList 인터페이스
interface LinearList<ET>{
	void insert(ET e);
	Boolean remove(ET e);
	int search(ET e);
	void show();
	ET get(int index);
}

//class LinearList를 상속받는 SLinkedList 생성
class SLinkedList <ET> implements LinearList<ET>{
	
	//Node<ET> 자료형의 first변수 생성
	private Node<ET> first;
	//List의 정수형 size변수
	private int size;  // number of elements in the list
	//constructor
	public SLinkedList() {
		first = null;
	}
	
	//Node<ET> class
	class Node<ET>{
		//elem ET형 변수
		private ET elem;
		//Node형 next변수
		private Node next;
		//constructor
		Node(ET elem, Node next){
			this.elem = elem;
			this.next = next;
		}
	} 
	
	//LinearList 인터페이스 속에 있는 insert 함수 오버라이드
	@Override
	public void insert(ET theElem) {
		first = new Node<ET>(theElem, first);
		size++;
	}
	
	//LinearList 인터페이스 속에 있는 remove 함수 오버라이드
	@Override
	public Boolean remove(ET theElem) {	
		// pn, cn  : previous and current node.
		Node<ET> pn = null, cn;
		for(cn = first; cn != null; cn = cn.next) {
			if(cn.elem.equals(theElem)) 
				break;
			pn = cn;
		}
		
		if(cn == null) 
			return false;  // 지우려는 값이 리스트에 없어서 찾지못할떄.
		
		if(cn.equals(first)) //if 지우려는 값(cn)이 첫번째(first)값일때.
		{
			Node<ET> NextNode = first.next; 
			first=NextNode; //cn의 이전값이 없기때문에 다음값의 주소값과 같다고 설정. ??
			size --;
			return true;
		}
		
		pn.next = cn.next;
		size--;
		return true; 
		//위처럼 return값이 있을때 main에서 출력하려면 print문을 사용하여야한다.
		//만약 return값이 없고 void ~~ 이면 print없이 함수를 호출하면 된다. 
		}
	
	//LinearList 인터페이스 속에 있는 search 함수 오버라이드
	@Override
	public int search(ET theElem) {
		int i = 0;
		Node<ET> cn;
		   for(cn = first; cn != null; cn = cn.next) {
			   if( cn.elem.equals(theElem) ) 
				   return i;
			   i++;				
	   	   }
		   return -1;
	}
	
	//LinearList 인터페이스 속에 있는 get 함수 오버라이드
	@Override
	public ET get(int index) {
		if (index < 0 || index >= size) { 
			try { // ***내가 찾으려는 elem이 생성되어 있는 리스트에 없을때 
				throw new IndexOutOfBoundsException();
			}
			catch(IndexOutOfBoundsException e){
				return null;  // ***null값을 반환한다. 
			}
		}
		Node<ET> cn = first;
		for(int i = 0; i < index; i++) {
			cn = cn.next;
		}
		return cn.elem;
	}
	
	//LinearList 인터페이스 속에 있는 show 함수 오버라이드
	@Override
	//show()는 void. 즉 return값이 없으므로 main에서 출력할  때 list.show()하면 print문 없이 출력 가능.
	public void show() {
		Node<ET> cn = first;
		for(int i=0; i<size; ++i) {
			
			System.out.print(cn.elem + " ");
			cn = cn.next;
		}
		System.out.println();
	}
}


//Main문
public class main {
	public static void main(String[] args) {
		SLinkedList<Integer> list = new SLinkedList<Integer>();
		//list 생성후 값 삽입. 
		list.insert(50);
		list.insert(30);
		list.insert(70);
		list.insert(20);
		list.insert(40);
		list.insert(60);
		
		//show()호출 하여 list값 전체 출력. 
		list.show();	
		
		//return값이 있는 get함수는 print사용. get함수는 원하는 순서에 있는 elem 출력.
		System.out.println(list.get(3));
		System.out.println(list.get(9));
		
		//search는 내가 원하는elem이 리스트 몇번째에 존재하는지 출력.
		System.out.println(list.search(30));
		System.out.println(list.search(90));
		
		//리스트에서 삭제하고 싶은 elem을 입력하면 리스트에서 삭제.
		list.remove(60);
		list.remove(70);
		list.remove(50);
		list.remove(90);
		
		//삭제되고 난 뒤의 리스트 전체값을 출력
		list.show();
		
		
		
		
	}
	
}
