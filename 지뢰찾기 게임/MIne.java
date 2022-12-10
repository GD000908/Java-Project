
/*
 * 2차원 어레이에 랜덤으로 지뢰를 심어주고, 그에따른 숫자들을 할당해준다.
 * GUI구현 전 콘솔에서 board를 확인하고 이 board정보를 button어레이에 넘겨주었다.
 */

public class Mine {
	int N, M, mine;
	int [][]board ;
	int mineCount, ii, jj;
	int x,y;	
	
	public Mine(int N, int M, int mine) {
	board = new int[N][M];	
	
	mineCount=0;
	ii=0; jj=0;
	x=0;	  y=0;
	this.N = N;
	this.M = M;
	this.mine = mine;
	
	init();
	setNum();
	printBoard();
	}
	
	//Initialize all 0
	void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				//board[i][j]='0';
				board[i][j]=0;
			}
		}
		
	//Set mine randomly
		for(int k=0; k<mine; k++) {
			x = (int)(Math.random() * N);
			y = (int)(Math.random() * M);	
			//board[x][y] ='*';
			board[x][y]=9;
		}	
	}
	
	//Set number about near mine 
	void setNum() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				switch(board[i][j]) {

				//지뢰이면 숫자 부여x 
				case 9:
					break;
				
					//주위 8칸 지뢰 count하기 
				default:
					for(mineCount=0, ii= (i>0)?i-1:0; ii<=i+1&&ii<=N-1; ii++) 
						for( jj=(j>0)?j-1:0; jj<=j+1&&jj<=M-1; jj++) 
							if(board[ii][jj]== 9) mineCount++;
		         
					//System.out.print(mineCount);
					board[i][j] = mineCount;       
					break;
				}
			}
		}
	}
	
	//Print board
	void printBoard() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) 
				System.out.print(board[i][j]);
			System.out.println();
		}
	}
	
	int[][] getBoard(){
		return board;
	}
	
}
