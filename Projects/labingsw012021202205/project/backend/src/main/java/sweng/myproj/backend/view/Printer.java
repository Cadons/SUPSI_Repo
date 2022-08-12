package sweng.myproj.backend.view;

interface IPrinter{
    public String print(Printable printable);
}
public class Printer implements IPrinter{

    public Printer() {

    }

    public String print(Printable printable) {
        var tmp = printable.getData().get(0).split(" ");//split string
        String output="";
        for (String string : tmp
        ) {
            string=Character.toUpperCase(string.charAt(0))+""+string.substring(1);//capitalize first char and add remain string
            output+=string+" ";//add space
        }
        return output.substring(0,output.length()-1);//merge and remove last space

    }
}
