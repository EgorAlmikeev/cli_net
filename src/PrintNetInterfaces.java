import java.net.*;
import java.util.*;


public class PrintNetInterfaces
{
    public static void main(String args[]) throws SocketException
    {
        int arg_length = args.length;

        String help = "NAME : cli_net - client displays network interfaces and info." +
                "\n\nUSAGE :" +
                "\n\tcli_net [global options] command [command options] [arguments...]" +
                "\n\nVERSION :" +
                "\n\t0.0.0" +
                "\n\nCOMMANDS:" +
                "\n\t--help, -h Shows a list of commands" +
                "\n\t--list, -l Prints all net interfaces" +
                "\n\t--show, -s [interface] Displays detail info about interface" +
                "\n\nGLOBAL OPTIONS :" +
                "\n\t--version Shows version information";

        switch (arg_length)
        {
            case 0 : System.out.println(help); break;
            case 1 :
            {
                switch (args[0])
                {
                    case "--help" : System.out.println(help); break;
                    case "-h" : System.out.println(help); break;
                    case "--list" : showNetList(); break;
                    case "-l" : showNetList(); break;
                    case "--version" : System.out.println("version 0.0.0"); break;
                    default : System.out.println(help);
                }
                break;
            }
            case 2 :
            {
                switch (args[0])
                {
                    case "--show" : showInterfaceInfo(args[1]); break;
                    case "-s" : showInterfaceInfo(args[1]); break;
                    default: System.out.println(help);
                }
                break;
            }
            default : System.out.println("Too many arguments");
        }

        System.out.println();
    }

    private static void showNetList()
    {
        try
        {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface i : Collections.list(nets))
                System.out.print(i.getName() + " ");
            System.out.println();
        }
        catch (SocketException ex_obj)
        {
            System.out.println(ex_obj);
            return;
        }
    }

    private static void showInterfaceInfo(String net_interface)
    {
        try
        {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface i : Collections.list(nets))
                if (i.getName().equals(net_interface))
                {
                    displayInfo(i);
                    return;
                }

            System.out.println("No such interface : " + net_interface);
        }
        catch (SocketException ex_obj)
        {
            System.out.println(ex_obj);
            return;
        }
    }

    private static void displayInfo(NetworkInterface net_int) throws SocketException
    {
        System.out.println("Display name : " + net_int.getDisplayName());
        System.out.println("Name : " + net_int.getName());
        System.out.println("Hardware address : " + Arrays.toString(net_int.getHardwareAddress()));

        List<InterfaceAddress> interfaceAddresses = net_int.getInterfaceAddresses();
        for (InterfaceAddress i : interfaceAddresses)
        {
            System.out.println("InterfaceAddress (Ipv4/Ipv6) : " + i.getAddress());
        }

        System.out.println("Virtual : " + net_int.isVirtual());
        System.out.println("MTU : " + net_int.getMTU());

    }
}