
package nqueen;

/**
 *
 * @author soneya
 */


import java.util.Scanner; 

public class nqueenfc 
{

    int[] numOfQueen;
    int N;
    int soni=0;
    int [][] ARRAY;
    public nqueenfc(int Q) {
        
        numOfQueen = new int[Q];
        ARRAY= new int [Q][Q];
        N=Q;
        initialize_array();
        
        
}
 
    
    
 private void initialize_array()
 {
     for (int i = 0; i < N; i++) 
     {
        numOfQueen[i]=-1;
        for (int j = 0; j < N; j++) 
        {
          ARRAY[i][j]=0; 
        }

     }
                 
 }
 
 public void print_numOfQueen(int x [])
 {
     for (int i = 0; i < N; i++) 
     {
                System.out.print(x[i]+" ");
     }
     System.out.println();    
     System.out.println(); 
 }
 
 public void print_array(int x[][])
 { 
     
     for (int i = 0; i < N; i++) 
     {
            
            for (int j = 0; j < N; j++) 
            {
                System.out.print(x[i][j]+" ");

            }
            System.out.println(" ");
     }
                 
 }
  
 
 
    public void printQueens(int[] x) {
        int n = x.length;
        soni++;
        System.out.println(); 
        System.out.print("solution:"+soni+": "); 
        print_numOfQueen(x);
        
        
        for (int i = 0; i < n; i++) {
          System.out.print(" _");
         }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("|");
            for (int j = 0; j < n; j++) {
                if (x[i] == j) {
                    System.out.print("Q|");
                } else {
                    System.out.print("_|");
                }
            }
            System.out.println();
        }
        System.out.println();
        
        
        
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (x[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }*/
        
    }
 //...........................................................................//
 
 
   
  
    
   public int SelectRowMRV(int array [][],int x[])
   {
       
     
     int min_row = -1 ;
     int infinity=10000000;
     
     for (int i=0;i<N;i++)
     { 
        if(x[i]==-1)
        {
            int count=0;
            for(int j=0;j<N;j++)
            {
                if(array[i][j]==0) 
                {
                     count++;
                }

            }
            if(count==0) {
                continue;
            }
            if(count<infinity) 
            {
                infinity=count;
                min_row=i;
            }
        }
       
         
     }
     
     
     if(min_row==-1) 
     {
           return -1;
       }
     else {
           return min_row;
       }
   }
   
  //............................................................................ 
   
    public boolean placeNQueensAndFCandMRV(int array[][],int x[],int N)
    {
        boolean flag,succ;
        
        if(noMoreRow(array,x))
        {
           printQueens(x);
           return true;
          
        }
        
       int i= SelectRowMRV(array,x);
       if(i==-1) {
            return false;
        }
       
       for (int j=0; j<N ; j++)
       {
           x[i]=j;
           
           flag= forwardCheck(array,i,j);
           
           if (flag==false)
           {
               continue;
           }
           
           
           int array2[][]= new int [N][N];
           int x2[]= new int [N];

           for(int k=0;k<N;k++){
               System.arraycopy(array[k], 0, array2[k], 0, N);
           }
           System.arraycopy(x, 0, x2, 0, N);
           forward(array2,i,j);
          
           succ=placeNQueensAndFCandMRV(array2,x2,N);
            
          
           
       }
       return false;
      
   }
   //...........................................................................
    
    private boolean noMoreRow(int X[][],int y[]) 
    { 
     
     
        for (int i=0;i<N;i++)
        { 

               for(int j=0;j<N;j++)
               {
                   if(X[i][j]==0) 
                   {
                      return false;
                   }

               }


        }
        
        
       boolean x=checkOneDarray(y);
       return x;
    }
    
    
    
    public boolean checkOneDarray(int y[]){
        for (int i=0; i<N; i++)
        {
            
              if (y[i]==-1) 
                {
                    return false;
                }  
            
        }
        return true;
    }
//.............................................................................  
    public void forward(int array[][],int row,int col)
    {
        int column0=col-1;
        int column1=col-1;
        
        int column2=col+1;
        int column3=col+1;
        //System.out.println("row"+row);
        //System.out.println("column"+col);
        for (int i=0;i<N;i++) // setting the column value to 1    //row 1 col 2
        {
           array[i][col]=1;
           //System.out.println("problem"+i+","+col);
               
        }
        
        for (int i=0;i<N;i++) // setting the column value to 1    //row 1 col 2
        {
           array[row][i]=1;
           //System.out.println("problem"+i+","+col);
               
        }
        //.............................................................................
        for (int i= row+1;i<N;i++)
        {
           if(column1>=0)
           {
              // System.out.println("--problem"+i+","+column1);
               array[i][column1--]=1;
               
           }
           if(column2<N)
           {
              // System.out.println("++problem"+i+","+column2);
               array[i][column2++]=1;
          
           }
           
           
        }
        //...................................................................
        for (int i=row-1;i>=0;i--)
        {  //row =0
           
            if(column3<N)
            {
               //System.out.println("..problem"+i+","+column3);
                array[i][column3++]=1;
            }
            if(column0>=0)
            {
               // System.out.println(",,problem"+i+","+column0);
                array[i][column0--]=1;
            }
         
        }
        
        
    }
    
    //.........................................................................
    
    private boolean forwardCheck(int array[][],int row,int col) { 
        if(array[row][col]==1) {
            return false;
        }
        return true;
    }
   
    
    
    
  
    
    
    //........................main function.....................................
    
    public static void main(String args[]) 
    {
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter # Queens:");
        int input;
        input = in.nextInt(); 
        System.out.println("N="+input);
        nqueenfc Q = new nqueenfc(input);
        
       
        //Q.print_numOfQueen(Q.numOfQueen);
        //Q.print_array(Q.ARRAY);
        
        Q.placeNQueensAndFCandMRV(Q.ARRAY,Q.numOfQueen,Q.N);
        
    }

    
}


