import java.util.concurrent.Exchanger;

public class ThreadsApp {

    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger();
        new Thread(new PutThread(ex)).start();
        new Thread(new GetThread(ex)).start();
    }
}

class PutThread implements Runnable{

    Exchanger<String> exchanger;
    String message;

    PutThread(Exchanger ex){

        this.exchanger=ex;
        message = "Hello Java!";
    }
    public void run(){

        try{
            message=exchanger.exchange(message);
            System.out.println("PutThread получил: " + message);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
class GetThread implements Runnable{

    Exchanger<String> exchanger;
    String message;

    GetThread(Exchanger ex){

        this.exchanger=ex;
        message = "Привет мир!";
    }
    public void run(){

        try{
            message=exchanger.exchange(message);
            System.out.println("GetThread получил: " + message);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
} 