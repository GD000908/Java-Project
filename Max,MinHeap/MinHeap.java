//insert한 값들을 작은순서대로 정렬한뒤에 작은값부터 삭제하는것을력출력

class MinHeap<ET extends Comparable<ET>> {
	private ET[] arr;
	private int size; // number of elements in arr[ ]
	private static final int ROOT = 1; // index 0 is not used.
	private static final int INIT_CAP = 4; // initial capacity

	// constructors
	public MinHeap() {
		this(INIT_CAP);
	}

	public MinHeap(int capacity) {
		arr = (ET[]) new Comparable[capacity + 1];
	}

	public MinHeap(ET[] a, int s) {
		arr = a;
		size = s;
	}

	public void insert( ET theKey )
    {
        if( size == arr.length-1 ) resize( 2*size );

        size++;
        int q = size;  // q : current node
        int p; // parent of q
        while( q != ROOT ) {
            p = q/2;
            if( theKey.compareTo(arr[p]) >= 0 ) break;
            arr[q] = arr[p];
            q = p;	 p = q / 2;
        }
        arr[q] = theKey;

        return;
    }

	public ET removeMin(  )
    {
        int q;  //  current node
        ET keyToReturn;

        if( size == 0 ) { return null; }  // empty heap

        keyToReturn = arr[ROOT];
        arr[ROOT] = arr[size];
        size--;  // removed
        heapify( ROOT );
        
        if( size <= arr.length/4 ) resize( arr.length/2 );
        return  keyToReturn;
    }  


	public  void buildHeap( ) {
        for( int i = size/2; i > 0; i-- )
            heapify( i );
    }

	 protected  void  heapify(  int  q ) {
	        int bc, rc;  // bc: child with bigger key, rc: right child
	        
	        ET  tmpKey = arr[q];
	        bc = 2*q;  // left child of q.
	        while( bc <= size )  {
	            rc = bc + 1;
	            if( rc <= size && arr[bc].compareTo( arr[rc]) > 0 ) bc = rc;

	            if( tmpKey.compareTo( arr[bc] ) < 0 ) break;
	            arr[q] = arr[bc];
	            q = bc;
	            bc = 2*q;
	        }
	        arr[q] = tmpKey;
	    }

	 protected void resize( int newSize )
	 {
	     ET[ ] a = (ET[ ]) new Comparable[newSize+1];

	     for( int i = 1; i <= size; i++ ) {
	            a[i] = arr[i];
	     }

	     arr = a;
	 }

} // end of MinHeap class

public class Main {
    public static void main(String[] args) {
    	//70  80  40  30  50  60  20  10
    	
    	MinHeap<Integer> MH = new MinHeap();
    	MH.insert(70);
    	MH.insert(80);
    	MH.insert(40);
    	MH.insert(30);
    	MH.insert(50);
    	MH.insert(60);
    	MH.insert(20);
    	MH.insert(10);
    	
    	System.out.println("첫번째 삭제된 원소   : "+MH.removeMin());
    	System.out.println("두번째 삭제된 원소   : "+MH.removeMin());
    	System.out.println("세번째 삭제된 원소   : "+MH.removeMin());
    	System.out.println("네번째 삭제된 원소   : "+MH.removeMin());
    	System.out.println("다섯번째 삭제된 원소 : "+MH.removeMin());
    	System.out.println("여섯번째 삭제된 원소 : "+MH.removeMin());
    	System.out.println("일곱번째 삭제된 원소 : "+MH.removeMin());
    	System.out.println("여덟번째 삭제된 원소 : "+MH.removeMin());
    	System.out.println("모든원소가 삭제된 후에 출력 : "+MH.removeMin());

    }
}
