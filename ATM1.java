import java.util.*;
class history{
    Scanner s=new Scanner(System.in);
    int bal=100000000;
    
    void view(int[] array,int[] v,int idx,String[] accountnumber){
        System.out.println("history as follows....");
        for(int i=0;i<idx;i++){
            if(array[i]==1)
               System.out.println("withdraw of "+v[i]);
            if(array[i]==2)
               System.out.println("deposit of "+v[i]);
            if(array[i]==3)
               System.out.println("transfer of "+v[i]+" to "+accountnumber[i]);
        }
    }
    int login(String[] un,String[] ps){
        int q=-1,f=0;
        String username,password;
        
        System.out.println("\nenter username:");
        username=s.next();
        System.out.println("enter password:");
        password=s.next();
        for(int i=0;i<un.length;i++){
            if(un[i].compareTo(username)==0 && ps[i].compareTo(password)==0){
                f=1;
                q=i; 
            }
        }
        if(f==1)
           return q;
        else
           return -1;
    }

    void deposit(int balance[],int q,int[] array,int[] v,int idx){
        System.out.println("enter amount to deposit:");
        int amount=s.nextInt();
        balance[q]=balance[q]+amount;
        System.out.println("\nSuccessfully deposited");
        array[idx]=2;
        v[idx]=amount;
    }

    void withdraw(int balance[],int q,int[] array,int[] v,int idx){
         System.out.println("enter amount want to withdraw:");
         int amount=s.nextInt();
         if(amount<=balance[q]){
            System.out.println("\nsuccessfully withdrawn");
            balance[q]=balance[q]-amount;
            array[idx]=1;
            v[idx]=amount;
         }
         else 
            System.out.println("Insufficient balance");
    }
    
    void transfer(int[] balance,String[] acnum,String[] un,String[] accountnumber,int q,int[] array,int[] v,int idx){
        System.out.println("enter account nuumber:");
        String anum=s.next();
        int t=-1,r;
        for(int i=0;i<acnum.length;i++){
            if(acnum[i].compareTo(anum)==0)
              t=i;
        }
        if(t!=-1){
            System.out.println("enter amount to transfer");
            int money=s.nextInt();
            if(money<=balance[q]){
                System.out.println("before transfer::");
                for(r=0;r<acnum.length;r++){
                    System.out.println(acnum[r]+"------"+balance[r]);
                }
               balance[t]=balance[t]+money;
               balance[q]=balance[q]-money;
               accountnumber[idx]=anum;
               array[idx]=3;
               v[idx]=money;
               System.out.println("\nSuccessfully transferred\n");
               System.out.println("after transfer::");
                for(r=0;r<acnum.length;r++){
                    System.out.println(acnum[r]+"------"+balance[r]);
                }
            }
            else
              System.out.println("Insufficient balance");
        }
    }
    void display(int[] balance,int q){
        System.out.println("balance is: "+balance[q]);
    }
    void quit(){
        System.out.println("Thank you...");
    }
}
class ATM1{
    public static void main(String[] args){
        history p=new history();
        int q,f,w=0,i,idx=0;
        int[] v=new int[10000000];
        int[] array=new int[10000000];
        String[] accountnumber=new String[10000000];
    
        Scanner s=new Scanner(System.in);
        String[] un={"abc","pqr","xyz"};
        String[] ps={"123","456","789"};
        String[] acnum={"201420152016","201520162017","201620172018"};
        int[] balance={200000,400000,600000};
         q=p.login(un,ps);
        if(q!=-1){
            idx=0;
             System.out.println("successfully logged in...");
          while(w!=1){
            System.out.println("\n1.withdraw\n2.deposit\n3.transfer\n4.history\n5.display\n6.quit");
            System.out.println("enter your choice:");
            int choice=s.nextInt();
            idx++;
            switch(choice){
                case 1:
                    p.withdraw(balance,q,array,v,idx);
                    break;
                case 2:
                    p.deposit(balance,q,array,v,idx);break; 
                case 3:
                    p.transfer(balance,acnum,un,accountnumber,q,array,v,idx);break;
                case 4:
                    p.view(array,v,idx,accountnumber);break;
                case 5:
                    p.display(balance,q);break;
                case 6:
                    p.quit();w=1;break;
                default:
                    System.out.println("enter valid choice");break;
            }
          }
        }
        else{
            System.out.println("Invalid login.....");
        }
    }
}